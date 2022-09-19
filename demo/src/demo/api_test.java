package demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;

import net.bytebuddy.jar.asm.commons.Method;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured;
import static io.restassured.specification.RequestSpecification;


class api_test {
	
	@BeforeTest
	public void createBooking() throws Exception {
		logger.info("------Test Case 1 Create Booking Positive Scenarios---------");
		//fetch Base URL 
		 RestAssured.baseURI="https://restful-booker.herokuapp.com";
		 //Request object
		 RequestSpecification httpRequest=RestAssured.given();
		 
		 //pass the request body
		 JSONObject requestParams=new JSONObject();
		 requestParams.put("firstname","Jim");
		 requestParams.put("lastname","Brown");
		 requestParams.put("totalprice",111);
		 requestParams.put("depositpaid",true);
		 requestParams.put("checkin","2018-01-01");
		 requestParams.put("checkout","2019-01-01");
		 requestParams.put("additionalneeds","Breakfast");
		
		 //to pass the header
		 httpRequest.header("Content-Type","application/json");
		 httpRequest.header("Accept","application/json");
		 
		 httpRequest.body(requestParams.toJSONString());
		 Response response=httpRequest.request(Method.POST,"/booking");
		 Thread.sleep(500);
	}
	@Test
	public void checkresponse(){
		 //print response in console
		 String  responsebody=response.getBody().asString();
		 System.out.println("Response Body is :"+responsebody);
		 //To find the id of response
		 JsonPath path=response.jsonPath();
		 String id=path.get("id");
		System.out.println("The Id of booking is:" +id);
		 
		 
		 //status code validation
		 int statusCode =response.getStatusCode();
		 System.out.println("Status Code is"+statusCode);
		 Assert.assertEquals(statusCode,200);
		 
		 //status line validation
		 String statusline=response.getStatusLine();
		 System.out.println("Status Line is :"+statusline);
		 Assert.assertEquals(statusline,"HTTP/1.1 200 OK");
	}
	
	@Test
	//to verify response body
	public void checkResponseBody(){
		JsonPath jsonpath=response.jsonPath();
		Assert.assertEquals(jsonpath.get("firstname"),"Jim");
		Assert.assertEquals(jsonpath.get("lastname"),"Brown");
		Assert.assertEquals(jsonpath.get("totalprice"),111);
		Assert.assertEquals(jsonpath.get("depositpaid"),true);
		Assert.assertEquals(jsonpath.get("additionalneeds"),"Breakfast");
		
		// to print and validate booking date; nested json
		JsonArray json=new JSONArray(jsonpath);
		for(int i=0;i<=json.length();i++) {
			String bookingDate=json.get("bookingDate").toString();// convert to string
			logger.log(LogStatus.INFO"Booking Dates are:"+bookingDate);// print dates
			
			
		}
		 
	}
}

	

