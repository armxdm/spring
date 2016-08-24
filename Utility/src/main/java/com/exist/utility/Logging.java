package com.exist.utility;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.apache.log4j.Logger;
import com.exist.dto.*;

@Aspect
public class Logging{
	
	static Logger log = Logger.getLogger(Logging.class.getName());

	@After("execution(* com.exist.service.PersonService.addPerson(..))")
   	public void addPerson(JoinPoint joinPoint){	
		log.info("Person: " + getPerson(joinPoint) + " is added.");
   	}
	
	@After("execution(* com.exist.service.PersonService.updatePerson(..))")
   	public void updatePerson(JoinPoint joinPoint){	
		log.info("Person: " + getPerson(joinPoint) + " is updated.");
   	}
	
	@After("execution(* com.exist.service.PersonService.deletePerson(..))")
   	public void deletePerson(JoinPoint joinPoint){	
		log.info("Person: " + getPerson(joinPoint) + " is deleted.");
   	}
	
	@After("execution(* com.exist.service.RoleService.addRole(..))")
   	public void addRole(JoinPoint joinPoint){
		log.info("Role: " + getRoleName(joinPoint) + " is added.");
   	}
	
	@After("execution(* com.exist.service.RoleService.updateRole(..))")
   	public void updateRole(JoinPoint joinPoint){
		log.info("Role: " + getRoleName(joinPoint) + " is updated.");
   	}
	
	@After("execution(* com.exist.service.RoleService.deleteRole(..))")
   	public void deleteRole(JoinPoint joinPoint){
		log.info("Role: " + getRoleName(joinPoint) + " is deleted.");
   	}
	
	private PersonDto getPerson(JoinPoint joinPoint){
		PersonDto person = null;
		for(Object arg: joinPoint.getArgs()){
			if(arg instanceof PersonDto){
				person = (PersonDto) arg;
			}
		}
		return person;
	}
	
	private String getRoleName(JoinPoint joinPoint){
		String roleName = null;
		for(Object arg: joinPoint.getArgs()){
			if(arg instanceof String){
				roleName = (String) arg;
			} else if(arg instanceof RoleDto){
				RoleDto role = (RoleDto) arg;
				roleName = role.getRoleName();
			}
		}
		return roleName;
	}
	
}