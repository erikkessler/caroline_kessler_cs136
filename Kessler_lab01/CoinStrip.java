import java.util.Random;
import java.util.Scanner;
/*
 *Name: Caroline Kessler
 *Lab: noon-2 lab
 *
 *THOUGHT QUESTIONS:
 * 1. To pick game sizes so that one has a 50 percent chance of a game with three coins, 25 percent
 * chance of a game with four coins, 12.5 percent change of a game with five coins, etc. you would 
 * start with your variable numCoins (the number of coins) being set to three. You would then 
 * randomly choose a 0 or a 1. If a 0 is chosen, the game is of size three coins, otherwise you
 * increase numCoins by one and randomly choose 0 or 1 again. If a 0 is chosen, the game is of
 * size four coins, otherwise you repeat the random choosing until a 0 is selected. The underlying
 * data structure could still be an array as long as the array is created after the number of coins
 * has been determined. If the data structure is to be created as the number of coins is being 
 * chosen, a vector would be needed in order to support possible expansion.
 *
 * 2. To generate games that are not immediate wins, the board must contain at least one coin that
 * is displaced (not in its final position- for example coin zero's winning position would be the
 * zeroth element with no spaces to the left). To guarantee a game with the possibility of at least
 * n moves, the game must have n coins displaced. For example a board of -0-1---2 guarantees at 
 * least 3 moves, though there could be many more moves depending on how many spaces players
 * choose to move at a time.
 *
 * 3. Opportunities that might be easy for the computer to recognize for a player is when there is
 * a possiblity for the player to win within the next few turns by making a move that forces an 
 * even number of moves to follow. An even number of moves available after player one's move would
 * ensure that player one wins. For example if the board is 01-2-3 the computer would recognize
 * that player one should play 01-23 rather than 102-3 because the first move forces two 
 * subsequent moves whereas the second possibilty allows only one more move which would make
 * player 2 win. 
 *
 * 4. A method, computerPlay, where the computer plays to win would best be written as an array 
 * for which each index represents a coin and the value stored at the index is an int that
 * represents the coin's position on the board (an array of spaces does not allow the computer
 * to check options and understand the board easily). The method should aim to have an even 
 * number of subsequent moves after the computer's turn. An even number of subsequent moves would
 * mean that the computer would win. The computer would count the number of moves based on the 
 * number of coins that are displaced. This would be checked by seeing if the value in the array is
 * the same int as the index. The computer would first try to put a coin in a winning position and 
 * then check if that results in an even or odd number of subsequent moves. If it is an odd number
 * of moves the computer will make a move that puts two coins in consecutive positions. This would
 * be done by making a move, checking the number of displaced coins, and either reevaluating 
 * before sending the move, or sending the move. The goal for the computer would be to always have
 * an even number of coins displaced (until a winning move can be played).
 *
 * 5. If the game was changed so that coins were allowed to pass each other, it would change my
 * implementation because I would not want to use an array that holds the number of zeros to the 
 * left of each coin. This type of implementation relies on relative positions, which would make
 * it difficult to create the game because for each move, the board would essentially have
 * to be put together in some representation so that each space has a position and each coin 
 * has a position. It would be better for the array to store the position of the coin on the board
 * treating the board as though it is a number line. This would make it easy to check where a moved
 * coin would be relative to the other coins, and would make it easy to check if a position in the
 * board is already taken by a coin.
 *
 *
 * Class CoinStrip- This program uses integers chosen by the player to create a board with a
 * selected number of coins that have a randomly selected number of spaces in between them 
 * (the maximum number is determined by the player). The program then creates and array in which
 * each index represents a coin and the values contained in the array are the number of spaces
 * that exist to the left of the coin. A winning board will be an array that contains only zeros.
 * The program prompts for each player to give a coin to move and spaces to move, checks that
 * the move is valid, and executes if it is, then prompts the next player. If a move is invalid it 
 * prompts the player to try again. After each move it checks to see if it is a winning board.
 */

public class CoinStrip {

    int[] coins; // Array of spaces to left of coin
    int player = 0; // Keeps track of current player

    public static void main(String []args) {
	// Executes one full game by asking players for inputs to create the game and for moves
        Scanner myScanner = new Scanner(System.in);
	int numCoins;
	do {
	    System.out.println( "How many coins do you want (more than 3)?");
	    numCoins = myScanner.nextInt();
	} while (numCoins <= 3);

	System.out.println( "At most, how many spaces do you want between coins?" );
	int numSpaces = myScanner.nextInt();
	
	System.out.println("Here's your board");
	CoinStrip strip;

	// Make sure have an interesting board
	do {
	    strip = new CoinStrip(numCoins, numSpaces);
	} while (strip.isWinningBoard());

	System.out.println(strip);
        
	// Let's players take turns making moves by picking a coin and spaces to move
	// until winning board
	while (!strip.isWinningBoard()) {
	    int whichCoin;
	    int moves;

	    // Prompts the player for a coin to move and number of spaces to be moved as long as 
	    // the player doesn't provide valid inputs
	    do {
		System.out.println( "Player " + (strip.getPlayer() + 1) + ": What number coin do you want to move? (0-" + (numCoins-1)+")");
		whichCoin = myScanner.nextInt();
		System.out.println( "Player " + (strip.getPlayer() + 1) + ": How many spaces do you want to move left?");
		moves = myScanner.nextInt();

	    } while (!strip.isValid(whichCoin, moves));

	    strip.move(whichCoin, moves);

	    // Checks for winning board
	    if (strip.isWinningBoard()) {
		System.out.println("Player " + (strip.getPlayer() + 1) + " wins!");
	    }
	    System.out.println(strip);
	    strip.changePlayer();
	}
    }

    public CoinStrip(int numCoins, int numSpaces) {
	// Creates a game board using number of coins and spaces entered by user
        int size= numCoins;
	int spaces= numSpaces;
	Random spaceChooser = new Random();
	if (size > 3) {
	    coins = new int[size];
	    for (int x=0; x<size; x++) {
		coins[x]= spaceChooser.nextInt(spaces);
	    }
	} else {
	    System.out.println( "Invalid number of coins" );
	    System.exit(0);
	}
    }

    public String toString() {
	// Prints game board, showing spaces and coin numbers
	String result = "";
	for (int i = 0; i < coins.length; i++) {
	    int numSpaces = coins[i];
	    for (int j = 0; j < numSpaces; j++) {
		result += "-";
	    }
	    result += i;
	}
	return result;
    }

    public boolean isValid(int playCoin, int numMoves) {
	
	// Checks that coin to move exists
	if ( playCoin < 0 || playCoin >= coins.length) {
	    System.out.println("Invalid coin number, try again...");
	    return false;
	} 
	
	// Checks move is positive integer
	if (numMoves <= 0) {
	    System.out.println("Number of spaces must be positive, try again...");
	    return false;
	}

	// Checks enough spaces are available to move
	if (numMoves > coins[playCoin]) {
	    System.out.println("Spaces not available, try again...");
	    return false;
	}

	return true;
    }
    
    public void move(int playCoin, int numMoves) {
	// Assumes valid move
        // Decrements spaces available to left of coin
	    coins[playCoin] = coins[playCoin] - numMoves;

	if (playCoin != coins.length - 1) {
      	// Increments spaces available to right of coin
	    coins[playCoin + 1] = coins[playCoin + 1] + numMoves;
	    }
    }

    public boolean isWinningBoard() {
	// Checks if board contains only zeros
	for (int i=0; i < coins.length; i++) {
	    if (coins[i] != 0) {
		return false;
	    } 
	}	
	return true;
    }

    public int getPlayer() {
	// Returns current player
	return player;
    }
    public void changePlayer() {
	// Changes player, so game can be played by two players
	player = (player + 1) % 2;
    }
}
