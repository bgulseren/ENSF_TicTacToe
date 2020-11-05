/**
 * Implementation of the SmartPlayer Class for the Tic-Tac-Toe Game
 * 
 * @author B.Gulseren, K. Behairy
 * @version 1.0
 * @since October 19th, 2020
 */

public class SmartPlayer extends BlockingPlayer {

	/**
	 * Call's the Player Class Constructor
	 *
	 * @param name the name of the player to be constructed
	 * @param mark the mark of the player (either X or O) to be constructed
	 */
	public SmartPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Checks tiles on the board in order for a winning condition and
	 * places mark on the first one which satisfies it.
	 * 
	 * If no winning tile is found, then call's parent's class (blocking player) makeMove.
	 *
	 */
	protected void makeMove() {
		int row;
		int col;
		
		for (row = 0; row < 3; row++) {
			for (col = 0; col < 3; col++) {
				if (testForWinning(row, col)) {
					getBoard().addMark(row, col, getMark());
					System.out.println("Bot " + getName() + " placed " + getMark() + " at row: " + row + ", col: " + col + ".\n");
					return;
				}
			}
		}
	
		//no winning move found, then just use blocking move from parent class
		super.makeMove();

	}
	
	/**
	 * Checks the given (row, col) tile for a winning condition for the player.
	 * 
	 * @param row the row number of the tile to be checked
	 * @param col the col number of the tile to be checked
	 * @return true if specified tile creates a winning condition for the player, otherwise false.
	 */
	protected boolean testForWinning(int row, int col) {
		//create a test board for checking winning moves safely
		Board testBoard = new Board();
		
		//copy game board into test board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				testBoard.addMark(i, j, getBoard().getMark(i, j));
			}
		}
		
		//if selected row is already occupied then return false (no winning move since it is already occupied)
		if (testBoard.isMarked(row, col)) {
			return false;
		}
		
		// add player's mark to the test board see if it creates a winning condition for the this player
		testBoard.addMark(row, col, getMark());
		if (testBoard.checkWinner(getMark()) == 1) {
			return true;
		}
		
		//it did not create a winning condition, so return false
		return false;
		
		
	}

}
