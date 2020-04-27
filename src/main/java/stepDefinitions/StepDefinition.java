package stepDefinitions;

import org.junit.Assert;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqspec;
	ResponseSpecification resspec;
	Response response;
	public static String place_id;
	JsonPath json;
	TestData testData = new TestData();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		reqspec = given().spec(requestSpec()).body(testData.addPlacePayLoad(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		// resspec =new
		// ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) 
			response = reqspec.when().post(resourceAPI.getResource());	
		 // .then().spec(resspec).extract().response();
		else if (method.equalsIgnoreCase("GET"))
			response = reqspec.when().get(resourceAPI.getResource());	
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		Assert.assertEquals(getJsonPath(response,keyValue), expectedValue);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    place_id= getJsonPath(response,"place_id");
		reqspec = given().spec(requestSpec())
				.queryParam("place_id",place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName= getJsonPath(response, "name");
		Assert.assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		reqspec=given().spec(requestSpec()).body(testData.deletePlacePayload(place_id));
	}

}
