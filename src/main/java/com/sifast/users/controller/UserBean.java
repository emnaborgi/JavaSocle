package com.sifast.users.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sifast.users.dao.UserRoleDao;
import com.sifast.users.model.User;

@ManagedBean
@SessionScoped
public class UserBean {
	
	@ManagedProperty(value="#{userRoleDao}")
	private UserRoleDao dao;
	
	private User user = new User();

	public UserRoleDao getDao() {
		return dao;
	}

	public void setDao(UserRoleDao dao) {
		this.dao = dao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String authentification(ActionEvent actionEvent) throws IOException{
		System.out.println(new StringBuilder("user name : " + user.getUsername() + " password : " + user.getPassword()));
		if(dao.authentification(user)){
			System.out.println("ok");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bienvenue"));
//			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
//			extContext.redirect(".xhtml");
			return "success";
		}else{
			System.out.println("no");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur!!"));
			return "";
		}
	}

}
