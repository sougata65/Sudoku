package com.sudokuu.utils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class temp {
	public static void main(String[] args){
		  Sudoku sudoku = new Sudoku();
		    int[][] board = new int[][] 
		    { 
		            {6, 0, 6, 5, 0, 8, 4, 0, 0}, 
		            {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
		            {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
		            {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
		            {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
		            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
		            {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
		            {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
		            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
		    }; 
		    int N = board.length; 
		  
		    if (Sudoku.solveSudoku(board, N)) 
		    { 
		        int[][] result = Sudoku.print(board, N); // print solution 
		    }  
		    else
		    { 
		        System.out.println("No solution"); 
		    } 
		}	

}
