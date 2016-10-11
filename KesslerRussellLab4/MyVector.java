import structure5.*;
import java.util.Comparator;

/*
 * Names: Eileen Russell and Caroline Kessler
 * Lab: noon-2 lab
 *
 * Class MyVector - This class extends Vector to include a sort method (selection sort) as well
 * as a swap method.
 */

public class MyVector<E> extends Vector<E> {
    public MyVector() {
	super();
    }

    // pre: c is a valid comparator
    // post: sort this vector in order determined by c
    public void sort (Comparator<E> c) {
	int numUnsorted = size();
	int index;
	int max;
	while (numUnsorted > 0) {
	    max = 0;
	    for (index = 1; index < numUnsorted; index++) {
		if (c.compare(get(index),get(max)) > 0) {
		    // sets max to value of index is element at index is greater than 
		    // element at max
		    max = index;
		}
	    }
	    swap(max, numUnsorted-1);
		numUnsorted--;
	}
    }

    // pre: i and j < MyVector.length()
    // post: Switches the elements at indices i and j
    public void swap(int i, int j) {
	E temp;
	temp = get(i);
	setElementAt(get(j),i);
	setElementAt(temp,j);
    }
}
