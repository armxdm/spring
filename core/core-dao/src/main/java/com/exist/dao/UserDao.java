package com.exist.dao;

import com.exist.utility.SessionUtil;
import com.exist.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao{
	
	/*@Autowired
	private SessionFactory factory;

	private Session session; 
	private Transaction transaction;*/
	@Autowired
	private SessionUtil sessionUtil;

	public User findByUsername(String username){
		/*User user = (User) sessionFactory.getCurrentSession()
			.createCriteria(User.class)
			.add(Restrictions.eq("username",username))
			.uniqueResult();
		return user;*/	
		User user = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Query query = sessionUtil.getSession().createQuery("FROM User u WHERE u.username = :username");
			query.setParameter("username", username);
			user = (User) query.uniqueResult();
		} catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
        return user;
	}
	
	public void save(User user){
		try {
			sessionUtil.openCurrentSessionAndTransaction();
			sessionUtil.getSession().save(user);
	    } catch (HibernateException e) {
			 sessionUtil.rollbackTransaction(e);
        } finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
		//sessionFactory.getCurrentSession().save(user);
	}
}