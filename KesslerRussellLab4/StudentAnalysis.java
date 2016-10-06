import java.util.Scanner;
import structure5.*;
import java.util.Comparator;

public class StudentAnalysis {
    MyVector<Student> vector = new MyVector<Student>();
    
    public static void main(String []args) {
	MyVector<Student> vector = new MyVector<Student>();
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
	    vector.add(s); // creates MyVector of students
	}
	System.out.println(analyze.lowSu().getName() +
			   " " + analyze.lowSu().getSu());
    }

    public Student lowSu() {
	vector.sort(new suComparator());
	return vector.get(0);
    }
}
