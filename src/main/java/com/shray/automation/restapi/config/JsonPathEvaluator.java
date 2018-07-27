package com.shray.automation.restapi.config;

import com.jayway.jsonpath.JsonPath;

public class JsonPathEvaluator {
	
	
	public static String getJSONValue (String responseString, String jsonPath) {
		
		return JsonPath.parse(responseString).read(jsonPath).toString();
		
	}

}
