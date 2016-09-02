package com.exist.dto;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.io.Serializable;

public class ContactDto implements Serializable{

	private int id;
	private String info;
	private ContactTypeDto type;
	private PersonDto person;
	
	public ContactDto(){}
	
	public ContactDto(int id, ContactTypeDto type, String info){
		this.id = id;
		this.type = type;
		this.info = info;
	}
	
	public ContactDto(String type, String info, PersonDto person){
		for(ContactTypeDto contactType: ContactTypeDto.values()){
			if(contactType.getName().equals(type)){
				this.type = contactType;
				break;
			};
		}
		this.info = info;
		this.person = person;
	}
	
	public ContactDto(int id, String info, String type){
		this.id = id;
		for(ContactTypeDto contactType: ContactTypeDto.values()){
			if(contactType.toString().equals(type)){
				this.type = contactType;
				break;
			}
		}
		this.info = info;
	}
	
	public ContactDto(int id, String info, String type, PersonDto person){
		this(id, info, type);
		this.person = person;
	}
	
    public void setId (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public String getInfo(){
		return info;
	}
	
	public void setContactType(ContactTypeDto type){
		this.type = type;
	}
	
	public ContactTypeDto getContactType(){
		return type;
	}
	
	public PersonDto getPerson(){
		return person;
	}
	
	public void setPerson(PersonDto person){
		this.person=person;
	}

	@Override
	public String toString(){
		return type.toString() + ": " + info;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		ContactDto obj2 = (ContactDto)obj;
		if((this.id == obj2.getId()) && (this.info.equals(obj2.getInfo()))){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int tmp = 0;
		tmp = ( id + info ).hashCode();
		return tmp;
	}
	
}
