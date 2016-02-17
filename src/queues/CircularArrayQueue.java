package queues;

import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
	
	private int head = 0; //add to head, points to next free space
	private int tail = 0; //take from tail, points to last element added
	private int space;
	private int[] queue;
	
	public CircularArrayQueue(int initialSize) {
		queue =  initialSize == 0 ? new int[10] : new int[initialSize];
		space = initialSize == 0? 10 : initialSize;
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
				space = temp.length - queue.length;
			}
			queue = temp;			
		}
		queue[tail]= in;
		tail = tail == (queue.length - 1) ? 0 : tail + 1;
		space--;
	}

	@Override
	public int dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int x = queue[head];
		head = (head == queue.length - 1) ? 0 : head + 1;
		space++;
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
	
	public int getCapacityLeft(){
		return space;
	}

	@Override
	public boolean isEmpty() {
		return head == tail && space != 0;
	}

}
