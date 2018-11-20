package com.sudokuu.controller;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sudokuu.constants.Constants;
import com.sudokuu.service.CreateGameService;

/**
 * Servlet implementation class AdminGameAdder
 */
@WebServlet("/creategame")
public class CreateGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String usertype = (String) request.getSession().getAttribute("usertype");
			String username = (String) request.getSession().getAttribute("username");
			logger.info("INFO Received username and type");
			RequestDispatcher dispatcher;
			CreateGameService creationService = new CreateGameService();
			int level = creationService.getNextLevelToBeAdded();
			if(usertype==null)
			{
				logger.info("INFO Not logged in, redirecting to login");
				response.sendRedirect("login?redmsg=You must be logged in to access the page");
			}
			else{
				String access = (String) request.getAttribute("access");
				if(access==null)
				{
					if(!usertype.equals(Constants.USERROLEADMIN))
					{
						request.setAttribute("access", "no");
						request.setAttribute("nextLevel", level);
						dispatcher = request.getRequestDispatcher("CreateGame.jsp");
						dispatcher.forward(request, response);
					}
					else{
						int[][] matrix =(int[][]) request.getAttribute("template");
						request.setAttribute("access", "yes");
						request.setAttribute("nextLevel", level);
						request.setAttribute("template", matrix);
						dispatcher = request.getRequestDispatcher("CreateGame.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}catch(Exception e){
			logger.log(org.apache.log4j.Level.ERROR,e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			CreateGameService creationService = new CreateGameService();
			int i,j;
			int level =Integer.parseInt( (String) request.getParameter("level"));
			
			String template="";
			int[][] matrix = new int[9][9];
			int[][] check = new int[9][9];
			for(i=0;i<9;i++)
			{
				for(j=0;j<9;j++)
				{
					String cellname = ""+i+""+j;
					String value = (String) request.getParameter(cellname);
					int intvalue;
					if(value==null || value.equals(""))
					{
						intvalue = 0;
						template=template+" ";
					}
					else{
						intvalue = Integer.parseInt(value);
					}
					matrix[i][j] = intvalue;
					check[i][j]=intvalue;
					template=template+intvalue;
				}
			}
			boolean solvable = creationService.isSolvable(check);
			if(level==0)
			{
				request.setAttribute("template", matrix);
				request.setAttribute("access", "yes");
				request.setAttribute("redmsg","There was some error getting the level, try re-entering");
				RequestDispatcher dispatcher = request.getRequestDispatcher("CreateGame.jsp");
				dispatcher.forward(request, response);
			}
			else if(creationService.isLevelAlreadyPresent(level))
			{
				request.setAttribute("template", matrix);
				request.setAttribute("access", "yes");
				int nextLevel = creationService.getNextLevelToBeAdded();
				request.setAttribute("nextLevel", nextLevel);
				request.setAttribute("redmsg","Template already present for this level. Please add a template for a different level");
				RequestDispatcher dispatcher = request.getRequestDispatcher("CreateGame.jsp");
				dispatcher.forward(request, response);
			}
			else{
				if(solvable)
				{
					creationService.addLevel(level, matrix);
					request.setAttribute("template", matrix);
					request.setAttribute("access", "yes");
					int nextLevel = creationService.getNextLevelToBeAdded();
					request.setAttribute("nextLevel", nextLevel);
					request.setAttribute("greenmsg","Template successfully added for level "+level);
					RequestDispatcher dispatcher = request.getRequestDispatcher("CreateGame.jsp");
					dispatcher.forward(request, response);
				}
				else{
					request.setAttribute("template", matrix);
					request.setAttribute("access", "yes");
					int nextLevel = creationService.getNextLevelToBeAdded();
					request.setAttribute("nextLevel", nextLevel);
					request.setAttribute("redmsg","Please enter a solvable game. The one entered was not solvable");
					RequestDispatcher dispatcher = request.getRequestDispatcher("CreateGame.jsp");
					dispatcher.forward(request, response);
				}
			}
		}catch(Exception e){
			logger.error("Exception caught \n"+e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
		}
		
		
	}

}
