/**
 * Implementation of the BlockingPlayer Class for the Tic-Tac-Toe Game
 * 
 * @author B.Gulseren, K. Behairy
 * @version 1.0
 * @since October 19th, 2020
 */

public class BlockingPlayer extends RandomPlayer {

	/**
	 * Call's the Player Class Constructor
	 *
	 * @param name the name of the player to be constructed
	 * @param mark the mark of the player (either X or O) to be constructed
	 */
	public BlockingPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Checks tiles on the board in order for a blocking condition and
	 * places mark on the first one which satisfies it.
	 * 
	 * If no blocking tile is found, then call's parent's class (random player) makeMove.
	 *
	 */
	protected void makeMove() {
		int row;
		int col;
		
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
				if (testForBlocking(row, col)) {
					getBoard().addMark(row, col, getMark());
					System.out.println("Bot " + getName() + " placed " + getMark() + " at row: " + row + ", col: " + col + ".\n");
					return;
				}
			}
		}
	
		//no blocking move found, then just use random move from parent class
		super.makeMove();

	}
	
	/**
	 * Checks the given (row, col) tile for a blocking condition for the opponent.
	 * 
	 * @param row the row number of the tile to be checked
	 * @param col the col number of the tile to be checked
	 * @return true if specified tile creates a blocking condition for the opponent, otherwise false.
	 */
	protected boolean testForBlocking(int row, int col) {
		Board testBoard = new Board();
		
		//copy game board into test board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				testBoard.addMark(i, j, getBoard().getMark(i, j));
			}
		}
		
		//if selected row is already occupied then return false (no blocking move since it is already occupied)
		if (testBoard.isMarked(row, col)) {
			return false;
		}
		
		// add opponent's mark to see if it creates a winning condition for the opponent
		char oppMark = getOpponent().getMark();
		testBoard.addMark(row, col, oppMark);
		if (testBoard.checkWinner(oppMark) == 1) {
			return true; //the opponent won the game if they placed the mark here, so return true to block it.
		}
		//it did not create a winning condition, so return false
		return false;
		
		
	}
}
