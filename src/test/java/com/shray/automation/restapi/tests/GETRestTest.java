package com.shray.automation.restapi.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.shray.automation.restapi.base.TestBase;
import com.shray.automation.restapi.client.RestClient;
import com.shray.automation.restapi.config.JsonPathEvaluator;
import com.shray.automation.restapi.config.ResponseCodes;

public class GETRestTest extends TestBase{
	
	public String appURL;
	public String getServiceURL;
	public String getURI;
	public TestBase testBase;
	public RestClient restClient;
	public CloseableHttpResponse getResponse;
	public int statusCode;
	
	
	@BeforeTest
	public void initialize() {
		
		testBase = new TestBase();
		restClient = new RestClient();
		appURL = prop.getProperty("appURL");
		getServiceURL = prop.getProperty("getSecondUser");		
		getURI=appURL+getServiceURL;
		
	}
	
	
	@Test
	public void getAPITestVerfiyUserDetails() throws ClientProtocolException, IOException {
		
		
		String actualUserID;
		String actualFirstName;
		String actualLastName;
		String actualAvatar;
		String userId = "2";
		
		//Get the response for the GET request 
		getResponse = restClient.GetRequest(getURI);
		
		
		//Fetch the status code
		statusCode = getResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, ResponseCodes.RESPONSE_STATUS_CODE_200);
		
		//Fetch the response
		String getResponseString = EntityUtils.toString(getResponse.getEntity(), "UTF-8");
		
		
		//Convert the response string into JSON string    -- This is not required as of now
		//JSONObject responseJSON = new JSONObject(getResponseString);
		//System.out.println(responseJSON);
		
		//
		
		actualUserID = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.id");
		Assert.assertEquals(actualUserID, userId);
	
		actualFirstName = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.first_name");
		Assert.assertEquals(actualFirstName, "Janet");
		
		actualLastName = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.last_name");
		Assert.assertEquals(actualLastName, "Weaver");
		
		actualAvatar = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.avatar");
		Assert.assertEquals(actualAvatar, "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		
		
		//Getting all the Headers from the response
		
		Header[] allHeaders = getResponse.getAllHeaders();
		HashMap<String, String> responseHeaders = new HashMap<String, String>();
		
		for (Header header : allHeaders) {
			responseHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Here are all the header in the GET response:" + responseHeaders);
						
		
	}
	
	
	@Test(priority=1)
	public void getAPITestVerfiyUserDetailsWithHeaders() throws ClientProtocolException, IOException {
		
		
		String actualUserID;
		String actualFirstName;
		String actualLastName;
		String actualAvatar;
		String userId = "2";
		
		
		//Creating the Header for the GET request
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test213");
//		headerMap.put("Auth Token", "12345");
		
		//Get the response for the GET request 
		getResponse = restClient.GetRequestWithHeaders(getURI, headerMap);
		
		
		//Fetch the status code
		statusCode = getResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, ResponseCodes.RESPONSE_STATUS_CODE_200);
		
		//Fetch the response
		String getResponseString = EntityUtils.toString(getResponse.getEntity(), "UTF-8");
		
		
		//Convert the response string into JSON string    -- This is not required as of now
		//JSONObject responseJSON = new JSONObject(getResponseString);
		//System.out.println(responseJSON);
		
		//
		
		actualUserID = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.id");
		Assert.assertEquals(actualUserID, userId);
	
		actualFirstName = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.first_name");
		Assert.assertEquals(actualFirstName, "Janet");
		
		actualLastName = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.last_name");
		Assert.assertEquals(actualLastName, "Weaver");
		
		actualAvatar = JsonPathEvaluator.getJSONValue(getResponseString, "$.data.avatar");
		Assert.assertEquals(actualAvatar, "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		
		
		Header[] allHeaders = getResponse.getAllHeaders();
		HashMap<String, String> responseHeaders = new HashMap<String, String>();
		
		for (Header header : allHeaders) {
			responseHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Here are all the header in the GET response:" + responseHeaders);
						
		
	}
	
	

}
