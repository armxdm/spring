package com.exist.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import com.exist.dto.AddressDto;

@Embeddable
public class Address {
	
	private int number;
	private String street;
	private String barangay;
	private String city;
	private int zipCode;
	
	public Address(){
	}
	
	public Address(int number, String street, String barangay, String city, int zipCode){
		this.number = number;
		this.street = street;
		this.barangay = barangay;
		this.city = city;
		this.zipCode = zipCode;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}
	
	public String getBarangay() {
		return barangay;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public AddressDto toDto(){
		return new AddressDto(this.number, this.street, this.barangay, this.city, this.zipCode);
	}
	
	@Override
	public String toString(){
		return number + " " + street + ", " + barangay + ", " + city + ", " + zipCode;
	}
	
}
