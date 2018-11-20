package com.sudokuu.utils;

import java.util.HashSet;
import java.util.Set;


public class MatrixOperations {
	public boolean isSolvable(int[][] matrix){
		  Sudoku sudoku = new Sudoku(); 
		    int N = matrix.length; 
		  
		    if (Sudoku.solveSudoku(matrix, N)) 
		    { 
		        int[][] result = Sudoku.print(matrix, N); // print solution
		        return true;
		    }  
		    else
		    { 
		        System.out.println("No solution");
		        return false;
		    } 
		}
	
	public int[][] solve(int[][] matrix){
		  Sudoku sudoku = new Sudoku();
		  int N = matrix.length; 
		  
		    if (Sudoku.solveSudoku(matrix, N)) 
		    { 
		        int[][] result = Sudoku.print(matrix, N); // print solution 
		        return result;
		    }  
		    else
		    { 
		        System.out.println("No solution");
		        return null;
		    } 
	}
	
	public int[][] templateToMatrix(String template){
		if(template==null)
		{
			return new int[9][9];
		}
		int stridx=0;
		int i,j;
		int[][] matrix = new int[9][9];
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				if(Character.isDigit(template.charAt(stridx)))
				{
					matrix[i][j]=Integer.parseInt(template.substring(stridx, stridx+1));
				}
				else if(template.charAt(stridx)==' '){
					matrix[i][j]=0;
				}
				stridx++;
			}
		}
		return matrix;
	}
	
	public String matrixToTemplate(int[][] matrix){
		int i,j;
		String template = "";
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				if(matrix[i][j]!=0)
				{
					template=template+matrix[i][j];
				}
				else
				{
					template=template+" ";
				}
			}
		}
		return template;
	}
	
	public boolean isSolved(int[][] matrix){
		int i,j;
		// empty spaces check
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				if(matrix[i][j]==0)
				{
					return false;
				}
			}
		}
		// row and column check
		Set<Integer> axisCheck = new HashSet<>();
		for(i=0;i<9;i++)
		{
			axisCheck.clear();
			for(j=0;j<9;j++)
			{
				axisCheck.add(matrix[i][j]);
			}
			if(axisCheck.size()!=9)
			{
				return false;
			}
		}
		for(j=0;j<9;j++)
		{
			axisCheck.clear();
			for(i=0;i<9;i++)
			{
				axisCheck.add(matrix[i][j]);
				
			}
			if(axisCheck.size()!=9)
			{
				return false;
			}
		}
		// 3x3 grid check
		i=0;j=0;
		Set<Integer> gridCheck = new HashSet<>();
		for(i=0;i<9;i=i+3)
		{
			for(j=0;j<9;j=j+3)
			{
				gridCheck.clear();
				gridCheck=gridValues(matrix,i,j);
				if(gridCheck.size()!=9)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	static Set<Integer> gridValues(int[][] matrix,int istart,int jstart){
		int i,j;
		Set<Integer> result = new HashSet<>();
		for(i=istart;i<istart+3;i++)
		{
			for(j=jstart;j<jstart+3;j++)
			{
				result.add(matrix[i][j]);
			}
		}
		return result;
	}
	public boolean isValidSudoku(int[][] board){
		char[][] array = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char chrToBeInsterted = Character.forDigit(board[i][j] , 10);
				if (chrToBeInsterted == '0') {
					chrToBeInsterted = '.';
				}
				array[i][j] = chrToBeInsterted;
			}
		}
		
		return isValidSudoku(array);
		
	}

	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return false;
		// check each column
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - '1')]) {
						return false;
					}
					m[(int) (board[i][j] - '1')] = true;
				}
			}
		}

		// check each row
		for (int j = 0; j < 9; j++) {
			boolean[] m = new boolean[9];
			for (int i = 0; i < 9; i++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - '1')]) {
						return false;
					}
					m[(int) (board[i][j] - '1')] = true;
				}
			}
		}

		// check each 3*3 matrix
		for (int block = 0; block < 9; block++) {
			boolean[] m = new boolean[9];
			for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
				for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
					if (board[i][j] != '.') {
						if (m[(int) (board[i][j] - '1')]) {
							return false;
						}
						m[(int) (board[i][j] - '1')] = true;
					}
				}
			}
		}

		return true;
	}


}
