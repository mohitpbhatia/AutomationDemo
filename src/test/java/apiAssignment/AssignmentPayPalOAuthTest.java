package apiAssignment;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class AssignmentPayPalOAuthTest {

	static String access_token;
	static String clientID = "AaBQMDDTXuZk3GhPov95id2uZ7_aZRRYUmz3xlyg3NOZkqOHRtUIw5I9_PGt7WHu9kwoZV8By7MgDd1p";
	static String secret = "EEmmOZEBgad0wAkpqmsaG3GDkVkHCkqu2QGipPgGK0mQDJVhPN2QZiRO372G9pwkxWHVcyPBXcU0Mrcb";

	// Get Access Token
	@Test(priority = 1)
	public void getAccessToken() {

		Response response = given().auth().preemptive().basic(clientID, secret)
				.param("grant_type", "client_credentials").log().all()
				.post("https://api-m.sandbox.paypal.com/v1/oauth2/token");

		response.prettyPrint();
		System.out.println("Status code is  :" + response.statusCode());

		access_token = response.getBody().path("access_token");
		System.out.println("Access Token is  :" + access_token);

	}

	// Create Order (oauth2)

	@Test(priority = 2, dependsOnMethods = "getAccessToken")
	public void createPayPalOrder() {
		String payload = "{\r\n" + "  \"intent\": \"CAPTURE\",\r\n" + "  \"payment_source\": {\r\n"
				+ "    \"paypal\": {\r\n" + "      \"experience_context\": {\r\n"
				+ "        \"payment_method_preference\": \"IMMEDIATE_PAYMENT_REQUIRED\",\r\n"
				+ "        \"landing_page\": \"LOGIN\",\r\n" + "        \"shipping_preference\": \"GET_FROM_FILE\",\r\n"
				+ "        \"user_action\": \"PAY_NOW\",\r\n"
				+ "        \"return_url\": \"https://example.com/returnUrl\",\r\n"
				+ "        \"cancel_url\": \"https://example.com/cancelUrl\"\r\n" + "      }\r\n" + "    }\r\n"
				+ "  },\r\n" + "  \"purchase_units\": [\r\n" + "    {\r\n" + "      \"invoice_id\": \"90210\",\r\n"
				+ "      \"amount\": {\r\n" + "        \"currency_code\": \"USD\",\r\n"
				+ "        \"value\": \"230.00\",\r\n" + "        \"breakdown\": {\r\n"
				+ "          \"item_total\": {\r\n" + "            \"currency_code\": \"USD\",\r\n"
				+ "            \"value\": \"220.00\"\r\n" + "          },\r\n" + "          \"shipping\": {\r\n"
				+ "            \"currency_code\": \"USD\",\r\n" + "            \"value\": \"10.00\"\r\n"
				+ "          }\r\n" + "        }\r\n" + "      },\r\n" + "      \"items\": [\r\n" + "        {\r\n"
				+ "          \"name\": \"T-Shirt\",\r\n" + "          \"description\": \"Super Fresh Shirt\",\r\n"
				+ "          \"unit_amount\": {\r\n" + "            \"currency_code\": \"USD\",\r\n"
				+ "            \"value\": \"20.00\"\r\n" + "          },\r\n" + "          \"quantity\": \"1\",\r\n"
				+ "          \"category\": \"PHYSICAL_GOODS\",\r\n" + "          \"sku\": \"sku01\",\r\n"
				+ "          \"image_url\": \"https://example.com/static/images/items/1/tshirt_green.jpg\",\r\n"
				+ "          \"url\": \"https://example.com/url-to-the-item-being-purchased-1\",\r\n"
				+ "          \"upc\": {\r\n" + "            \"type\": \"UPC-A\",\r\n"
				+ "            \"code\": \"123456789012\"\r\n" + "          }\r\n" + "        },\r\n" + "        {\r\n"
				+ "          \"name\": \"Shoes\",\r\n" + "          \"description\": \"Running, Size 10.5\",\r\n"
				+ "          \"sku\": \"sku02\",\r\n" + "          \"unit_amount\": {\r\n"
				+ "            \"currency_code\": \"USD\",\r\n" + "            \"value\": \"100.00\"\r\n"
				+ "          },\r\n" + "          \"quantity\": \"2\",\r\n"
				+ "          \"category\": \"PHYSICAL_GOODS\",\r\n"
				+ "          \"image_url\": \"https://example.com/static/images/items/1/shoes_running.jpg\",\r\n"
				+ "          \"url\": \"https://example.com/url-to-the-item-being-purchased-2\",\r\n"
				+ "          \"upc\": {\r\n" + "            \"type\": \"UPC-A\",\r\n"
				+ "            \"code\": \"987654321012\"\r\n" + "          }\r\n" + "        }\r\n" + "      ]\r\n"
				+ "    }\r\n" + "  ]\r\n" + "}";

		Response response = given().contentType("application/json").auth().oauth2(access_token).body(payload).log()
				.all().post("https://api-m.sandbox.paypal.com/v2/checkout/orders");
		response.prettyPrint();
		System.out.println("Status code is" + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

	}

}
