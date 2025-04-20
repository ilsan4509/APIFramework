package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	// Method to create a reusable request specification with base URI, query parameters, and logging filters
	public RequestSpecification requestSpecification() throws IOException {
		
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			// Build a reusable request specification
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
			
			return req;	
		} 
		return req;	
	}
	
	 // Method to read global values from a properties file
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties"); // Open properties file
		prop.load(fis);
		return prop.getProperty(key);
	}
}
