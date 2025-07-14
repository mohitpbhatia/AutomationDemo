package apiAssignment;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestfulBookerTest {
	String token;
	int bookingId;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Map<String, String> creds = new HashMap<>();
		creds.put("username", "admin");
		creds.put("password", "password123");

		Response response = RestAssured.given().contentType("application/json").body(creds).when().post("/auth").then()
				.statusCode(200).extract().response();
		token = response.jsonPath().getString("token");
		System.out.println("Token :" + token);

	}

	@Test
	public void createBooking() {
		Map<String, Object> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2023-10-01");
		bookingDates.put("checkout", "2023-10-10");
		Map<String, Object> booking = new HashMap<>();
		booking.put("firstname", "John");
		booking.put("lastname", "Doe");
		booking.put("totalprice", 120);
		booking.put("depositpaid", true);
		booking.put("bookingdates", bookingDates);
		booking.put("additionalneeds", "Breakfast");

		Response response = RestAssured.given().contentType("application/json").body(booking).when().post("/booking")
				.then().statusCode(200).extract().response();
		bookingId = response.jsonPath().getInt("bookingid");

	}

}
