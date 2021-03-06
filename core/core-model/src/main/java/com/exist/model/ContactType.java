package com.exist.model;

public enum ContactType {
    EMAIL_ADDRESS("Email Address"), MOBILE_NUMBER("Mobile Number"), LANDLINE_NUMBER("Landline Number");

    private String name;

    ContactType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}