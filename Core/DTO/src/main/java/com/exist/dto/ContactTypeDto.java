package com.exist.dto;

public enum ContactTypeDto{
	EMAIL_ADDRESS("Email Address"), MOBILE_NUMBER("Mobile Number"), LANDLINE_NUMBER("Landline Number");
	
	private String name;
	
	ContactTypeDto(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}