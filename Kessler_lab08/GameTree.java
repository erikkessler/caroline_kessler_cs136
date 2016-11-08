import structure5.*;

/*
 * Name: Caroline Kessler
 * Lab: noon-2 lab
 * Class GameTree- This class recursively creates a Gametree built of smaller GameTrees which 
 * represents all of the possible moves that can be made for the given board size. The children of
 * a node are stored in a vector of GameTrees.In creating the GameTree, the different levels are
 * alternatingly assigned to a player in order to represent the back and forth playing of the 
 * game. The class contains two methods size and toString which return the number of moves in the
 * tree and print each of the moves in the tree respectively.
 */

public class GameTree {
    Vector<GameTree> children = new Vector<GameTree>();
    HexBoard current;
    char player;

    /*
     * pre: board and player are valid
     * post: returns GameTree containing all moves for game
     */
    public GameTree(HexBoard board, char player) {
	this.player = player;
	Vector<HexMove> moves = new Vector<HexMove>(); // vector of all moves with given board
	current = board;
	moves = current.moves(player);
	if (!current.win(HexBoard.opponent(player))) {
	    for (int i = 0; i < moves.size(); i++) {
		HexBoard nextBoard = new HexBoard(current, moves.get(i));
		GameTree child = new GameTree(nextBoard, current.opponent(player));
		children.add(child); // adds move GameTree to vector to create node's children
	    }
      	}
    }
    // Returns current player
    public char getPlayer() {
	return player;
    }

    // Test that GameTree is created correctly with proper size
    public static void main(String args[]) {
	/*
	HexBoard board = new HexBoard();
	GameTree tree = new GameTree(board, board.WHITE);
	System.out.println(tree.toString());
	System.out.println(tree.size());
	*/

	// Thought Question 1
	HexBoard newBoard = new HexBoard(3,5);
	GameTree newTree = new GameTree(newBoard, HexBoard.WHITE);
	System.out.println(newTree.size());

    }

    // Returns String of HexBoards using level order tree traversal
    public String toString() {
	Queue<GameTree> toVisit = new QueueList<GameTree>();
	String boards = "";
	toVisit.enqueue(this);
	while (!toVisit.isEmpty()){
	    GameTree recent = toVisit.dequeue();
	    for (GameTree child : recent.children) {
		toVisit.enqueue(child);
	    }
	    boards = boards + recent.current.toString();
	}
	return boards;
	
    }
    
    // Returns the number of nodes contained in the GameTree
    public int size() {
	Queue<GameTree> toVisit = new QueueList<GameTree>();
	int count = 0;
	toVisit.enqueue(this);
	while (!toVisit.isEmpty()) {
	    GameTree recent = toVisit.dequeue();
	    for (GameTree child : recent.children) {
		toVisit.enqueue(child);
	    }
	    count++;
	}
	return count;
    }
}