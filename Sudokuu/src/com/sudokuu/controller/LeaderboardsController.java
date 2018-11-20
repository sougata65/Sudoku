package com.sudokuu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sudokuu.dao.LeaderboardsDAO;
import com.sudokuu.service.LeaderboardsService;

/**
 * Servlet implementation class LeaderboardsServlet
 */
@WebServlet("/leaderboards")
public class LeaderboardsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderboardsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String username = (String) request.getSession().getAttribute("username");
			if(username==null)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("redmsg", "You have to be logged in to access the page");
				dispatcher.forward(request, response);
			}
			LeaderboardsService leaderboardsService = new LeaderboardsService();
			LinkedHashMap<String,Integer> leaderboards =  leaderboardsService.getLeaderboards();
			request.setAttribute("leaderboards",leaderboards);
			request.setAttribute("access", "yes");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Leaderboards.jsp");
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
	}

}
