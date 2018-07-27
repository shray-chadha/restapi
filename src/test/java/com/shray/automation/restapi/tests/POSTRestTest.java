package com.shray.automation.restapi.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shray.automation.restapi.base.TestBase;
import com.shray.automation.restapi.client.RestClient;
import com.shray.automation.restapi.config.ResponseCodes;
import com.shray.automation.restapi.data.ActualNewUser;
import com.shray.automation.restapi.data.NewUser;

public class POSTRestTest extends TestBase{
	
	TestBase testBase;
	String postURI;
	RestClient restClient;
	String name;
	String job;
	String postJSONPayload;
	CloseableHttpResponse postResponse;
	
	int status;
	
	
	@BeforeMethod
	public void setup() {
		
		testBase = new TestBase();
		restClient = new RestClient();
		postURI = prop.getProperty("appURL")+prop.getProperty("addUser");
		name = prop.getProperty("newUserName");
		job = prop.getProperty("newUserJob");
		
	}
	
	
	@Test
	public void PostRestTestWithHeaders() throws ClientProtocolException, IOException {
		
		
		//Creating a new POJO object 
		NewUser expectedUser = new NewUser(name, job);
		
		//Converting this POJO to the JSON string to send as a payload in the POST resquest
		ObjectMapper mapper = new ObjectMapper();
		String postJSONPayloadString = mapper.writeValueAsString(expectedUser);		
		
		//Creating Header for the POST request
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Getting the POST response
		postResponse = restClient.PostRequestWithHeaders(postURI, postJSONPayloadString, headerMap);
		
		//Getting the status code from the POST response
		status = postResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, ResponseCodes.RESPONSE_STATUS_CODE_201);
		
		//Getting the response Headers from the POST repsonse
		Header[] allHeaders = postResponse.getAllHeaders();
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		
		for (Header header : allHeaders) {
			responseHeader.put(header.getName(), header.getValue());			
		}
		
		System.out.println("Here are all the headers in the response"+ responseHeader);
		
		//Getting the JSON response string from the POST response
		String jsonPostRepsponse = EntityUtils.toString(postResponse.getEntity(), "UTF-8");
		System.out.println("Resposne is "+ jsonPostRepsponse);
		
		
		//Converting this JSON response string to the JAVA object - i.e. performing Unmarshelling
		ActualNewUser actualUser = mapper.readValue(jsonPostRepsponse, ActualNewUser.class);
		
		Assert.assertEquals(actualUser.getName(), expectedUser.getName());
		Assert.assertEquals(actualUser.getJob(), expectedUser.getJob());
		
		System.out.println("The id of the user created is: "+actualUser.getId());
		System.out.println("The user is created at:"+actualUser.getCreatedAt());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
