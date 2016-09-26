import structure5.*;
import java.util.Random;

/* Name: Caroline Kessler
 * Lab: noon-2 lab
 *
 * Class Table - This class is used in WordGen to store sequences of characters as an association
 * with a FrequencyList containing successor characters. It adds sequences to the vector and also
 * randomly selects a starting sequence from the vector, or uses a sequence in the vector to have
 * FrequencyList randomly generate a successor character
 */

public class Table {

    private Vector<Association<String, FrequencyList>> charTable;
    private FrequencyList listFrequency = new FrequencyList(); 

    public Table() {
	charTable = new Vector<Association<String, FrequencyList>>();
    }

    // pre: str length greater than 1
    // post: adds sequence to table and successor char to frequency list
    public void add (String str) {
	// Gets sequence of length k+1, checks to see if already in table
	String sequence = str.substring(0, str.length() - 1); // sequence for table
	char next = str.charAt(str.length()-1); // proceeding char
	Association<String, FrequencyList> match = 
	    new Association<String, FrequencyList>(sequence, null);
	Association<String, FrequencyList> current;
	int i; //Index into charTable

	for (i = 0; i < charTable.size(); i++){
	    // If sequence in table, checks FrequencyList for proceeding char and increments
	    // or adds to list as necessary
	    current = charTable.get(i);
	    if (current.equals(match)) {
		current.getValue().add(next);
		break;
	    }
	}
	if (i == charTable.size()) {
	    // Creates new association if charTable doesn't contain sequence
	    FrequencyList fl = new FrequencyList();
	    Association<String, FrequencyList> newSequence = 
		new Association<String, FrequencyList>(sequence, fl);
	    fl.add(next);
	    charTable.add(newSequence);
	}
    }

    // post: returns Table as String of sequences, successor characters, and occurrences
    public String toString() {
	// Uses associated frequency list to print occurences of each k+1 character sequence
	String occurrences = "";
	for (int i = 0; i < charTable.size(); i++) {
	    occurrences =  occurrences + "" + "|" + charTable.get(i).getKey() + "|\n" +
	        charTable.get(i).getValue().toString() + "\n";
	}
	return occurrences;
    }

    // post: returns String of random sequence
    public String pickFirst() {
	// Selects a random sequence of k characters already contained in the table
	Random randomString = new Random();
	int number = randomString.nextInt(charTable.size());
	String chosen = charTable.get(number).getKey();

	return chosen;
	
    }

    // pre: str length k
    // post: returns random String 
    public String pickNext(String str) {
	// Uses input string to randomly generate the (k+1)th character in the sequence
	String randomString = ""; // String to be returned
	Association<String, FrequencyList> match = 
	    new Association<String, FrequencyList>(str, null); //Association with key for searching
	Association<String, FrequencyList>current; // Empty association to use to compare
	int i; // Index into charTable

	for ( i = 0; i < charTable.size(); i++) {
	    current = charTable.get(i);
	    if (current.equals(match)) {
		randomString = "" + current.getValue().pickNext();
		break;
	    }
	}
	if (i == charTable.size()) {
	    // If no known successor character to sequence, randomly generate sequence using 
	    // pickFirst()
	    randomString = pickFirst();
	}
	return randomString;
    }
}
