package com.exist.dao;

import com.exist.dao.util.SessionUtil;
import com.exist.model.Person;
import com.exist.model.Contact;

import org.hibernate.Query;;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao{
	
	@Autowired
	private SessionUtil sessionUtil;
	
	 public List<Person> getPersons(String order) {
       	List<Person> persons = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Session session = sessionUtil.getSession();
			Query query = null;
			if (order.equals("Last Name")){
				query = session.createQuery("FROM Person p ORDER BY p.lastName ASC");
			} else if (order.equals("Date Hired")){
         		query = session.createQuery("FROM Person p ORDER BY p.dateHired ASC");
			} else if (order.equals("GWA")){
				query = session.createQuery("FROM Person p ORDER BY p.gwa ASC");
			} else if (order.equals("ID")){
				query = session.createQuery("FROM Person p ORDER BY p.id ASC");
       		}
			persons = query.list();
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
        	sessionUtil.closeCurrentSessionAndTransaction();
		}
        return persons;
    }
	
	public List<Person> getPersonsByGwa(double gwa){
		List<Person> persons = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Query query = null;
			if(gwa < 0){
				query = sessionUtil.getSession().createQuery("FROM Person p ORDER BY p.gwa");
			} else{
				query = sessionUtil.getSession().createQuery("FROM Person p WHERE p.gwa = :gwa ORDER BY p.gwa");
				query.setParameter("gwa", gwa);
			}
			query.setCacheable(true);
			persons = query.list();
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
        	sessionUtil.closeCurrentSessionAndTransaction();
		}
        return persons;
	}
	
	public List<Person> getPersonsByLastName(String lastName){
		List<Person> persons = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Query query = null;
			if(lastName.isEmpty()){
				query = sessionUtil.getSession().createQuery("FROM Person p ORDER BY p.lastName");
			} else {
				query = sessionUtil.getSession().createQuery("FROM Person p WHERE lower(p.lastName) = :lastName ORDER BY p.lastName");
				query.setParameter("lastName", lastName.toLowerCase());
			}
			query.setCacheable(true);
			persons = query.list();
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
        	sessionUtil.closeCurrentSessionAndTransaction();
		}
        return persons;
	}
	
	public List<Person> getPersonsByDateHired(Date dateHired){
		List<Person> persons = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Query query = null;
			if(dateHired == null){
				query = sessionUtil.getSession().createQuery("FROM Person p ORDER BY p.dateHired");
			} else {
				query = sessionUtil.getSession().createQuery("FROM Person p WHERE p.dateHired = :dateHired ORDER BY p.dateHired");
				query.setParameter("dateHired", dateHired);
			}
			query.setCacheable(true);
			persons = query.list();
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
        	sessionUtil.closeCurrentSessionAndTransaction();
		}
        return persons;
	}
	
	public Person getPersonById(int personId){
		Person person = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			person = (Person)sessionUtil.getSession().get(Person.class, personId);
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
		return person;
	}
	
	public Contact getContactById(int contactId){
		Contact contact = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			contact = (Contact)sessionUtil.getSession().get(Contact.class, contactId);
		} catch (HibernateException e) {
           sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
		return contact;
	}
	
	public void addPerson(Person person){
		try {
			sessionUtil.openCurrentSessionAndTransaction();
			sessionUtil.getSession().persist(person);
	    } catch (HibernateException e) {
			sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
	}
	
	public void deletePerson (Person person) {
		try{
			sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().delete(person);
        }catch (HibernateException e) {
           	sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
    }
    
    public void updatePerson (Person person) {
	    try {
			sessionUtil.openCurrentSessionAndTransaction();
		    sessionUtil.getSession().update(person);
	    } catch (HibernateException e) {
           	sessionUtil.rollbackTransaction(e);
        } finally {
			sessionUtil.closeCurrentSessionAndTransaction();
		}
    }
}
