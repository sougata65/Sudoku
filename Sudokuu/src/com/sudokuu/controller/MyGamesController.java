package com.sudokuu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sudokuu.service.MyGamesService;

/**
 * Servlet implementation class MyGamesController
 */
@WebServlet("/mygames")
public class MyGamesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	} 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyGamesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String username = (String) request.getSession().getAttribute("username");
			RequestDispatcher dispatcher;
			if(username==null)
			{
				dispatcher = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("redmsg", "Please login to access the page");
				dispatcher.forward(request, response);
			}
			else{
				request.setAttribute("access", "yes");
				MyGamesService mygamesservice = new MyGamesService();
				ArrayList<ArrayList<String>> mygames = mygamesservice.getScores(username);
				int level = mygamesservice.getUserLevel(username);
				request.setAttribute("level", level);
				int totalScore = mygamesservice.getUserTotalScore(username);
				request.setAttribute("totalScore", totalScore);
				request.setAttribute("mygames",mygames);
				dispatcher = request.getRequestDispatcher("MyGames.jsp");
				dispatcher.forward(request, response);
			}
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
		
	}

}
