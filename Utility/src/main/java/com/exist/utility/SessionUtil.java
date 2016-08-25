package com.exist.utility;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionUtil{
	
	@Autowired
	private SessionFactory factory;
	
	private Session session; 
	private Transaction transaction;
	
	public Session getSession(){
		return session;
	}
	
	public void openCurrentSessionAndTransaction(){
		session = this.factory.openSession();
		transaction = session.beginTransaction();
	}
	
	public void closeCurrentSessionAndTransaction(){
		transaction.commit();
		session.close();
	}
	
	public void rollbackTransaction(Exception e){
		 if (transaction!=null) {
				transaction.rollback();
		 }
         throw new RuntimeException(e);
	}
}