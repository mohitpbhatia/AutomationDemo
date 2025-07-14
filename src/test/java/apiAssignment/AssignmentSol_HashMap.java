package apiAssignment;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AssignmentSol_HashMap {
	@Test
	public void post() {

		Map<String, Object> data = new HashMap<>();
		data.put("year", 2019);
		data.put("price", 1849.99);
		data.put("CPUmodel", "Intel Core i9");
		data.put("Harddisksize", "1 TB");

		Map<String, Object> deviceInfo = new HashMap<>();
		deviceInfo.put("name", "Apple MacBook Pro 164");
		deviceInfo.put("data", data);

		Response response = RestAssured.given().contentType("application/json").body(deviceInfo).when()
				.post("https://api.restful-api.dev/objects").then().statusCode(200).extract().response();
		System.out.println(response.asPrettyString());

	}
}
