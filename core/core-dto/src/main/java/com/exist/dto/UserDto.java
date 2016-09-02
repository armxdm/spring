package com.exist.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class UserDto{

	private int id;
	
	private String username;

	private String password;

	private boolean enabled;

	private UserRoleDto userRoleDto;
	
	private Date dateCreated;

	public UserDto() {
	}

	public UserDto(String username, String password, UserRoleDto userRoleDto) {
		this.username = username;
		this.password = password;
		//this.name = name;
		this.userRoleDto = userRoleDto;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRoleDto getUserRoleDto() {
		return this.userRoleDto;
	}

	public void setUserRoleDto(UserRoleDto userRoleDto) {
		this.userRoleDto = userRoleDto;
	}

}