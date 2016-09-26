import structure5.*;
import java.util.Random;

/* Name: Caroline Kessler
 * Lab: noon-2 lab
 *
 * Class FrequencyList- Given a char from Table, this class adds the char to a vector of 
 * Associations between a Character and an Integer to keep track of the frequency of the 
 * character. It also randomly generates a character based on the associated frequencies stored in
 * in the vector.
 */

public class FrequencyList{
    
    protected  Vector<Association<Character, Integer>> charFreq; // Vector to store Characters
    private  int total; // Total chars added to the list

    // post: initializes the Vector of associations
    public FrequencyList() {
	charFreq= new Vector<Association<Character, Integer>>();
    }

    // pre: takes single char
    // post: creates new association with char or increases frequency if char already in vector
    public void add(char ch){
	// Check if charFreq contains character
	Association<Character, Integer> match =
	    new Association<Character, Integer>( ch, null); //Null Association with appropriate key
	Association<Character, Integer> current; // Empty association to use to check for match
	int i; // index in to charFreq

	for (i = 0; i < charFreq.size(); i++) {
	    // Increments frequency by one, if charFreq contains character
	    current = charFreq.get(i);
	    if (current.equals(match)){
		Integer f = current.getValue();
		current.setValue(new Integer (f + 1));
		total += 1;
		break;
	    }
	} 

	if (i == charFreq.size()) {
	    // Creates a new association if charFreq doesn't contain character
	    Association<Character, Integer> newChar =
		new Association<Character, Integer>(ch, 1);
	    charFreq.add(newChar);
	    total += 1;
	}
    }

    // post: returns String containing Association keys and their values
    public String toString(){
	String occurrences = "";
	for (int i = 0; i < charFreq.size(); i++) {
	    occurrences = occurrences + charFreq.get(i).getKey() + " occurs "
		+ charFreq.get(i).getValue() + " times" + "\n";
	}
	return occurrences;
    }

    // post: returns randomly chosen char from vector
    public char pickNext() {
	// Randomly picks a char based on frequencies in list
	Random randomChar = new Random();
	char chosen = '!'; // Empty char that will be hold the random char
	int currentPos = 0; // Represents relative frequencies of each char
	int number = randomChar.nextInt(total) + 1; // Randomly chosen number

	for (int i = 0; i < charFreq.size(); i++) {
	    currentPos += charFreq.get(i).getValue();
   	    if (number <= currentPos) {
		chosen = charFreq.get(i).getKey();
		break;
	    }
	}
	return chosen;
    }
}
