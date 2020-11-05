/**
 * Implementation of the RandomPlayer Class for the Tic-Tac-Toe Game
 * 
 * @author B.Gulseren, K. Behairy
 * @version 1.0
 * @since October 19th, 2020
 */

public class RandomPlayer extends Player {

	/**
	 * Call's the Player Class Constructor
	 *
	 * @param name the name of the player to be constructed
	 * @param mark the mark of the player (either X or O) to be constructed
	 */
	public RandomPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Places the player's mark on a random available tile.
	 * 
	 */
	protected void makeMove() {
		int row, col;
		RandomGenerator rnd = new RandomGenerator();
		
		while (true) {
			
			row = rnd.discrete(0, 2); //get a random value between 0 and 2
			col = rnd.discrete(0, 2); //get a random value between 0 and 2
			
			if (!getBoard().isMarked(row, col)) { //check if the coordinates are already marked
				break; //tile is available so break the loop
			}
		}

		// place the mark
		getBoard().addMark(row, col, getMark());
		System.out.println("Bot " + getName() + " placed " + getMark() + " at row: " + row + ", col: " + col + ".\n");
	}

}
