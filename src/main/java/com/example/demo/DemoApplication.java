package com.example.demo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.models.Location;
import com.example.demo.services.Atm;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	@Autowired
	private Atm atm;
	
	public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/")
		public String hello(@RequestParam(value = "name", defaultValue = "") String name) {
		return String.format("It works %s! :)", name);
	}

	@PostMapping("/location")
	public ResponseEntity<Location> printData(@Validated @RequestBody Location location) {
		System.out.println("Printing the user data:"+location);
		return new ResponseEntity<Location>(location, HttpStatus.OK);
	}
	
	@PostMapping("/location/atm/near")
	public ResponseEntity<String> atmNear(@Validated @RequestBody Location location) {
		System.out.println("Printing the user data:"+location);
		JSONObject json = atm.getNearAtms(location.getEstado());
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/location/atm/all")
	public ResponseEntity<String> atms() {
		JSONObject json = atm.getAllAtms();
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK); 
	}

}
            