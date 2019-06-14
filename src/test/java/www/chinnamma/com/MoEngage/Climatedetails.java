package www.chinnamma.com.MoEngage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class Climatedetails {
	String apiPath1=Config.path+"/search.json?key";
	String apiPath2=Config.path+"/current.json?key";
	String city,endpoint,cityName;
	
  @Test
  public void getDelhiClimateDetails() 
  {
	  city="Delhi";
	  endpoint=apiPath1+"="+Config.key+"&q="+city;
	  Response resp=ReuestAndResponseHandler.getClimateDetails(endpoint);
	  int size = resp.jsonPath().getList("id").size();
	  System.out.println(size);
      int value=CommonMethods.getRandomNumber(1,size);
	  cityName=resp.jsonPath().getString("["+value+"].name");
	  System.out.println(cityName);
	  	  
	 }
  @Test(dependsOnMethods="getDelhiClimateDetails")
  public void getClimateDetailsOfTheCity() throws IOException
  {
	  endpoint=apiPath2+"="+Config.key+"&q="+cityName;
	  Response resp=ReuestAndResponseHandler.getClimateDetails(endpoint);
	  //Writning to the file
	  CommonMethods.WriteToFile(resp.asString(),"StoreClimateDetails.txt");
	  
  }
  
  
}
