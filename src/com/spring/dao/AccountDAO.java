package com.spring.dao;

import com.spring.model.Account;
import com.spring.strategy.CreditCard;

public interface AccountDAO {

	public Account getAccount(int id);
	
	public void addAccount(Account acc);
	
	public void addCreditCard(CreditCard cc);
	
	public void updateAccount(Account acc);
}
