import java.util.Comparator;
import structure5.*;
/*
 * Names: Eileen Russell and Caroline Kessler
 * Lab: noon-2 lab
 *
 * Class Student - Stores the name, address, campus phone, SU box, and cell number of the 
 * student. This class has methods to that return the name, SU box number, and count the number
 * of vowels in a students name.
 */

public class Student {
    String name;
    String address;
    long campus;
    int su;
    long cell;
    
    // pre: must be created with stuName, stuAddress, stuCampus, stuSu, and stuCell
    // post: initializes name, address, campus, su, and cell
    public Student(String stuName, String stuAddress,
			long stuCampus, int stuSu, long stuCell) {
	name = stuName;
	address = stuAddress;
	campus = stuCampus;
	su = stuSu;
	cell = stuCell;
    }

    // pre: SU number exists
    // post: returns Student SU number
    public Integer getSu() {
	return su;
    }

    // pre: name exists
    // post: returns Student name
    public String getName() {
	return name;
    }
    // pre: name.length() > 0
    // post: returns Integer of number of vowels in name
    public Integer countVowel() {
	Integer count = 0;
	for(int i = 0; i < name.length(); i++) {
	    if(name.charAt(i) == ('a') || name.charAt(i) == ('e') || 
	       name.charAt(i) == ('i') ||name.charAt(i) == ('o') || 
	       name.charAt(i) == ('u')) {
		count++;
		    }
	}
	return count;
    }

    // post: returns three digit int of area code for valid phone numbers
    public int getAreaCode() {
	String number = Long.valueOf(cell).toString();
	String code = "";
	if (number != "-1" && number.length() >= 3) {
	    // For valid phone numbers the String for first three numbers
	    code = number.substring(0,3);
	} 
	if (!code.equals("")) {
	    // turns area code from String into Integer
	    return Integer.parseInt(code);
	} else {
	    // returns 0 for invalid phone number
	    return 0;
	}
    }
}

// Compares two Student objects based on SU values
class suComparator implements Comparator<Student> {
    /* pre: a and b are valid objects
     * post: returns a value <, =, or > than 0
     * if a is less than, equal to, or greater than b
     */
    public int compare(Student a, Student b) {
	// Compares two Students based on SU number
	return a.getSu().compareTo(b.getSu());
    }
}

class vowelComparator implements Comparator<Student> {
    /* pre: a and b are valid objects
     * post: returns a value <, =, or > than 0
     * if a is less than, equal to, or greater than b
     */
    public int compare(Student a, Student b) {
	// Compares two Students based on the number of vowels in their name
	return a.countVowel().compareTo(b.countVowel());
    }
}

class nameComparator implements Comparator<Student> {
    /* pre: a and b are valid objects
     * post: returns a value <, =, or > than 0
     * if a is less than, equal to, or greater than b
     */
    public int compare(Student a, Student b) {
	// Compares two Students based on name
	return a.getName().compareTo(b.getName());
    }
}

class areaCodeComparator implements Comparator<Association<Integer,Integer>> {
    /* pre: a and b are valid objects
     * post: returns a value <, =, or > than 0
     * if a is less than, equal to, or greater than b
     */
    public int compare(Association<Integer,Integer> a, Association<Integer,Integer> b) {
	// Compares two Associations based on their frequency value
	return a.getValue().compareTo(b.getValue());
    }
}