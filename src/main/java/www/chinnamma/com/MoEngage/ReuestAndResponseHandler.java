package www.chinnamma.com.MoEngage;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReuestAndResponseHandler {
	
	public static Response getClimateDetails(String endpoint)
	{
		Response resp=given()
				      .contentType(ContentType.JSON)
				      .when().log().all()
				      .get(endpoint);
				      resp.then().log().all();
	   return resp;
	}
	
	
	

}
