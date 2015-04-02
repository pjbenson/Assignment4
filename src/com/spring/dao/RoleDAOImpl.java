package com.spring.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return sessionFactory.openSession();
	}
    
	@Override
	@Transactional(readOnly=true)
	public Role getRole(int id) {
		return (Role) sessionFactory.getCurrentSession().load(Role.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public void updateRole(Role role) {
		sessionFactory.getCurrentSession().update(role);		
	}

}
