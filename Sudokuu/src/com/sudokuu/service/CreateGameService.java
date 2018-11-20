package com.sudokuu.service;

import com.sudokuu.dao.CreateGameDAO;
import com.sudokuu.utils.MatrixOperations;

public class CreateGameService {
	public boolean isSolvable(int[][] matrix){
		MatrixOperations operator = new MatrixOperations();
		boolean isValid=operator.isValidSudoku(matrix);
		boolean result;
		if(isValid)
		{
			result = operator.isSolvable(matrix);
		}
		else{
			result = false;
		}
		return result;
	}
	
	public boolean isLevelAlreadyPresent(int level) throws Exception{
		CreateGameDAO creator = new CreateGameDAO();
		boolean result = creator.isLevelAlreadyPresent(level);
		return result;
	}
	
	public void addLevel(int level,int[][] matrix) throws Exception{
		CreateGameDAO creator = new CreateGameDAO();
		creator.addTemplate(level, matrix);
	}
	public int getNextLevelToBeAdded() throws Exception{
		CreateGameDAO createDAO = new CreateGameDAO();
		return createDAO.getNextLevelToBeAdded();
	}

}
