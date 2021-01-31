package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Add_Location;
import pojo.Location;
import resource.ApiResources;
import resource.TestData;
import resource.Utils;




public class Payload {
	
	RequestSpecification ret;
	static ResponseSpecification ra;
	Response responce;
	TestData add = new TestData();
    Utils util = new Utils();
    static String placeId;
	
	
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
	    
	    	
        
        
		
	    ra=new ResponseSpecBuilder().expectStatusCode(200).build();
	    ret = given().log().all().spec(util.reqBuilder()).body(add.addLocation(name,language,address));
	}


	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String reqUrl, String requestType) {
	    
		System.out.println(requestType);
		ApiResources resource =	ApiResources.valueOf(reqUrl);
		if(requestType.contains("Post")) {
		responce =	ret.when().post(resource.getApiResource()).then().spec(ra).log().all().extract().response();
		}
		else if(requestType.contains("Delete")) {
			responce =	ret.when().delete(resource.getApiResource());
		}
		else if(requestType.contains("Get")) {
			responce= ret.when().get(resource.getApiResource());
		}
		else {
			System.out.println(">>>>>>>   Request Type not Correct  <<<<<<<<<<<<<<<<<<<<");
		}
	}
	/*@Then("Api Status Code is {int}")
	public void api_status_code_is(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(int1);
		assertEquals(responce.getStatusCode(),200);
	   // responce.getStatusCode();
	}*/
	@Then("Api {string} in responce body is {string}")
	public void api_in_responce_body_is(String keyValue, String Value) {
	    
	 // String res=responce.asString();
	  //System.out.println(res);
	//  JsonPath js= new JsonPath(res);
	 // System.out.println(js.get("scope"));
	  assertEquals(Utils.getJsonResponce(responce, keyValue),Value);
		//Utils.getJsonResponce(responce, keyValue);
	  
	}
	
	@Then("Verify place id has same as {string} using {string}")
	public void verify_place_id_has_same_as_using(String name, String baseUrl) throws IOException {
		placeId=Utils.getJsonResponce(responce, "place_id");
		System.out.println(placeId);
  ret=given().spec(util.reqBuilder()).queryParam("place_id", placeId);
  user_call_with_http_request(baseUrl, "Get");
  String name1=Utils.getJsonResponce(responce, "name");
  assertEquals(name1,name);
}
	
	 @Given("^Delete place payload$")
	   public void delete_place_payload() throws Throwable {
		 ret = given().log().all().spec(util.reqBuilder()).body(add.deletePlace(placeId));
		 ///System.out.println(add.deletePlace(placeId));
	    }

	    //@When("^user call \"([^\"]*)\" with \"([^\"]*)\" http request$")
	   // public void user_call_something_with_something_http_request(String req_url, String strArg2) throws Throwable {
	   // 	ret.when().post(req_url);
	    //}

	    //@Then("^Api \"([^\"]*)\" in responce body is \"([^\"]*)\"$")
	    //public void api_something_in_responce_body_is_something(String strArg1, String strArg2) throws Throwable {
	    //  System.out.println("Just checking");  
	   // }
}