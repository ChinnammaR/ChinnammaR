package www.chinnamma.com.MoEngage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CommonMethods {
	
	public static int getRandomNumber(int min,int max)
	{
		  Random random = new Random();
		  int value= random.nextInt(max - 1 + 1) + 1;
		  return value;
	}

	public static void WriteToFile(String fileContent,String fname) throws IOException
	 {
		  
		  File file=new File(Config.pathFile+"/"+fname);
		  file.delete();
		  file.createNewFile();
	      FileWriter fileWriter = new FileWriter(file);
		  BufferedWriter bufferwrite = new BufferedWriter(fileWriter);
		  bufferwrite.write(fileContent);
		  bufferwrite.close();
	 }
	
	public static void appendToFile(String fileContent,String fname) throws IOException
	 {
		 String path=System.getProperty("user.dir");
		 File file=new File(Config.pathFile+"/"+fname);
		 FileWriter fr = new FileWriter(file, true);
		 fr.write(fileContent);
		 fr.close();
     }
	
}	
