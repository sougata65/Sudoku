package com.sudokuu.service;

import com.sudokuu.beans.User;
import com.sudokuu.dao.SignUpDAO;

public class SignUpService {
	public String addUser(User newUser) throws Exception{
		SignUpDAO userAdder = new SignUpDAO();
		String msg=userAdder.addUser(newUser);
		if(msg.equals("User already present"))
		{
			return "User already present";
		}
		else{
			return "User successfully added";
		}
	}

}
