package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination {
	@Given("Add Place Payload")
	public void add_Place_Payload() {
		
	}
	
	@When("user calls {string} with post http request")
	public void user_calls_with_http_request(String string) {
		
	}
	
	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is_1(String string, String string2) {
	  
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is_2(String string, String string2) {
	  
	}
}
