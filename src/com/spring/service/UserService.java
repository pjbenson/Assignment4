package com.spring.service;

import java.util.List;

import com.spring.model.Cart;
import com.spring.model.Order;
import com.spring.model.Stock;
import com.spring.model.User;

public interface UserService {
	
	public void updateUser(User user);
	
	public User getUser(int userId);
	
	public void addUser(User user);
	
	public List<User> userList();
	
	public void deleteUser(Integer userID);
	
	public void saveOrder(Order order);
	
	public void updateCart(Cart cart);
}
