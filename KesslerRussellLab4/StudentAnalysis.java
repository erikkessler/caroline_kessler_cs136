import java.util.Scanner;
import structure5.*;
import java.util.Comparator;

/*
 * Names: Eileen Russell and Caroline Kessler
 * Lab: noon-2 lab
 *
 * Class Student Analysis- This class reads in the information from the given phonebook and
 * adds each student to a MyVector of students. It also makes a MyVector of Associations that
 * includes area codes of phone numbers and the freuqency with which they occur. Using these 
 * vectors, the data is sorted and analyzed in different ways. The main method then prints out
 * the lowest SU box, the highest SU box, the name with the most vowels, the first name that 
 * would appear in a phone book (first name first), and the top ten most common area codes
 *
 */


public class StudentAnalysis {
    MyVector<Student> vector = new MyVector<Student>(); // Vector of students
    MyVector<Association<Integer, Integer>> codeVector = 
	new MyVector<Association<Integer,Integer>>(); // Vector to hold area code and frequencies

    // pre: Takes student data including name, SU box, two phone numbers, and an address
    public static void main(String []args) {
	StudentAnalysis analyze = new StudentAnalysis();
	Scanner in = new Scanner(System.in);
	while(in.hasNextLine()){
	    String name = in.nextLine();
	    String address = in.nextLine();
	    long campus = in.nextLong();
	    int su = in.nextInt();
	    long cell = in.nextLong();
	    in.nextLine();
	    in.nextLine();

	    Student s = new Student(name, address, campus, su, cell);
	    analyze.makeVector(s); // creates MyVector of students
	    analyze.areaCodeAnalysis(s); // creates MyVector of area code and frequencies
	}

	System.out.println(analyze.getInfoSu()); // highest and lowest SU box
	System.out.println(analyze.getVowelInfo()); // name with most vowels
	System.out.println(analyze.getFirstStudentInfo()); // first student printed in phonebook
	System.out.println(analyze.getAreaCodeInfo()); // top ten most common area codes
    }

    // pre: s must be of type Student, with all necessary information
    // post: makes a MyVector of all students
    public void makeVector(Student s) {
	vector.add(s);
    }

    // pre: vector.size() > 1
    // post: returns the Student with the lowest SU
    public Student lowSu() {
	// Sorts students by SU box number
	vector.sort(new suComparator());
	return vector.get(0);
    }

    // pre: vector.size() > 1
    // post: returns the student with the highest SU
    public Student highSu() {
	// Sorts students by SU box number
	vector.sort(new suComparator());
	return vector.get(vector.size() - 1);
    }

    // pre: vector.size() > 1
    // post: returns Student with most vowels in name
    public Student mostVowels() {
	// Sorts students by vowels in name
	vector.sort(new vowelComparator());
	return vector.get(vector.size() - 1);
    }

    // pre: vector.size() > 1
    // post: returns Student with name that would appear first alphabetically
    public Student firstStudent() {
	// Sorts students alphabetically
	vector.sort(new nameComparator());
	return vector.get(0);
    }

    // pre: Student s is valid Student with all information
    // post: creates MyVector of Associations of area codes and frequencies
    public void areaCodeAnalysis(Student s) {
	int code = s.getAreaCode();
	Association<Integer, Integer> check = new Association<Integer, Integer>(code, 1);
	for (int i = 0; i < codeVector.size(); i++) {
	    // Compares the current area code to all of those already contained in the vector
	    int compare = codeVector.get(i).getKey();
	    if (compare == code) {
		// Increments value of Assocation if area code exists
		check.setValue(codeVector.get(i).getValue() + 1);
		codeVector.remove(check);
	    }
	}
	// adds new Association with area code if it does not already exist
	codeVector.add(check);
    }

    // pre: i > 0 
    // post: returns Association at specified index of vector
    public Association mostAreaCode(int i) {
	// Sorts area codes based on frequency
	codeVector.sort(new areaCodeComparator());
	return codeVector.get(codeVector.size() - i);
    }

    // post: returns Student name with lowest SU and Student name with highest SU
    public String getInfoSu() {
	return "Lowest SU: " + lowSu().getName() + " " + lowSu().getSu() + "\n" +
	     "Highest SU: " + highSu().getName() + " " + highSu().getSu();
    }

    // post: returns Student name with greatest number of vowels in name
    public String getVowelInfo() {
	return "Highest Vowel Count: " + mostVowels().getName() + " ," 
	    + mostVowels().countVowel() + " vowels";
    }

    // post: returns Student name that appears first alphabetically
    public String getFirstStudentInfo() {
	return "First name in directory: " + firstStudent().getName();
    }

    // post: returns top ten most common area codes and their number of occurrences 
    public String getAreaCodeInfo() {
    	String result = "";
	for(int i = 1; i < 12; i++) {
	    if (!mostAreaCode(i).getKey().equals(0)) {
		// finds top ten most common valid area codes
	    result += "Area code " + mostAreaCode(i).getKey() + 
		" occurs " + mostAreaCode(i).getValue() + " times." + "\n";
	    }
	}
	return result;
    }
}
