package www.chinnamma.com.MoEngage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
public class BengluruTest {
	String apiPath=Config.path+"/forecast.json?key";
	String city;
	int days;
	
	
 @Test
  public void getTemperatureDetails() throws IOException 
	{
	  String city="Bengaluru",fileContent="";
	  int days=7;
	  double sum = 0,sd=0; 
	  String endpoint=apiPath+"="+Config.key+"&q="+city+"&days="+days;
	  Response resp=ReuestAndResponseHandler.getClimateDetails(endpoint);
	  //note the api gives the weather details for max 7 days including the present day hence i am ignoring the current days's temperature and here to find the Standard Deviation i am considering max temp
	  Date date = new Date();
	  String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	  for(int i=0;i<days;i++)
	 { 
		  if(!modifiedDate.equals(resp.jsonPath().getString("forecast.forecastday["+i+"].date")))
			  sum+=Double.parseDouble(resp.jsonPath().getString("forecast.forecastday["+i+"].day.maxtemp_c"));
	  }
	  //Since i am considering the temperature of 6 and its the max temperature  in celsius 
	  sd=sum/(days-1);
	  fileContent="Standard Deviation   "+sd;
	  //Writing  to the file 
	  CommonMethods.WriteToFile(fileContent,"StoreSD.txt");
	  //Printing to the console 
	  System.out.println(sd);
	  
}
 
 
}
