package com.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Category;
import com.spring.model.Stock;
import com.spring.model.User;

@Repository
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
	@Transactional
	public List<Stock> getAllStock() {
		return sessionFactory.getCurrentSession().createCriteria(Stock.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getCategories() {
		return sessionFactory.getCurrentSession().createCriteria(Category.class).list();
	}

	@Override
	@Transactional
	public void saveStock(Stock stock) {
		sessionFactory.getCurrentSession().saveOrUpdate(stock);
	}

	@Override
	@Transactional
	public void saveCategory(Category cat) {
		sessionFactory.getCurrentSession().saveOrUpdate(cat);
	}

	@Override
	@Transactional
	public Stock getStockById(int id) {
		return (Stock) sessionFactory.getCurrentSession().get(Stock.class, id);
	}

	@Override
	@Transactional
	public void updateStock(Stock stock) {
		sessionFactory.getCurrentSession().update(stock);
	}

}
