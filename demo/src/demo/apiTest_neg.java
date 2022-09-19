package demo;

import org.junit.Assert;
import org.testng.annotations.Test;

import net.bytebuddy.jar.asm.commons.Method;

public class apiTest_neg {
  @Test
  public void NegativeScenarios() {
	  logger.info("------Test Case 2 Create Booking Negative Scenarios---------");
		//fetch Base URL 
		 RestAssured.baseURI="https://restful-booker.herokuapp.com";
		 //Request object
		 RequestSpecification httpRequest=RestAssured.given();
		 
		 //pass the request body with invalid data type
		 JSONObject requestParams=new JSONObject();
		 requestParams.put("firstname","Jim");
		 requestParams.put("lastname",Brown);//passing as not character 
		 requestParams.put("totalprice","111");//passing a string price
		 requestParams.put("depositpaid",true);
		 requestParams.put("checkin","2018-01-01");
		 requestParams.put("checkout","01/01/2019");//passing date in DD/MM/YYYY format
		 requestParams.put("additionalneeds","Breakfast");
		 
		 httpRequest.body(requestParams.toJSONString());
		 Response response=httpRequest.request(Method.POST,"/booking");//call the endpoint
		 Thread.sleep(500);
		 
		 //status code validation
		 int statusCode =response.getStatusCode();
		 System.out.println("Status Code is"+statusCode);
		 Assert.assertEquals(statusCode,404);    //for an invalid type it should displays 400 status code
		 
		//print response in console
		 String  responsebody=response.getBody().asString();
		 System.out.println("Response Body is :"+responsebody);
  }
}
