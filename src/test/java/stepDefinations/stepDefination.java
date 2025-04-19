package stepDefinations;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")  
	public void add_Place_Payload(String name, String language, String address) throws IOException {
		// Prepare and send the request using spec builder
		// The payload is created dynamically based on the name, language, and address provided from the feature file
		res = given().spec(requestSpecification())
		.body(data.addPlacePayLoad(name, language, address)); // Attach POJO as request body
		
	}
	
	@When("user calls {string} with Post http request")
	public void user_calls_with_http_request(String string) {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("/maps/api/place/add/json")
			.then().spec(resspec).extract().response();
	}
	
	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is_1(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);
	}
	
//	@Then("{string} in response body is {string}")
//	public void in_response_body_is_2(String string, String string2) {
//		
//	}
}
