package com.exist.model;

import com.exist.dto.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import static javax.persistence.GenerationType.AUTO;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(region="Person", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="person")
public class Person{

	@Id
    @Column(name="id", nullable=false)
	@SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq")
	@GeneratedValue(strategy = AUTO, generator = "person_id_seq")
	private int id;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="middle_name", nullable=false)
	private String middleName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="suffix")
	private String suffix;
	
	@Column(name="title")
	private String title;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="number", column=@Column(name="house_number")),
		@AttributeOverride(name="street",column=@Column(name="home_street")),
		@AttributeOverride(name="barangay", column=@Column(name="home_barangay")),
		@AttributeOverride(name="city", column=@Column(name="home_city")),
		@AttributeOverride(name="zipCode", column=@Column(name="home_zip_code"))
  	})
	private Address homeAddress;
	
	@Embedded
	 @AttributeOverrides({
		@AttributeOverride(name="number", column=@Column(name="work_bldg_number")),
    	@AttributeOverride(name="street",column=@Column(name="work_street")),
		@AttributeOverride(name="barangay", column=@Column(name="work_barangay")),
		@AttributeOverride(name="city", column=@Column(name="work_city")),
		@AttributeOverride(name="zipCode", column=@Column(name="work_zip_code"))
  	})
	private Address workAddress;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Contact> contacts = new HashSet<>();
	
	@Column(name="birthday", nullable=false)
	private Date birthday;
	
	@Column(name="gwa")
	private double gwa;
	
	@Column(name="date_hired")
	private Date dateHired;
	
	@Column(name="is_currently_employed")
	private boolean currentlyEmployed;
			
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "person_role", joinColumns = {
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
	private Set<Role> roles;
    
	public Person(){
	}
	
	public Person(int id){
		this.id = id;
	}
	
	public Person(int id, String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, Address workAddress, Address homeAddress, boolean currentlyEmployed, Set<Role> roles){
		this(firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress, homeAddress, currentlyEmployed, roles);
		this.id = id;
    }
	
	public Person(int id, String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, Address workAddress, Address homeAddress, boolean currentlyEmployed, Set<Role> roles, Set<Contact> contacts){
		this(firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress, homeAddress, currentlyEmployed, roles);
		this.id = id;
		this.contacts = new HashSet<>(contacts);
    }
	
    public Person(String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, Address workAddress, Address homeAddress, boolean currentlyEmployed, Set<Role> roles){
        setData(firstName, middleName, lastName, suffix, title, birthday, gwa, dateHired, workAddress, homeAddress, currentlyEmployed, roles);
    }
	
	public void setData(String firstName, String middleName, String lastName, String suffix, String title, Date birthday, double gwa, 
				  Date dateHired, Address workAddress, Address homeAddress, boolean currentlyEmployed, Set<Role> roles){
		this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.title = title;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
        this.birthday = birthday;
        this.gwa = gwa;
        this.dateHired = dateHired;
        this.currentlyEmployed = currentlyEmployed;
        this.roles = new HashSet<>(roles);
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
	
	public void setHomeAddress(Address address) {
		this.homeAddress = address;
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	
	public void setWorkAddress(Address address) {
		this.workAddress = address;
	}
	
	public Address getWorkAddress() {
		return workAddress;
	}
	
	public void setContacts(Set contacts) {
		this.contacts = new HashSet<>(contacts);
	}
	
	public Set<Contact> getContacts() {
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
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Role> getRoles() {
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
	
	public PersonDto toDto(){
		return new PersonDto(this.id, this.firstName, this.middleName, this.lastName, this.suffix, this.title, this.birthday, this.gwa, 
				  this.dateHired, this.workAddress.toDto(), this.homeAddress.toDto(), this.currentlyEmployed, rolesToDto(), contactsToDto());
	}
	
	
	private Set<RoleDto> rolesToDto(){
		Set<RoleDto> r = new HashSet<>();
		for(Role role : this.roles){
			r.add(role.toDto());
		}
		return r;
	}
	
	private Set<ContactDto> contactsToDto(){
		Set<ContactDto> c = new HashSet<>();
		for(Contact contact : this.contacts){
			c.add(new ContactDto(contact.getId(), contact.getInfo(), contact.getContactType().toString()));
		}
		return c;
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
		Person obj2 = (Person)obj;
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
