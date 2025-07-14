package apiAssignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AssignmentSol_1 {

	// 1.Find pet by its status

	@Test(priority = 1)
	public void GetPetbyStatus() {
		Response response = RestAssured.given().header("accept", "application/json").when()
				.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		Assert.assertEquals(response.getStatusCode(), 200);
		// System.out.println("Response is :" + response.asPrettyString());
	}

	// 2.Add new Pet to the store

	@Test(priority = 2)
	public void AddNewPet() {
		Response response = RestAssured.given()
				.body("{\r\n" + "\"id\": 111,\r\n" + "\"category\": {\r\n" + "\"id\": 0,\r\n"
						+ "\"name\": \"string\"\r\n" + "},\r\n" + "\"name\": \"doggie\",\r\n" + "\"photoUrls\": [\r\n"
						+ "\"string\"\r\n" + "],\r\n" + "\"tags\": [\r\n" + "{\r\n" + "\"id\": 0,\r\n"
						+ "\"name\": \"string\"\r\n" + "}\r\n" + "],\r\n" + "\"status\": \"available\"\r\n" + "}")
				.header("accept", "application/json").header("Content-Type", "application/json").when()
				.post("https://petstore.swagger.io/v2/pet");

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	// 3.For the same generated id find the pet

	@Test(priority = 3)
	public void GetPetByID() {
		Response response = RestAssured.given().header("accept", "application/json").when()
				.get("https://petstore.swagger.io/v2/pet/111");
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	// 4. Update an existing pet

	@Test(priority = 4)
	public void UpdatePet() {
		String jsonBody = "{\r\n" + "\"id\": 9223372036854740000,\r\n" + "\"category\": {\r\n" + "\"id\": 0,\r\n"
				+ "\"name\": \"string\"\r\n" + "},\r\n" + "\"name\": \"doggie\",\r\n" + "\"photoUrls\": [\r\n"
				+ "\"string\"\r\n" + "],\r\n" + "\"tags\": [\r\n" + "{\r\n" + "\"id\": 0,\r\n"
				+ "\"name\": \"string\"\r\n" + "}\r\n" + "],\r\n" + "\"status\": \"available\"\r\n" + "}";
		Response response = RestAssured.given().body(jsonBody).header("accept", "application/json")
				.header("Content-Type", "application/json").when().put("https://petstore.swagger.io/v2/pet");
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	// 5.Delete existing pet with same id

	@Test(priority = 5)
	public void DeletePet() {
		Response response = RestAssured.given().header("accept", "application/json").when()
				.delete("https://petstore.swagger.io/v2/pet/111");
		// System.out.println("Response is :" + response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
