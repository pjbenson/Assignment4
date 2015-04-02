package com.spring.dao;

import com.spring.model.Role;

public interface RoleDAO {
	
	public Role getRole(int id);
	
	public void updateRole(Role role);
}
