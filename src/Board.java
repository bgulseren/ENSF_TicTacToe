/**
 * Implementation of the Board Class for the Tic-Tac-Toe Game
 * 
 * @author M.Morshirpour (modified by B.Gulseren, K. Behairy)
 * @version 1.0
 * @since October 19th, 2020
 */


public class Board implements Constants {
	/** the board, a character array which represents tile grid and marks on it */
	private char theBoard[][];
	/** used for storing the total number of marks placed onto the board so far */
	private int markCount;

	/**
	 * Board Class Constructor, clears theBoard by filling spaces in it and
	 * reset the mark counts.
	 *
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Returns the mark for the specified tile
	 * 
	 * @param row the row number of the tile to be checked for the mark
	 * @param col the column number of the tile to be checked for the mark
	 * @return the mark on the tile (either X, O or SPACE)
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	
	/**
	 * Returns true if specified tile has any X or O marks placed already.
	 * 
	 * @param row the row number of the tile to be checked for the mark
	 * @param col the column number of the tile to be checked for the mark
	 * @return true if specified tile has any X or O marks placed already.
	 */
	public boolean isMarked(int row, int col) {
		return (theBoard[row][col] != SPACE_CHAR);
	}

	/**
	 * Returns true if all the tiles of the board are fully marked.
	 * 
	 * @return true if all the tiles of the board are fully marked.
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Returns true if mark X satisfies the winning condition of the game
	 * 
	 * @return true means X won the game.
	 */
	public boolean xWins() {
		return (checkWinner(LETTER_X) == 1);
	}

	/**
	 * Returns true if mark O satisfies the winning condition of the game
	 * 
	 * @return true means O won the game.
	 */
	public boolean oWins() {
		return (checkWinner(LETTER_O) == 1);
	}

	/**
	 * Outputs the current status of the board to the console
	 * 
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Places the specified mark to the specified row and column on the board.
	 * Increments mark count by one after placement.
	 * 
	 * @param row the row number of the tile where the mark will be placed
	 * @param col the column number of the tile where the mark will be placed
	 * @param mark type of mark which will be placed to the tile
	 */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears the current board and fills all the tiles with spaces. Reset the mark count.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks the current status of the board if the given mark satisfies the winning condition.
	 * 
	 * @param the type of mark to be checked for the winning condition.
	 * @return 1: specified mark satisfies a winning condition, 0: specified mark does not satisfy any winning condition.
	 * 
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Outputs column headers like col1 col2 to the CLI. Used for visualizing the board on the CLI.
	 * 
	 * 
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/**
	 * Outputs hyphens in a formatted to the CLI. Used for visualizing the board on the CLI.
	 * 
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

	/**
	 * Outputs spaces in a formatted to the CLI. Used for visualizing the board on the CLI.
	 * 
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
