import java.util.Scanner;

public class WordGen{
    Scanner scan = new Scanner(System.in);
    String text = "";
    Table charTable = new Table();
    static int k = 0;

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
    
    public void readText() {
	// Reads text in and creates one big string to be parsed later
	StringBuffer textBuffer = new StringBuffer();
	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    textBuffer.append(line);
	    textBuffer.append("\n");
	}
	text = textBuffer.toString();
    }

    public void fillTable() {
	String sequence = "";
	for (int i = 0; i < text.length() - k; i++) {
	    sequence = text.substring(i, (i + k + 1));
	    charTable.add(sequence);
	}
	System.out.println(charTable.toString()); 
    }

    public String writeText(){
	String text = "The stuff: " + charTable.pickFirst();
	while (text.length() < 500) {
	    text += charTable.pickNext(text.substring(text.length() - k, text.length()));
	}
	return text;
    }
}
