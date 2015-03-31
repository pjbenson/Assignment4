package com.spring.dao;

import com.spring.model.Account;

public interface AccountDAO {

	public Account getAccount(int id);
	
	public void addStrategyToAccount(Account acc);
}
