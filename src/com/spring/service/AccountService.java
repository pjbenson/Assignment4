package com.spring.service;

import com.spring.model.Account;

public interface AccountService {
	
	public Account getAccount(int id);
	
	public void addStrategyToAccount(Account acc);
}
