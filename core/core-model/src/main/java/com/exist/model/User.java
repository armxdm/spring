package com.exist.model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import static javax.persistence.GenerationType.AUTO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name="account")
public class User implements UserDetails{

	@Id
    @Column(name="id", nullable=false)
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
	@GeneratedValue(strategy = AUTO, generator = "user_id_seq")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
			
	@Enumerated(EnumType.STRING)
	@Column(name="user_role")
	private UserRole userRole;
	
	@Column(name = "enabled")
	private boolean enabled = true;
	
	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;
	
	public User(){}
	
	public User(String username, String password, UserRole userRole){
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
			
	public void setUsername(String username){
		this.username = username;
	}
			
	public String getUsername(){
		return username;
	}
			
	public void setPassword(String password){
		this.password = password;
	}
			
	public String getPassword(){
		return password;
	}
			
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.userRole.toString()));
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		User obj2 = (User)obj;
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