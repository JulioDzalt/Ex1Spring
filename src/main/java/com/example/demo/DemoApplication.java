package com.example.demo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/hello")
		public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s! desde vscode xd", name);
	}

	@PostMapping("/location/atm")
	public ResponseEntity<Location> printData(@RequestBody Location location) {
		System.out.println("Printing the user data:"+location);
		return new ResponseEntity<Location>(location, HttpStatus.OK);
	}
	
	@GetMapping("/atm")
	public String atms() {
		final String uri = "https://www.banamex.com/localizador/jsonP/json5.json";
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
	            if(data.get(17).equalsIgnoreCase("Guerrero")) {
	            	jsonObjectResponse.put(key, data);

	            }
//	            Iterator<String> jIterObj = data.iterator();
//		        while (jIterObj.hasNext()) {
//		        	String aux = jIterObj.next();
//		            System.out.println(aux);
//		        }
	          }
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return jsonObjectResponse.toString();
	   
	}

}
            