package com.example.demo.services;

import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Atm {

	final String uri = "https://www.banamex.com/localizador/jsonP/json5.json";
	
	public JSONObject getNearAtms(String estado) {
		RestTemplate restTemplate =  new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		String json = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObjectResponse = new JSONObject();
	    try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			JSONObject jsonObject2 = (JSONObject) jsonObject.get("Servicios");
		    JSONObject jsonObject3 = (JSONObject) jsonObject2.get("100");
		    JSONObject jsonObject4 = (JSONObject) jsonObject3.get("0");
		    

		    for (Iterator iterator = jsonObject4.keySet().iterator(); iterator.hasNext(); ) { //Get keys
	            String key = (String) iterator.next();
	            //System.out.println(key);
	            List<String> data = (List<String>) jsonObject4.get(key);
	            //System.out.println(data.get(17));
	            if(data.get(17).equalsIgnoreCase(estado)) {
	            	jsonObjectResponse.put(key, data);

	            }
	          }
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return jsonObjectResponse;
	   
	}
	public JSONObject getAllAtms() {
		RestTemplate restTemplate =  new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		String json = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
		JSONObject jsonObject = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObjectResponse = new JSONObject();
	    try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			JSONObject jsonObject2 = (JSONObject) jsonObject.get("Servicios");
		    JSONObject jsonObject3 = (JSONObject) jsonObject2.get("100");
		    JSONObject jsonObject4 = (JSONObject) jsonObject3.get("0");
		    

		    for (Iterator iterator = jsonObject4.keySet().iterator(); iterator.hasNext(); ) { //Get keys
	            String key = (String) iterator.next();
	            System.out.println(key);
	            List<String> data = (List<String>) jsonObject4.get(key);
	            System.out.println(data.get(17));
	            jsonObjectResponse.put(key, data);
	          }
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return jsonObjectResponse;
	   
	}
}
