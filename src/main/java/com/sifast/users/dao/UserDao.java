package com.sifast.users.dao;

import com.sifast.users.model.User;

public interface UserDao {
	
	public void save(User user);

	public boolean authentification(User user);

}
