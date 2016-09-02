package com.exist.dao;

import com.exist.dao.util.SessionUtil;
import com.exist.model.Role;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    private SessionUtil sessionUtil;

    public List<Role> getRoles() {
        List<Role> roles = null;
        try {
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

    public Role getRoleById(int roleId) {
        Role role = null;
        try {
            sessionUtil.openCurrentSessionAndTransaction();
            role = (Role) sessionUtil.getSession().get(Role.class, roleId);
        } catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        } finally {
            sessionUtil.closeCurrentSessionAndTransaction();
        }
        return role;
    }

    public void addRole(Role role) {
        try {
            sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().persist(role);
        } catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        } finally {
            sessionUtil.closeCurrentSessionAndTransaction();
        }
    }

    public void updateRole(Role role) {
        try {
            sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().update(role);
        } catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        } finally {
            sessionUtil.closeCurrentSessionAndTransaction();
        }
    }

    public void deleteRole(Role role) {
        try {
            sessionUtil.openCurrentSessionAndTransaction();
            sessionUtil.getSession().delete(role);
        } catch (HibernateException e) {
            sessionUtil.rollbackTransaction(e);
        } finally {
            sessionUtil.closeCurrentSessionAndTransaction();
        }
    }
}