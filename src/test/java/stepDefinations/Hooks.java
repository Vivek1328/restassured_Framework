package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScrenario() throws IOException {
		
		
		
		Payload pl = new Payload();
		if(Payload.placeId==null) {
		pl.add_place_payload("Vivek", "English", "Ontario or toranto");
		pl.user_call_with_http_request("AddPlaceApi", "Post");
		pl.verify_place_id_has_same_as_using("Vivek", "GetPlaceApi");
		
		}
	}
	
}
