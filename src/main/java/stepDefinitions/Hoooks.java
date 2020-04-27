package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hoooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		StepDefinition stdef = new StepDefinition();
		if (StepDefinition.place_id == null) {
			stdef.add_Place_Payload_with("Arun", "English", "India");
			stdef.user_calls_with_http_request("AddPlaceAPI", "POST");
			stdef.verify_place_id_created_maps_to_using("Arun", "GetPlaceAPI");
		}
	}

}
