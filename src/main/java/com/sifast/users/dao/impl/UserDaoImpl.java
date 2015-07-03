package com.sifast.users.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sifast.users.dao.UserDao;
import com.sifast.users.model.User;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl implements UserDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.getCurrentSession().flush();
	}

	// @Override
	@Transactional
	public boolean authentification(User user) {
		System.out.println("*********************************************");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		criteria.add(Restrictions.eq("username", user.getUsername())).add(
				Restrictions.eq("password", user.getPassword()));
		User u;
		if (criteria.list() != null && criteria.list().size() > 0)
			u = (User) criteria.list().get(0);
		// if(user.getUsername().equals("admin") &&
		// user.getPassword().equals("admin")){
		// StringBuilder sb = new StringBuilder();
		// return true;
		// }
		if (criteria.list().size() == 0)
			return false;
		else
			return true;
	}

}