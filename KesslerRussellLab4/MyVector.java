import structure5.*;
import java.util.Comparator;

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
		//if (vector.get(max).getSu() < vector.get(index).getSu()) {
		    max = index;
		}
	    }
	    swap(max, numUnsorted-1);
		numUnsorted--;
	}
    }

    public void swap(int i, int j) {
	E temp;
	temp = get(i);
	setElementAt(get(j),i);
	setElementAt(temp,j);
    }
}
