package apiAssignment;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AssignmentSol_2b {

	public static void main(String[] args) {

		// HashMap for 'data' block
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("year", 2019);
		dataMap.put("price", 1849.99);
		dataMap.put("CPUmodel", "Intel Core i9");
		dataMap.put("Harddisksize", "1 TB");

		// POJO for root object
		AssignmentSol_2a obj = new AssignmentSol_2a();
		obj.setName("Apple MacBook Pro 16");
		obj.setData(dataMap);

		// Base URI
		RestAssured.baseURI = "https://api.restful-api.dev";

		// POST request
		Response response = given().header("Content-Type", "application/json").body(obj).when().post("/objects").then()
				.statusCode(200).extract().response();
		System.out.println(response.asPrettyString());

	}
}
