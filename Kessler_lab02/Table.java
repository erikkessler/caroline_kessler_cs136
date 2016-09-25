import structure5.*;
import java.util.Random;

public class Table {

    Vector<Association<String, FrequencyList>> charTable;
    private FrequencyList listFrequency = new FrequencyList();

    public static void main (String args[]) {
	//Table table = new Table("banana banana", 2);
	//Table table = new Table("the theater is their thing", 2);
	//	System.out.println(table.toString());
	//System.out.println(table.pickNext("th"));
    } 

    public Table() {
	charTable = new Vector<Association<String, FrequencyList>>();
	//String sequence; // Sequence to store in table

	//	for (int i = 0; i < (text.length() - k); i++) {
	    // sequence = text.substring(i, (i + k + 1));

	    // add(sequence);
	//}
    }

    public void add (String str) {
	// Gets sequence of length k+1, checks to see if already in table
	String sequence = str.substring(0, str.length() - 1); // sequence for table
	char next = str.charAt(str.length()-1); // proceeding char
	Association<String, FrequencyList> match = 
	    new Association<String, FrequencyList>(sequence, null);
	Association<String, FrequencyList> current;
	int i; //Index into charTable

	for (i = 0; i < charTable.size(); i++){
	    current = charTable.get(i);
	    // If sequence in table, checks FrequencyList for proceeding char and increments
	    // or adds to list as necessary
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

    public String toString() {
	// Uses associated frequency list to print occurences of each k+1 character sequence
	String occurrences = "";
	for (int i = 0; i < charTable.size(); i++) {
	    occurrences =  occurrences + "" + "|" + charTable.get(i).getKey() + "|\n" +
	        charTable.get(i).getValue().toString() + "\n";
	}
	return occurrences;
    }

    public String pickFirst() {
	Random randomString = new Random();
	int number = randomString.nextInt(charTable.size());
	String chosen = charTable.get(number).getKey();
	System.out.println(chosen);
	return chosen;
	
    }

    public char pickNext(String str) {
	// Uses input string to randomly generate the (k+1)th character in the sequence
	char randomChar = '!'; // char to be returned
	Association<String, FrequencyList> match = 
	    new Association<String, FrequencyList>(str, null); //Association with key for searching
	Association<String, FrequencyList>current;

	for (int i = 0; i < charTable.size(); i++) {
	    current = charTable.get(i);
	    if (current.equals(match)) {
		randomChar = current.getValue().pickNext();
		break;
	    }
	}
	return randomChar;
    }
}
