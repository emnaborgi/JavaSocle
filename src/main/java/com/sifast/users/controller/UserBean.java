package com.sifast.users.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sifast.users.dao.UserRoleDao;
import com.sifast.users.model.User;

@ManagedBean(name = "loginBean")
// @SessionScoped
@RequestScoped
public class UserBean {

	@ManagedProperty(value = "#{userRoleDao}")
	private UserRoleDao dao;

	private User user = new User();

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

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

	public String authentification(ActionEvent actionEvent) throws IOException {

		System.out.println(dao == null);
		System.out.println(new StringBuilder("user name : "
				+ user.getUsername() + " password : " + user.getPassword()));
		if (dao.authentification(user)) {
			System.out.println("ok");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bienvenue"));
			ExternalContext extContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			extContext.redirect("secure.xhtml");
			System.out.println("success");
		} else {
			System.out.println("no");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur!!"));
			
			ExternalContext extContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			extContext.redirect("unsecure.xhtml");
			System.out.println("");
		}
		// ////////////////////////////
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.getUser(), this.getPassword());

			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		/**/// ///////////////////////originale///////////////////////
		// System.out.println(new StringBuilder("user name : "
		// + user.getUsername() + " password : " + user.getPassword()));
		// if (dao.authentification(user)) {
		// System.out.println("ok");
		// FacesContext.getCurrentInstance().addMessage(null,
		// new FacesMessage("Bienvenue"));
		// // ExternalContext extContext =
		// // FacesContext.getCurrentInstance().getExternalContext();
		// // extContext.redirect(".xhtml");
		// return "success";
		// } else {
		// System.out.println("no");
		// FacesContext.getCurrentInstance().addMessage(null,
		// new FacesMessage("Erreur!!"));
		// return "";
		// }

		// //////////////////////////////*/
		return "";

	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	

	private Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}
