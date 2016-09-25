import structure5.*;
import java.util.Random;

public class FrequencyList{
    
    Vector<Association<Character, Integer>> charFreq;
	//new Vector<Association<Character, Integer>>;
    int total; // Total chars added to the list

    public static void main (String args[]) {
	System.out.println("Test");
	FrequencyList list = new FrequencyList();
	list.add('a');
	list.add('a');
	list.add('b');
	System.out.println(list.toString());
	System.out.println(list.pickNext());
    }

    public FrequencyList() {
	charFreq= new Vector<Association<Character, Integer>>();
    }

    public void add(char ch){
	// Check if charFreq contains character
	Association<Character, Integer> match =
	    new Association<Character, Integer>( ch, null);
	Association<Character, Integer> current;
	
	int i; // index in to charFreq

	for (i = 0; i < charFreq.size(); i++) {
	    current = charFreq.get(i);
	    // Increments frequency by one, if charFreq contains character
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

    public String toString(){
	String occurrences = "";
	for (int i = 0; i < charFreq.size(); i++) {
	    occurrences = occurrences + charFreq.get(i).getKey() + " occurs "
		+ charFreq.get(i).getValue() + " times in FL." + "\n";
	}
	return occurrences;
    }

    public char pickNext() {
	// Randomly picks a char based on frequencies in list
	Random randomChar = new Random();
	char chosen = 'a';
	int currentPos = 0;
	int number = randomChar.nextInt(total) + 1;

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
