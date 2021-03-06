package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.spring.dao.UserDao;
import com.spring.model.Order;
import com.spring.model.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public List<User> userList() {
		return userDao.userList();
	}

	@Override
	public void deleteUser(Integer userID) {
		userDao.deleteUser(userID);
	}

	@Override
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateBalance(user);
	}

	@Override
	public void saveOrder(Order order) {
		userDao.saveOrder(order);
	}
}
