package com.sudokuu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sudokuu.constants.Constants;
import com.sudokuu.service.PlayService;
import com.sudokuu.utils.Config;
import com.sudokuu.utils.MatrixOperations;

/**
 * Servlet implementation class LoadSavedGameServlet
 */
@WebServlet("/play")
public class PlayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger = Logger.getRootLogger();
	}    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String redmsg = request.getParameter("redmsg");
		String greenmsg = request.getParameter("greenmsg");
		String msg = request.getParameter("msg");
		String username = (String) request.getSession().getAttribute("username");
		if(username==null)
		{
			request.setAttribute("redmsg","Please login to access the page");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request,response);
		}
		PlayService playService = new PlayService();
		ArrayList<int[][]> game = new ArrayList<>();
		game = playService.loadGame(username);
		request.setAttribute("redmsg",redmsg);
		request.setAttribute("greenmsg", greenmsg);
		request.setAttribute("msg", msg);
		request.setAttribute("game", game);
		request.setAttribute("access", "yes");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Play.jsp");
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
		try{
			String username= (String) request.getSession().getAttribute("username");
			int level =(int) request.getSession().getAttribute("userlevel");
			String action = request.getParameter("action");
			int matrix[][]=new int[9][9];
			int i,j;
			for(i=0;i<9;i++)
			{
				for(j=0;j<9;j++)
				{
					String cellname = ""+i+""+j;
					String cellvalue = request.getParameter(cellname);
					if(cellvalue==null || cellvalue.isEmpty())
					{
						matrix[i][j]=0;
					}
					else
					{
						matrix[i][j]=Integer.parseInt(cellvalue);
					}
				}
			}
			PlayService playService = new PlayService();
			if(action.equals("submit")){
				boolean isSolved = playService.isSolved(matrix);
				if(isSolved)
				{
					String computerSolver = (String) request.getSession().getAttribute("SOLVER");
					int[][] nextLevelMatrix = new int[9][9];
					playService.saveGame(username, level+1, nextLevelMatrix);
					if(computerSolver==null)
					{
						playService.saveSolvedGame(username, level, matrix);
					}
					else{
						playService.saveGame(username, level, matrix);
					}
					ArrayList<int[][]> game = new ArrayList<>();
					request.setAttribute("greenmsg","Level "+level+" completed successfully");
					request.setAttribute("access", "yes");
					level=playService.getUserLevel(username);
					game = playService.loadGame(username);
					request.setAttribute("game", game);
					request.getSession().setAttribute("userlevel", level);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Play.jsp");
					dispatcher.forward(request, response);
				}
				else{
					ArrayList<int[][]> game = new ArrayList<>();
					game = playService.loadGame(username);
					request.setAttribute("game", game);
					request.setAttribute("redmsg","Incorrect solution");
					request.setAttribute("access", "yes");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Play.jsp");
					dispatcher.forward(request, response);
				}
			}
			if(action.equals("save"))
			{
				playService.saveGame(username, level, matrix);
				ArrayList<int[][]> game = new ArrayList<>();
				game = playService.loadGame(username);
				request.setAttribute("game", game);
				request.setAttribute("greenmsg","Saved successfully");
				request.setAttribute("access", "yes");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Play.jsp");
				dispatcher.forward(request, response);
			}
			if(action.equals("solve"))
			{
				ArrayList<int[][]> game = new ArrayList<>();
				game=playService.loadGame(username);
				int[][] gametemplate = new int[9][9];
				for(int row=0;row<9;row++)
				{
					for(int col=0;col<9;col++)
					{
						gametemplate[row][col]=game.get(0)[row][col];
					}
				}
				int[][] solvedMatrix = playService.solve(gametemplate);
				game.remove(1);
				game.add(1, solvedMatrix);
				request.setAttribute("game", game);
				request.getSession().setAttribute("SOLVER", "yes");
				request.setAttribute("access", "yes");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Play.jsp");
				dispatcher.forward(request, response);
			}
		}catch(Exception e){
			logger.error("ERROR \n"+e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
			dispatcher.forward(request, response);
		}
	}

}
