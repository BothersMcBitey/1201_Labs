package queues;

import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
	
	private int head = 0; //add to head, points to first element
	private int tail = 0; //take from tail, points to space after last element
	private int space;
	private int[] queue;
	
	public CircularArrayQueue(int initialSize) {
		queue =  initialSize == 0 ? new int[10] : new int[initialSize];
		space = initialSize;
	}

	@Override
	public void enqueue(int in) {
		if(space == 0){
			int[] temp = new int[queue.length * 2];
			if(head < tail){
				for(int i = 0; i < queue.length; i++){
					temp[i] = queue[i];
				}				
			} else {
				int j =0;
				for(int i = head; i < queue.length; i++){
					temp[j] = queue[i];
					j++;
				}
				for(int i = 0; i < tail; i++){
					temp[j] = queue[i];
					j++;
				}
				head = 0;
				tail = j;
			}
			queue = temp;			
		}
		queue[tail]= in;
		tail = tail == (queue.length - 1) ? 0 : tail++;
		space = queue.length - 1;
	}

	@Override
	public int dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			
		}
		int x = queue[head];
		head = head == queue.length -1 ? 0 : head++;
		return x;
	}

	@Override
	public int noItems() {
		if(isEmpty()){
			return 0;
		} else {
			if(head < tail){
				return tail - head;
			} else {
				return queue.length - (head - tail);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return head == tail;
	}

}
