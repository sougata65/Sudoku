package com.sudokuu.utils;

/* A Backtracking program in  
 Java to solve Sudoku problem */
class Sudoku {
	public static boolean isSafe(int[][] board, int row, int col, int num) {
		// row has the unique (row-clash)
		for (int d = 0; d < board.length; d++) {
			// if the number we are trying to
			// place is already present in
			// that row, return false;
			if (board[row][d] == num) {
				return false;
			}
		}

		// column has the unique numbers (column-clash)
		for (int r = 0; r < board.length; r++) {
			// if the number we are trying to
			// place is already present in
			// that column, return false;

			if (board[r][col] == num) {
				return false;
			}
		}

		// corresponding square has
		// unique number (box-clash)
		int sqrt = (int) Math.sqrt(board.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;

		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
			for (int d = boxColStart; d < boxColStart + sqrt; d++) {
				if (board[r][d] == num) {
					return false;
				}
			}
		}

		// if there is no clash, it's safe
		return true;
	}

	public static boolean solveSudoku(int[][] board, int n) {
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					row = i;
					col = j;

					// we still have some remaining
					// missing values in Sudoku
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) {
				break;
			}
		}

		// no empty space left
		if (isEmpty) {
			return true;
		}

		// else for each-row backtrack
		for (int num = 1; num <= n; num++) {
			if (isSafe(board, row, col, num)) {
				board[row][col] = num;
				if (solveSudoku(board, n)) {
					// print(board, n);
					return true;
				} else {
					board[row][col] = 0; // replace it
				}
			}
		}
		return false;
	}

	public static int[][] print(int[][] board, int N) {
		int[][] result = board;
		// we got the answer, just print it
		for (int r = 0; r < N; r++) {
			for (int d = 0; d < N; d++) {
				System.out.print(board[r][d]);
				System.out.print(" ");
			}
			System.out.print("\n");

			if ((r + 1) % (int) Math.sqrt(N) == 0) {
				System.out.print("");
			}
		}
		return result;
	}

	// Driver Code
	public static void main(String args[]) {

		int[][] board = new int[][] { { 3, 3, 6, 5, 0, 8, 4, 0, 4 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

		int N = board.length;
		if(isValidSudoku(board)){
			System.out.println("Validddddddddddddddd");
		}
		else
		{
			System.out.println("Not Validddddddd");
		}

		if (solveSudoku(board, N)) {
			print(board, N); // print solution
		} else {
			System.out.println("No solution");
		}
	}
	public static boolean isValidSudoku(int[][] board){
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

	public static boolean isValidSudoku(char[][] board) {
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

// This code is contributed
// by MohanDas
