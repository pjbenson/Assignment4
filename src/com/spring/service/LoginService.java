package com.spring.service;

import com.spring.model.User;

public interface LoginService {
	
	public User checkLogin(String userName, String userPassword);

	public void logOut();
}
