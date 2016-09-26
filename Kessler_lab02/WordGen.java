import java.util.Scanner;

/* Name: Caroline Kessler
 * Lab: noon-2 lab
 *
 * QUESTIONS:
 * SCP 3.2) The add(v) method of Vector adds the element (v) to the end of the vector in the next
 * available spot. On the other hand, the add(i, v) method shifts all elements at and after index  
 * i to the right (increases indicies), then adds the specified element (v) at the now empty index
 * i. So, while the add(i, v) method allows an element to be placed anywhere in
 * the vector and forces other elements to be moved, the add(v) method can only add the element
 * to the next available spot in the vector and doesn't cause any other elements to be moved.
 *
 * SCP 3.3) The set(i, v) method replaces the element currently stored in the vector at index i
 * with the specified element (v), and thus the previous element stored at index i is no longer
 * held in the vector. So, while the add(i, v) method retains all current information in the 
 * vector by moving all elements at and after index i to the right and then adding the new 
 * element, the set(i, v) method replaces current information in the vector, the element at index
 * i, with the new specified element (v).
 *
 * SCP 3.4) The remove(v) method uses the equals method is used to compare the Object (v) to
 * existing elements in the vector to find the first occurrence of the object. If the object is
 * found in the vector, the object is removed from that index and the size of the vector 
 * decreases by one (all succeeding elements are shifted left). The remove(i) method finds the 
 * element at index i and removes it from the vector, again decreasing the size of the vector by 
 * one (moving all succeeding elements to the left). The remove(v) method looks to remove a 
 * certain object that may (or may not) be contained at any index, while the remove(i) method
 * removes the element at the specified index regardless of what element is contained there.
 *
 * P 3.6) Having a specialty vector like BitVector (with all values stored as booleans)is 
 * advantageous because it allows the user to override Vector methods with methods that
 * are better suited for and specific to booleans. This makes the class more efficient and better
 * suited for the user's purposes.
 *
 * Class WordGen- This class reads in text from a source and takes in an int k from the user. It 
 * then parses the text into sequences k characters long and stores those sequences in a table. 
 * Using that data the class then generates a new text with relative sequence and character 
 * frequencies.  
 */
public class WordGen{

    private  String text = ""; // String to hold input text
    private  Table charTable = new Table(); // Table for sequences and successor chars
    static int k = 0; // Number of characters in sequences

    // pre: requires input for k and input text to be used
    // post: returns new text based on input text character frequencies
    public static void main (String args[]) {
	if (args.length == 0) {
	    // no args, so print usage line and do nothing else
	    System.out.println("Usage: java WordGen <level>");
	} else {
	    // convert first argument to k
	    k = Integer.parseInt(args[0]);
	}
	
	WordGen generator = new WordGen();
	generator.readText();
	generator.fillTable();
	System.out.println(generator.writeText());
    }
    
    // post: creates one String containing input text
    public void readText() {
	// Reads text in and creates one big string to be parsed later
	Scanner scan = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();
	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    textBuffer.append(line);
	    textBuffer.append("\n");
	}
	text = textBuffer.toString();
    }

    // post: builds Table of sequences from input text
    public void fillTable() {
	// Fills table of sequences of length k  and associated frequencies based on text 
	String sequence = "";
	for (int i = 0; i < text.length() - k; i++) {
	    sequence = text.substring(i, (i + k + 1));
	    charTable.add(sequence);
	}
    }

    // post: returns String of randomly generated text
    public String writeText(){
	// Prints text using randomly generated sequences based on input text char frequencies
	String text = "\n" +  charTable.pickFirst();
	while (text.length() < 500) {
	    text += charTable.pickNext(text.substring(text.length() - k, text.length()));
	}
	return text;
    }
}
