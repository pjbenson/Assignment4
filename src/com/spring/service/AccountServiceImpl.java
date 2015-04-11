package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.AccountDAO;
import com.spring.model.Account;
import com.spring.strategy.CreditCard;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDao;

	@Override
	public Account getAccount(int id) {
		return accountDao.getAccount(id);
	}

	@Override
	public void addAccount(Account acc) {
		accountDao.addAccount(acc);
	}

	@Override
	public void addCreditCard(CreditCard cc) {
		accountDao.addCreditCard(cc);
	}

	@Override
	public void updateAccount(Account acc) {
		accountDao.updateAccount(acc);
		
	}

}
