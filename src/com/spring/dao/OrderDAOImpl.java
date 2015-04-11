package com.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Order;
import com.spring.model.Stock;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		return sessionFactory.getCurrentSession().createCriteria(Order.class).list();
	}

}
