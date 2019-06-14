package www.chinnamma.com.MoEngage;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HeatIndex {
	String apiPath=Config.path+"/current.json?key";
	String endpoint,cityName;
	HashMap hT=new HashMap<String,Double>();
	
	 @Test()
	  public void getClimateDetailsOfTheCities() throws IOException
	  {
	     Response resp;
		 String cities[]=new String[]{"Delhi","Chennai"};
		 int T;
		 long rh;
		 double heatIndex;
		    for(int i=0;i<cities.length;i++)
		    {
		    	cityName=cities[i];
		    	endpoint=apiPath+"="+Config.key+"&q="+cityName;
		    	resp=ReuestAndResponseHandler.getClimateDetails(endpoint);
		    	T=Math.round((resp.jsonPath().getFloat("current.temp_c")));
		    	rh=resp.jsonPath().getLong("current.humidity");
		    	heatIndex=-42.379+(2.04901523*T)+(10.14333127*rh)-(0.22475541*T*rh)-(6.83783*Math.pow(10,-3)*Math.pow(T,2))-(5.481717*Math.pow(10,-2)*Math.pow(rh,2))+(1.22874*Math.pow(10,-3)*Math.pow(T,2)*rh)+(8.5282*Math.pow(10,-4)*T*Math.pow(rh,2))-(1.99*Math.pow(10,-6)*Math.pow(T,2)*Math.pow(rh,2));
		        hT.put(cityName,heatIndex);
		    }
		
		    sortMap(hT);		   

	  } 
	 public static void sortMap(HashMap<String, Double> hT) throws IOException
	 {
		 List<Map.Entry<String, Double> > list = new LinkedList<Map.Entry<String, Double> >(hT.entrySet()); 
	      Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
	            public int compare(Map.Entry<String, Double> o1,  
	                               Map.Entry<String, Double> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
		 
	      HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
	        for (Map.Entry<String, Double> aa : list) { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        String fileContent="";
	        for (Map.Entry<String, Double> en : temp.entrySet()) { 
	        	fileContent="Heat index Of  "+ en.getKey() +"  is   "+  en.getValue()+"\n"; 
	        	System.out.println(fileContent);
	        	CommonMethods.appendToFile(fileContent,"StoreHeatIndexDetails.txt");
	        } 
	  
	       
     }
	 
	 }

