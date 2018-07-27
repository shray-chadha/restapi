package com.shray.automation.restapi.client;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.shray.automation.restapi.base.TestBase;

public class RestClient extends TestBase{
	
	
	
	//** HTTP GET request method without headers  **//
	
	public CloseableHttpResponse GetRequest(String getURI) throws ClientProtocolException, IOException {
		
		//Creating a new HTTP Client object
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Creating a new HTTP GET object
		HttpGet httpGet = new HttpGet(getURI);
		
		//Executing the GET request and storing the response in the CloseableHTTPResponse object
		CloseableHttpResponse getResponse = httpClient.execute(httpGet);
		
		//Returning the response back to the method
		
		return getResponse;
				
	}
	
	
	//** HTTP GET request method headers  **//
	
	public CloseableHttpResponse GetRequestWithHeaders(String getURI, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		//Creating a new HTTP Client object
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Creating a new HTTP GET object
		HttpGet httpGet = new HttpGet(getURI);
		
		//Adding the headers to the GET request
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		//Executing the GET request and storing the response in the CloseableHTTPResponse object
		CloseableHttpResponse getResponse = httpClient.execute(httpGet);
		
		return getResponse;
	
		
	}
	
	
	
	//** HTTP POST request with headers ** //
	
	public CloseableHttpResponse PostRequestWithHeaders (String postURI, String payloadEntityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		//Creating a new HTTP Client object
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Creating a new HTTP POST object
		HttpPost httpPost = new HttpPost(postURI);
		
		
		//Adding Payload to the HTTP POST request 
		httpPost.setEntity(new StringEntity(payloadEntityString));
		
		//Adding Headers to the POST request
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse postResponse = httpClient.execute(httpPost);
		
		return postResponse;		
		
	}
	
	
	
	

}
