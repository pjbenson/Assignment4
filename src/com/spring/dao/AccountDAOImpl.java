package com.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Account;
import com.spring.template.CreditCard;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Account getAccount(int id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void addAccount(Account acc) {
		sessionFactory.getCurrentSession().saveOrUpdate(acc);
	}

	@Override
	public void addCreditCard(CreditCard cc) {
		sessionFactory.getCurrentSession().saveOrUpdate(cc);
	}

	@Override
	public void updateAccount(Account acc) {
		sessionFactory.getCurrentSession().update(acc);
		
	}

}
