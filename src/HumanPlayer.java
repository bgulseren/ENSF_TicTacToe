import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of the HumanPlayer Class for the Tic-Tac-Toe Game
 * 
 * @author B.Gulseren, K. Behairy
 * @version 1.0
 * @since October 19th, 2020
 */

public class HumanPlayer extends Player {

	/**
	 * Call's the Player Class Constructor
	 *
	 * @param name the name of the player to be constructed
	 * @param mark the mark of the player (either X or O) to be constructed
	 */
	public HumanPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Checks whether player can play or not. If player can play, calls the makeMove method
	 * and then passes the turn to the next player. Otherwise, it outputs the result of the
	 * game (winner or tie) to the console.
	 *
	 * @throws IOException Downstream Player makeMove method uses standard input to read lines
	 * from the CLI, which can throw an exception.
	 */
	protected void makeMove() throws IOException {
		int row, col;
		BufferedReader stdin;
		stdin = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.print(this.getName() + ", what row should your next " + this.getMark() + " be placed in?");
			String entryRow = stdin.readLine();
			
			while (entryRow == null) {
				System.out.print("Please try again: ");
				entryRow = stdin.readLine();
			}
			
			System.out.print(this.getName() + ", what column should your next " + this.getMark() + " be placed in?");
			String entryCol = stdin.readLine();
			
			while (entryCol == null) {
				System.out.print("Please try again: ");
				entryCol = stdin.readLine();
			}
			
			try {
				row = Integer.parseInt(entryRow); //can throw a number format exception if entry was not a valid integer
				col = Integer.parseInt(entryCol);
				
				if (row > 2 || row < 0 || col > 2 || col < 0) {
					System.out.println("Row/Column must be between 0 and 2, please try again!");
				} else if (getBoard().isMarked(row, col)) { //check if the coordinates are already marked
					System.out.println("Tile already marked, please try again!");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("Row and column need to be entered as valid integers, please try again!");
			}
			

		}

		getBoard().addMark(row, col, getMark());
	}

}
