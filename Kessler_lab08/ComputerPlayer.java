import structure5.*;
import java.util.Random;
import java.util.Scanner;

/*
 * Name: Caroline Kessler
 * Lab: noon to 2 lab
 *
 * Class ComputerPlayer- This class is a version of a player that makes random (legal) moves
 * based on the GameTree. It is a smart player because it prunes the GameTree when it loses. 
 * The move that the ComputerPlayer played before the opponent made a move and won is stored 
 * and then removed from the tree once ComputerPlayer determines that it has lost.
 *
 */
public class ComputerPlayer implements Player {
    GameTree start;
    Vector<GameTree> lastChildren;
    int lastMove;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public ComputerPlayer(GameTree start) {
	this.start = start; // saves original GameTree
    }

    /*
     * pre: Node is non null GameTree
     * post: Prunes tree and asks to play again if opponent has won,
     * else makes a random, legal move and calls opponent to make move
     */
    public Player play(GameTree node, Player opponent) {
	if (node.current.win(HexBoard.opponent(node.getPlayer()))) {
	    System.out.println("Player " + HexBoard.opponent(node.getPlayer()) + " wins!");
	    if (lastChildren.size() > 1) {
		// only prunes tree if there children is greater than 1, prevents crashing
	    lastChildren.remove(lastMove); // Prune losing move
	    }
	    System.out.println("Play again? (y/n)");
	    if (scanner.next().equals("y")) {
		if (node.getPlayer() == HexBoard.WHITE) {
		    return play(start, opponent); // This player was p1, so goes first again
		} else {
		    return opponent.play(start, this); // Opponent was p1, so they go first
		}
	    } else {
		System.exit(0);
		return null;
	    }
	} else {
	    lastChildren = node.children; // Save all possible moves for turn
	    lastMove = random.nextInt(lastChildren.size()); // Save move taken
	    System.out.println("***COMPUTER MOVE*** \n" + lastChildren.get(lastMove).current);
	    return opponent.play(lastChildren.get(lastMove), this);
	}    
    }
}