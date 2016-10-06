import java.util.Comparator;

public class suComparator implements Comparator<Student> {

    // pre: a and b are valid objects
    // post: returns a value <, =, or > than 0 if a is less than, equal to, or greater than b
    public int compare(Student a, Student b) {
	return a.getSu().compareTo(b.getSu());
    }
}
