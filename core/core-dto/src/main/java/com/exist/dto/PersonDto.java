package com.exist.dto;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class PersonDto implements Serializable{

	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String title;
	private AddressDto homeAddress;
	private AddressDto workAddress;
	private Set<ContactDto> contacts = new HashSet<>();
	private Date birthday;
	private double gwa;
	private Date dateHired;
	private boolean currentlyEmployed;
	private Set<RoleDto> roles = new HashSet<>();
    
	public PersonDto(){}
	
	public PersonDto(int id){
		this.id = id;
	}
	
	public PersonDto(String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, AddressDto workAddress, AddressDto homeAddress, boolean currentlyEmployed, Set<RoleDto> roles){
		this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.title = title;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
        this.birthday = birthday;
       	if(gwa >= 1 || gwa <= 5){
			this.gwa = gwa;
		}
        this.dateHired = dateHired;
        this.currentlyEmployed = currentlyEmployed;
        this.roles = new HashSet<>(roles);
	}
	
	public PersonDto(int id, String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, AddressDto workAddress, AddressDto homeAddress, boolean currentlyEmployed, Set<RoleDto> roles){
		this(firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress, homeAddress, currentlyEmployed, roles);
		this.id = id;
    }
	
	public PersonDto(int id, String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, AddressDto workAddress, AddressDto homeAddress, boolean currentlyEmployed, Set<RoleDto> roles, Set<ContactDto> contacts){
		this(id, firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress, homeAddress, currentlyEmployed, roles);
		this.contacts = new HashSet<>(contacts);
    }
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setHomeAddress(AddressDto address) {
		this.homeAddress = address;
	}
	
	public AddressDto getHomeAddress() {
		return homeAddress;
	}
	
	public void setWorkAddress(AddressDto address) {
		this.workAddress = address;
	}
	
	public AddressDto getWorkAddress() {
		return workAddress;
	}
	
	public void setContacts(Set contacts) {
		this.contacts = new HashSet<>(contacts);
	}
	
	public Set<ContactDto> getContacts() {
		return contacts;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setGwa(double gwa) {
		this.gwa = gwa;
	}
	
	public double getGwa() {
		return gwa;
	}
	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	
	public Date getDateHired() {
		return dateHired;
	}
	
	public void setCurrentlyEmployed(boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}
	
	public boolean isCurrentlyEmployed() {
		return currentlyEmployed;
	}
	
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public String getDateHiredStr(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateHiredStr = df.format(this.dateHired);
		return dateHiredStr;
	}
	
	public String getBirthdayStr(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String birthdayStr = df.format(this.birthday);
		return birthdayStr;
	}
	
	
    @Override
    public String toString(){
		if (suffix == null || suffix.isEmpty()){
			return lastName + ", " + firstName + " " + middleName;
		} else {
        	return lastName + " " + suffix + ", " + firstName + " " + middleName;
		}
    }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		PersonDto obj2 = (PersonDto)obj;
		if(this.id == obj2.getId()){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}
	
}
