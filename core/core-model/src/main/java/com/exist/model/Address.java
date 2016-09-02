package com.exist.model;

import com.exist.dto.AddressDto;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private int number;
    private String street;
    private String barangay;
    private String city;
    private int zipCode;

    public Address() {
    }

    public Address(int number, String street, String barangay, String city, int zipCode) {
        this.number = number;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public AddressDto toDto() {
        return new AddressDto(this.number, this.street, this.barangay, this.city, this.zipCode);
    }

    @Override
    public String toString() {
        return number + " " + street + ", " + barangay + ", " + city + ", " + zipCode;
    }

}
