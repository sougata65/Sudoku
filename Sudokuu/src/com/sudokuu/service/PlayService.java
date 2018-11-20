package com.sudokuu.service;

import java.util.ArrayList;

import com.sudokuu.dao.PlayDAO;
import com.sudokuu.utils.MatrixOperations;

public class PlayService {
	
	public ArrayList<int[][]> loadGame(String username) throws Exception{
		MatrixOperations operator = new MatrixOperations();
		PlayDAO playDAO = new PlayDAO();
		int level = playDAO.getUserLevel(username);
		String[] game = playDAO.loadGame(username, level);
		String template=game[0];
		String savedgame=game[1];
		ArrayList<int[][]> result = new ArrayList<>();
		result.add(operator.templateToMatrix(template));
		result.add(operator.templateToMatrix(savedgame));
		return result;
	}
	public int getUserLevel(String username) throws Exception{
		PlayDAO playDAO = new PlayDAO();
		return playDAO.getUserLevel(username);
	}
	public int getMaxLevelAddedTillNow() throws Exception{
		PlayDAO playDAO = new PlayDAO();
		return playDAO.getMaxLevelAdded();
	}
	
	public void saveGame(String username,int level,int[][] matrix) throws Exception{
		MatrixOperations operator = new MatrixOperations();
		String savedgame = operator.matrixToTemplate(matrix);
		PlayDAO saver = new PlayDAO();
		saver.saveGame(username, level, savedgame);
	}
	
	public void saveSolvedGame(String username,int level,int[][] matrix) throws Exception{
		MatrixOperations operator = new MatrixOperations();
		String savedgame = operator.matrixToTemplate(matrix);
		PlayDAO saver = new PlayDAO();
		saver.saveSolvedGame(username, level, savedgame);
	}
	
	public boolean isSolved(int[][] matrix){
		MatrixOperations operator = new MatrixOperations();
		boolean result = operator.isSolved(matrix);
		return result;
	}
	public int[][] solve(int[][] matrix){
		MatrixOperations operator = new MatrixOperations();
		int[][] result = operator.solve(matrix);
		return result;
	}
	public boolean isSolvable(int[][] matrix){
		MatrixOperations operator = new MatrixOperations();
		return operator.isSolvable(matrix);
	}
}
