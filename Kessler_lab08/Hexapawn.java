import java.util.Scanner;

/*
 * Name: Caroline Kessler
 * Lab: noon - 2 lab
 * Class Hexapawn- This class is the main class through which the game is run. It expects
 * four command-line arguments describing the width, height, and player type of two players.
 * It creates a new GameTree given these parameters and calls p1 to make the first move.
 */

public class Hexapawn {
    public static void main(String[] args) {
	// Prompts for correct command line arguments
	if (args.length < 4) {
	    System.out.println("Usage: java Hexapawn <width> <height> <p1: h,r,c> <p2: h,r,c>");
	    System.exit(0);
	}
	
	int width = Integer.parseInt(args[0]);
	int height = Integer.parseInt(args[1]);
	GameTree tree = new GameTree(new HexBoard(width,height), 
				     HexBoard.WHITE); //creates new GameTree
	Player p1 = null, p2 = null;
	
	// Initializes p1 and p2 with correct Player types
	if (args[2].equals("h")) {
	    p1 = new HumanPlayer();
	} else if (args[0].equals("r")) {
	    p1 = new RandomPlayer();
	} else {
	    p1 = new ComputerPlayer(tree);
	}

	if (args[3].equals("h")) {
	    p2 = new HumanPlayer();
	} else if (args[1].equals("r")) {
	    p2 = new RandomPlayer();
	} else {
	    p2 = new ComputerPlayer(tree);
	}
	
	p1.play(tree , p2);
    }

}