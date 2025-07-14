package apiAssignment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TelecomProject {

	private String token;
	private String email;
	private String password = "myPassword";
	private String contactId;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}

//Test Case 1
	@Test(priority = 1)
	public void testCreateUser() {
		email = "test_" + UUID.randomUUID() + "@fake.com";

		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("firstName", "Test");
		requestBody.put("lastName", "User");
		requestBody.put("email", email);
		requestBody.put("password", password);

		given().contentType(ContentType.JSON).body(requestBody).when().post("/users").then().statusCode(201)
				.statusLine(containsString("Created"));
	}

	// Test Case 4
	@Test(priority = 2, dependsOnMethods = "testCreateUser")
	public void testLoginUser() {
		Map<String, String> loginBody = new HashMap<>();
		loginBody.put("email", email);
		loginBody.put("password", password);

		Response loginResponse = given().contentType(ContentType.JSON).body(loginBody).when().post("/users/login")
				.then().statusCode(200).extract().response();

		token = loginResponse.jsonPath().getString("token");
		System.out.println("Token after login: " + token);
	}

	// Test case 2
	@Test(priority = 3, dependsOnMethods = "testLoginUser")
	public void testGetUserProfile() {
		given().header("Authorization", "Bearer " + token).when().get("/users/me").then().statusCode(200).body("email",
				equalTo(email));
	}

	// Test Case 3
	@Test(priority = 4, dependsOnMethods = "testLoginUser")
	public void testUpdateUser() {
		Map<String, String> updateBody = new HashMap<>();
		updateBody.put("firstName", "Updated");
		updateBody.put("lastName", "Username");
		updateBody.put("email", "test2_" + UUID.randomUUID() + "@fake.com");
		updateBody.put("password", "myNewPassword");

		given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(updateBody).when()
				.patch("/users/me").then().statusCode(200).body("firstName", equalTo("Updated"))
				.body("lastName", equalTo("Username"));
	}

	@Test(priority = 5, dependsOnMethods = "testLoginUser")
	public void testAddContact() {
		Map<String, Object> contactPayload = new HashMap<>();
		contactPayload.put("firstName", "John");
		contactPayload.put("lastName", "Doe");
		contactPayload.put("birthdate", "1970-01-01");
		contactPayload.put("email", "jdoe@fake.com");
		contactPayload.put("phone", "8005555555");
		contactPayload.put("street1", "1 Main St.");
		contactPayload.put("street2", "Apartment A");
		contactPayload.put("city", "Anytown");
		contactPayload.put("stateProvince", "KS");
		contactPayload.put("postalCode", "12345");
		contactPayload.put("country", "USA");

		Response response = given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token)
				.body(contactPayload).when().post("/contacts").then().statusCode(201).body("firstName", equalTo("John"))
				.extract().response();

		contactId = response.jsonPath().getString("_id"); // extract contact ID
		System.out.println("Contact ID: " + contactId);
	}

	@Test(priority = 6, dependsOnMethods = "testAddContact")
	public void testGetAllContacts() {
		given().header("Authorization", "Bearer " + token).when().get("/contacts").then().statusCode(200)
				.statusLine(containsString("OK")).body("size()", greaterThan(0)); // Ensure at least one contact exists
	}

	@Test(priority = 7, dependsOnMethods = "testAddContact")
	public void testGetSpecificContact() {
		given().header("Authorization", "Bearer " + token).when().get("/contacts/" + contactId).then().statusCode(200)
				.statusLine(containsString("OK")).body("firstName", equalTo("John")).body("_id", equalTo(contactId));
	}

	@Test(priority = 8, dependsOnMethods = "testGetSpecificContact")
	public void testUpdateContact() {
		Map<String, Object> updatePayload = new HashMap<>();
		updatePayload.put("firstName", "Amy");
		updatePayload.put("lastName", "Miller");
		updatePayload.put("birthdate", "1992-02-02");
		updatePayload.put("email", "amiller@fake.com");
		updatePayload.put("phone", "8005554242");
		updatePayload.put("street1", "13 School St.");
		updatePayload.put("street2", "Apt. 5");
		updatePayload.put("city", "Washington");
		updatePayload.put("stateProvince", "QC");
		updatePayload.put("postalCode", "A1A1A1");
		updatePayload.put("country", "Canada");

		given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(updatePayload).when()
				.put("/contacts/" + contactId).then().statusCode(200).body("email", equalTo("amiller@fake.com"))
				.body("firstName", equalTo("Amy")).body("country", equalTo("Canada"));
	}

	@Test(priority = 9, dependsOnMethods = "testUpdateContact")
	public void testPartialUpdateContact() {
		Map<String, String> patchPayload = new HashMap<>();
		patchPayload.put("firstName", "Anna");

		given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).body(patchPayload).when()
				.patch("/contacts/" + contactId).then().statusCode(200).body("firstName", equalTo("Anna"));
	}

	@Test(priority = 10, dependsOnMethods = "testPartialUpdateContact")
	public void testLogoutUser() {
		given().header("Authorization", "Bearer " + token).when().post("/users/logout").then().statusCode(200)
				.statusLine(containsString("OK"));
	}

}
