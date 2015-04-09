package com.spring.dao;

import java.util.List;

import com.spring.model.Account;
import com.spring.model.Cart;
import com.spring.model.Order;
import com.spring.model.Stock;
import com.spring.model.User;

public interface UserDao {
	
	public void updateBalance(User user);
	
	public void addUser(User user);
	
	public List<User> userList();
	
	public User getUser(int userId);
	
	public void deleteUser(Integer userID);
	
	public void saveOrder(Order order);
	
	public void updateCart(Cart cart);
}
