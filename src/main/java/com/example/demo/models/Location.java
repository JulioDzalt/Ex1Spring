package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Location {
	private String gps;
	private String codigoPostal;
	private String delegacion;
	private String estado;
}
