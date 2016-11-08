import java.util.Random;
import structure5.*;

/*
 * Name: Caroline Kessler
 * Lab: noon-2 lab
 * Class Random Player- This class receives a GameTree from the opponent, checks if the opponent's
 * move was a win- if it was a win, the game ends. If it was not a win, the player randomly
 * chooses a legal next move based on the GameTree and passes it to the opponent, prompting the
 * opponent to make a move.
 */

public class RandomPlayer implements Player {
    Random random = new Random();

    /*
     * pre: GameTree is non null, opponent is a valid Player
     * post: Exits the game if the opponent's move resulted in a win, else randomly chooses
     * a legal move from the GameTree and passes to the opponent.
     */
    public Player play(GameTree node, Player opponent) {
	// Checks if opponents previous turn won the game
	if (node.current.win(HexBoard.opponent(node.getPlayer()))) {
	    System.out.println("Player " + HexBoard.opponent(node.getPlayer()) + " wins!");
	    System.exit(0);
	    return null;
	} else {
	    Vector<GameTree> children = node.children;
	    int move = random.nextInt(children.size());
	    System.out.println("***RANDOM MOVE*** \n" + children.get(move).current);
	    return opponent.play(children.get(move), this);
	}
    }
}