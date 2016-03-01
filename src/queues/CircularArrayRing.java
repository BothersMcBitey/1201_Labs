package queues;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<E> extends AbstractCollection<E> implements Ring<E> {

	Object[] array;
	int head = 0;
	int noItems = 0;
	
	public CircularArrayRing() {
		this(10);
	}
	
	public CircularArrayRing(int size) {
		array = new Object[size];
	}
	
	public boolean add(E e){
		array[head] = e;
		head = (head + 1) % array.length;
		noItems += (noItems < array.length) ? 1 : 0;
		return true;
	}
	
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if((index+1) > noItems || index < 0) throw new IndexOutOfBoundsException();
		return (head-1-index >= 0) ? (E)array[head-1-index] : (E) array[array.length + (head-1-index)];
	}

	@Override
	public Iterator<E> iterator() {		
		return new Iterator<E>() {

			int itCount = 0;
			
			@Override
			public boolean hasNext() {
				if(itCount >= noItems)return false;
				return true;
			}

			@Override
			public E next() throws NoSuchElementException{
				if(!hasNext()) throw new NoSuchElementException();
				E ret;
				int i = (head-1 - itCount > 0) ? head-1 - itCount : array.length + (head-1 - itCount);
				ret = (E)array[i];
				itCount++;
				return ret;
			}
			
			@Override
			public void remove() throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public int size() {		
		return noItems;
	}

}
