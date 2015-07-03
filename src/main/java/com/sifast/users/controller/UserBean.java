package com.sifast.users.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	 public String authentification() {
	        try {
	            Authentication result = null;
	            Authentication request = new UsernamePasswordAuthenticationToken(this.getUser(), this.getPassword());
	            result = authenticationManager.authenticate(request);
	            SecurityContextHolder.getContext().setAuthentication(result);
	        } catch (AuthenticationException e) {
	            e.printStackTrace();
	        }
	        return "Secured";
	    }
	 
	    /**
	     * Cancel.
	     * 
	     * @return the string
	     */
	    public String cancel() {
	        return null;
	    }
	 
	    /**
	     * Logout.
	     * 
	     * @return the string
	     */
	    public String logout() {
	        SecurityContextHolder.clearContext();
	        /**
	         * Delete Cookies
	         */
	        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
	                .getResponse();
	        Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", null);
	        cookie.setMaxAge(0);
	        cookie.setPath(httpServletRequest.getContextPath().length() > 0 ? httpServletRequest.getContextPath() : "/");
	        httpServletResponse.addCookie(cookie);
	        return "loggedout";
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
