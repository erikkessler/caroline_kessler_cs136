import java.util.Comparator;

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
}

class suComparator implements Comparator<Student> {

    // pre: a and b are valid objects
    // post: returns a value <, =, or > than 0
    // if a is less than, equal to, or greater than b
    public int compare(Student a, Student b) {
	return a.getSu().compareTo(b.getSu());
    }
}
