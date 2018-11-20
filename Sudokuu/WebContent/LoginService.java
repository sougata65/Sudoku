package com.sudokuu.service;

import com.sudokuu.beans.User;
import com.sudokuu.dao.LoginDAO;
import com.sudokuu.dao.PlayDAO;

public class LoginService {

	public User authenticateAndGetUser(String username, String password) {
		LoginDAO authenticator = new LoginDAO();
		User user = authenticator.authenticate(username, password);
		
		return user;
	}
	public int getUserLevel(String username){
		PlayDAO playDAO = new PlayDAO();
		return playDAO.getUserLevel(username);
	}

}
