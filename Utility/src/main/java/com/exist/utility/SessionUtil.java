package com.exist.utility;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class SessionUtil{
	private SessionFactory factory;
	private Session session; 
	private Transaction transaction;
	
	public SessionUtil(SessionFactory factory){
		this.factory = factory;
	}
	
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