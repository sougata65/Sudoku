package com.sudokuu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sudokuu.beans.User;
import com.sudokuu.constants.Constants;
import com.sudokuu.dao.LoginDAO;
import com.sudokuu.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String msg= request.getParameter("msg");
			if(msg!=null)
			{
				request.setAttribute("msg", msg);
			}
			String redmsg= request.getParameter("redmsg");
			if(redmsg!=null)
			{
				request.setAttribute("redmsg", redmsg);
			}
			String greenmsg= request.getParameter("greenmsg");
			if(greenmsg!=null)
			{
				request.setAttribute("greenmsg", greenmsg);
			}
			logger.info("INFO Page msg recieved");
			if(request.getSession().getAttribute("username")==null)
			{
				logger.info("INFO Not logged in, forwarding to login page");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
			}
			else{
				logger.info("INFO Logged in, redirecting to play");
				response.sendRedirect("play");
				logger.info("INFO Redirected");
			}
		}catch(Exception e)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
			logger.error("Exception caught" + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			logger.info("INFO Uusername and password recieved");
			LoginService loginService = new LoginService();
			User user = loginService.authenticateAndGetUser(username,password);
			logger.info("INFO Authenticating");
			boolean authenticated=false;
			if(user!=null)
			{
				logger.info("Authenticated");
				authenticated=true;
			}
			if(authenticated)
			{
				HttpSession session = request.getSession();
				String role = user.getUserrole();
				boolean isAdmin=false;
				logger.info("INFO Getting user role");
				if(role.equals(Constants.USERROLEADMIN))
				{
					isAdmin = true;
				}
				if(isAdmin)
				{
					logger.info("INFO Userrole = admin");
					session.setAttribute("usertype","admin");
				}
				else{
					logger.info("INFO Userrole = user");
					session.setAttribute("usertype","user");
				}
		
				session.setAttribute("userlevel", loginService.getUserLevel(username));
				logger.info("INFO Userlevel received");
				session.setAttribute("username",username);
				logger.info("INFO Redirecting to play page");
				response.sendRedirect("play");
				logger.info("INFO Redirected");
			}
			else{
				logger.info("INFO Not authenticated");
				request.setAttribute("redmsg","Incorrect username or password or combination.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				logger.info("INFO Forwarding to login page");
				dispatcher.forward(request,response);
				logger.info("INFO Forwarded");
			}
		}catch(Exception e){
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
			logger.log(Level.ERROR, "Login Failed " , e);
		
		}
	}

}
