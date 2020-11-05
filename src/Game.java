
import java.io.*;

/**
 * Implementation of the Game Class for the Tic-Tac-Toe Game.
 * Has the main method which instantiates the board, the players and the referee.
 * Prompts the user from the CLI to enter each players' name.
 * Then appoints the referee which will invoke referee methods to get the game going.
 * 
 * @author M.Morshirpour (modified by B.Gulseren, K.Behairy)
 * @version 1.0
 * @since October 19th, 2020
 */


public class Game implements Constants {

	/** the board object where the game will be played */
	private Board theBoard;
	
	/** the referee object which will conduct (consist the backend) the game */
	private Referee theRef;
	
	/**
	 * Game Class Constructor, instantiates a new board to be played on.
	 *
	 */
    public Game() {
        theBoard  = new Board();
	}
    
	/**
	 * Appoints the referree, which consist the entry point to the game application.
	 * 
	 * @param r the referee object to be appointed for the game.
	 * @throws IOException Downstream Player makeMove method uses standard input to read lines
	 * from the CLI, which can throw an exception.
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }
	
	/**
	 * Main method, which instantiates the board, the players and the referee.
	 * Prompts the user from the CLI to enter each players' name.
	 * Then appoints the referee which will invoke referee methods to get the game going.
	 * 
	 * @param args system arguments
	 * @throws IOException uses standard input to read lines from the CLI, which can throw an exception.
	 */
	public static void main(String[] args) throws IOException {
		
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = create_player (name, LETTER_X, theGame.theBoard, stdin);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = create_player (name, LETTER_O, theGame.theBoard, stdin);
		
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}
	
	/**
	 * Creates the specified type of player indicated by the user. 
	 * 
	 * @param name player's name
	 * @param mark player's mark (X or O)
	 * @param board refers to the game board
	 * @param stdin refers to an input stream
	 * @return a newly created player
	 * @throws IOException
	 */
	public static Player create_player(String name, char mark, Board board, BufferedReader stdin) throws IOException {
		// Get the player type.
		final int NUMBER_OF_TYPES = 4;
		System.out.print ( "\nWhat type of player is " + name + "?\n");
		System.out.print(	"  1: Human\n" + 
							"  2: Random Player\n" +
							"  3: Blocking Player\n" +
							"  4: Smart Player\n");
		
		System.out.print( "Please enter a number in the range 1-" + NUMBER_OF_TYPES + ": ");
		int player_type = 0;

		String input;
		stdin = new BufferedReader(new InputStreamReader(System.in));
		input= stdin.readLine();
		player_type = Integer.parseInt(input);
		while (player_type < 1 || player_type > NUMBER_OF_TYPES) {
			System.out.print( "Please try again.\n");
			System.out.print ( "Enter a number in the range 1-" +NUMBER_OF_TYPES + ": ");
			input= stdin.readLine();
			player_type = Integer.parseInt(input);
		}
		
		// Create a specific type of Player 
		Player result = null;
		switch(player_type) {
			case 1:
				result = new HumanPlayer(name, mark);
				break;
			case 2:
				result = new RandomPlayer(name, mark);
				break;
			case 3:
				result = new BlockingPlayer(name, mark);
				break;
			case 4:
				result = new SmartPlayer(name, mark);
				break;
			default:
				System.out.print ( "\nDefault case in switch should not be reached.\n"
				+ "  Program terminated.\n");
				System.exit(0);
		}
		result.setBoard(board);
		return result;
	}
}
