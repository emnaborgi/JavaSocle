package com.sifast.users.dao;

import com.sifast.users.model.User;
import com.sifast.users.model.UserRole;

public interface UserRoleDao {
	
	public Integer save(UserRole userRole);

	public boolean authentification(User user);

}
