package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	//Static keyword is used so that "res" does not become null
	public static RequestSpecification res;
	
	public RequestSpecification reqBuilder() throws IOException {
		
		
		if(res==null)
		{
			PrintStream	 log = new PrintStream(new FileOutputStream("logging.txt"));
	
		 res=new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl")).addQueryParam("key","qaclick123")
	            .addHeader("Content-Type", "application/json").addFilter(RequestLoggingFilter.logRequestTo(log)).
	            addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		
		return res;
	
		
		}
		
		return res;
		}
	public static String getGlobalProperties(String Value) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\vivek\\eclipse-workspace\\new\\E2E_Framework_Restassured\\src\\test\\java\\resource\\Global.properties");
		prop.load(file);
		return prop.getProperty(Value);
		 
		
	}
	
	public static String getJsonResponce(Response responce, String value) {
		String res = responce.asString();
		JsonPath js = new JsonPath(res);
		return js.get(value).toString();
	
		
	}
	
}
