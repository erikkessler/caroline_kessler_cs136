import java.util.Comparator;
import structure5.*;

public class Student {
    String name;
    String address;
    long campus;
    int su;
    long cell;
    
    public Student(String stuName, String stuAddress,
			long stuCampus, int stuSu, long stuCell) {
	name = stuName;
	address = stuAddress;
	campus = stuCampus;
	su = stuSu;
	cell = stuCell;
    }

    public Integer getSu() {
	return su;
    }

    public String getName() {
	return name;
    }

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