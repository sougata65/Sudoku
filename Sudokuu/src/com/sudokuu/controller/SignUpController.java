package com.sudokuu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sudokuu.beans.User;
import com.sudokuu.dao.SignUpDAO;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			logger.error("Exception caught \n"+e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String fullname = request.getParameter("fullname");
			User newUser = new User(fullname,username,email,password);
			SignUpDAO adder = new SignUpDAO();
			String msg = adder.addUser(newUser);
			if(msg.equals("User already present"))
			{
				request.setAttribute("redmsg","Username taken. Please choose a different one");
				RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				response.sendRedirect("login?greenmsg=You have successfully signed up");
			}
		}catch(Exception e){
			logger.error("Exception caught \n"+e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
		}
	}

}
