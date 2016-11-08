import java.util.Scanner;
import structure5.*;

/*
 * Name: Caroline Kessler
 * Lab: noon-2 lab
 * Class HumanPlayer- This class allows a user to play the Hexapawn game. The class receives
 * the current GameBoard and presents the next possible moves to the user, telling them to select
 * one move. This selection is then passed to the opponent. The class checks for an opponent's
 * win when it first receives the GameTree.
 */

public class HumanPlayer implements Player {
    static Scanner scanner = new Scanner(System.in);

    /*
     * pre: GameTree is non null, player is valid
     * post: exits the game if opponent had winning move, offers user next possible move,
     * passes GameTree to opponent based on user's selection
     */
    public Player play(GameTree node, Player opponent) {
	System.out.println("***CURRENT BOARD***\n\n" + node.current.toString());
	if (node.current.win(HexBoard.opponent(node.getPlayer()))) {
	    System.out.println("Player " + HexBoard.opponent(node.getPlayer()) + " wins!");
	    System.exit(0);
	    return null;
	} else {
	    System.out.println("***SELECT MOVE***");
	    Vector<GameTree> children = node.children;
	    for (int i = 0; i < children.size(); i++) {
		System.out.println(i + ". \n" + children.get(i).current);
	    }
	    int move = scanner.nextInt();
	    return opponent.play(children.get(move), this);
	}
    }
}