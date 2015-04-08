package com.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Category;
import com.spring.model.Stock;

@Repository
@Transactional
public class StockDAOImpl implements StockDAO {
	
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
	public List<Stock> getAllStock() {
		return sessionFactory.getCurrentSession().createCriteria(Stock.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		return sessionFactory.getCurrentSession().createCriteria(Category.class).list();
	}

	@Override
	public void saveStock(Stock stock) {
		sessionFactory.getCurrentSession().saveOrUpdate(stock);
	}

	@Override
	public void saveCategory(Category cat) {
		sessionFactory.getCurrentSession().saveOrUpdate(cat);
	}

}
