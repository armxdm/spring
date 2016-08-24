package com.exist.dao;

import org.hibernate.HibernateException;
import com.exist.model.Role;
import com.exist.utility.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import java.util.List;

public class RoleDao {
	
	private SessionUtil sessionUtil;
	
	public RoleDao(SessionUtil sessionUtil){
		this.sessionUtil = sessionUtil;
	}
	
    public List<Role> getRoles() {
     	List<Role> roles = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			Criteria cr = sessionUtil.getSession().createCriteria(Role.class);
			cr.addOrder(Order.asc("id"));
			roles = cr.list();
		} catch (HibernateException e) {
             sessionUtil.rollbackTransaction(e);
        } finally {
			 sessionUtil.closeCurrentSessionAndTransaction();
		}
        return roles;
    }
    
    public Role getRoleById(int roleId){
        Role role = null;
		try{
			sessionUtil.openCurrentSessionAndTransaction();
			role = (Role)sessionUtil.getSession().get(Role.class, roleId);
		} catch (HibernateException e) {
             sessionUtil.rollbackTransaction(e);
        } finally {
			 sessionUtil.closeCurrentSessionAndTransaction();
		}
		return role;
    }
    
    public void addRole (Role role){
	    try {
			sessionUtil.openCurrentSessionAndTransaction();
		   	sessionUtil.getSession().persist(role);
	    }catch (HibernateException e) {
             sessionUtil.rollbackTransaction(e);
        }finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
    }
    
    public void updateRole (Role role){
	    try {
			sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().update(role);
	    }catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        }finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
    }
    
    public void deleteRole (Role role) {
		try{
			sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().delete(role);
        }catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        }finally{
			sessionUtil.closeCurrentSessionAndTransaction();
		}
    }
}

