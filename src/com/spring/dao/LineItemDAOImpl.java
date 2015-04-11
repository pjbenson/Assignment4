package com.spring.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.LineItem;

@Repository
@Transactional
public class LineItemDAOImpl implements LineItemDAO {
	
	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return sessionFactory.openSession();
	}

	@Override
	public void saveLineItem(LineItem lineItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(lineItem);

	}

	@Override
	public void deleteLineItem(int id) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM LineItem WHERE id = "+id).executeUpdate();
	}

	@Override
	public LineItem getItemById(int id) {
		return (LineItem) sessionFactory.getCurrentSession().get(LineItem.class, id);
	}

	@Override
	public void mergeLineItem(LineItem lineItem) {
		sessionFactory.getCurrentSession().update(lineItem);		
	}

}
