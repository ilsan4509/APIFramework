package stepDefinations;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class stepDefination {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		p.setLocation(l); // Set Location object in AddPlace object
		
		// Build a reusable request specification (재사용 가능한 요청 사양 생성)
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		// Build a reusable response specification (재사용 가능한 응답 사양 생성)
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		// Prepare and send the request using spec builder (사양 객체를 사용하여 요청 준비 및 전송)
		res = given().spec(req)
		.body(p); // Attach POJO as request body
		
	}
	
	@When("user calls {string} with post http request")
	public void user_calls_with_http_request(String string) {
		// Send POST request and apply response spec for validation
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
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is_2(String string, String string2) {
		
	}
}
