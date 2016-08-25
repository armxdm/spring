package com.exist.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import com.exist.dto.*;

@Component
@Aspect
public class LoggingAspect{
	
	static Logger log = Logger.getLogger(LoggingAspect.class.getName());
	
	@Pointcut("execution(* com.exist.service.*.add*(..))")
	private void addMethods(){}
	
	@Pointcut("execution(* com.exist.service.*.update*(..))")
	private void updateMethods(){}
	
	@Pointcut("execution(* com.exist.service.*.delete*(..))")
	private void deleteMethods(){}
	
	@Pointcut("addMethods() || updateMethods() || deleteMethods()")
	private void loggableServiceMethods(){}
	
	@After("loggableServiceMethods()")
	public void logServiceMethods(JoinPoint joinPoint){
		log.info("Executed: " + joinPoint.getSignature().getName());
	}
	/*@After("addMethods()")
	public void logAddMethod(JoinPoint joinPoint){
		//log.info("[%s] Added: (%s)", MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(), joinPoint.getArgs()); 
		log.info("[" + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + "] Added: " + joinPoint.getArgs());
	}
	
	@After("updateMethods()")
	public void logUpdateMethod(JoinPoint joinPoint){
		//log.info("[%s] Updated: (%s)", MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(), joinPoint.getArgs()); 
		log.info("[" + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + "] Updated: " + joinPoint.getArgs());
	}
	
	@After("deleteMethods()")
	public void logDeleteMethod(JoinPoint joinPoint){
		//log.info("[%s] Deleted: (%s)", MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(), joinPoint.getArgs()); 
		log.info("[" + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + "] Deleted: " + joinPoint.getArgs());
	}*/
	/*@After("execution(* com.exist.service.PersonService.addPerson(..))")
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
	}*/
	
}