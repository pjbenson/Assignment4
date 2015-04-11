package com.spring.service;

import com.spring.model.Account;
import com.spring.strategy.CreditCard;

public interface AccountService {
	
	public Account getAccount(int id);
	
	public void addAccount(Account acc);
	
	public void addCreditCard(CreditCard cc);
	
	public void updateAccount(Account acc);
}
