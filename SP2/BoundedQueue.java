
package mxd180035;

import java.util.Scanner;

/**
*  BoundedQueue class 
* 
* 
* @author Mani Chandana Dyda(MXD180035)
*
* @version 1.0
*
* @param <T> the type of elements held in this collection.
*/

public class BoundedQueue<T> {

	private int bound; // max items the queue can store
	private int size =0; // size of queue
	private int pointer =0; // position of first element of queue

	private T[] queue; // array to store queue elements


	/**
	 * Constructs an Object of BoundedQueue instance.
	 * 
	 * @param size max items the queue can store
	 */
	public BoundedQueue(int size) {
		super();
		this.bound = size;
		queue = (T[]) new Object[size];
	}


	/**
	 * Inserts the specified element into Bounded queue.
	 * 
	 * @param x the element to add
	 * @return true if element is added queue, false if the size limit exceeds
	 * 
	 */
	public boolean offer(T x) {

		if(size == bound) {
			return false;
		}

		int index =(pointer + size)% bound;
		queue[index] = x;
		this.size++;

		return true;
	}

	/**
	 * Retrieves and removes the head of this queue,or returns null if this queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 */
	public T poll() {

		if(size>0) {
			T elem = queue[pointer];
			this.size--;
			pointer = (++pointer)%bound;

			return elem;
		}

		return null;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,or returns null if this queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 */
	public T peek() {

		if(size>0) {

			return queue[pointer];
		}

		return null;
	}

	/**
	 * Returns the number of elements in this queue.
	 * 
	 * @return the number of elements in this collection
	 */
	public int size() {

		return this.size;
	}

	/**
	 * Returns true if this collection contains no elements
	 * 
	 * @return true if this collection contains no elements
	 */
	public boolean isEmpty() {

		return (this.size==0);
	}

	/**
	 * Removes all of the elements from this priority queue.The queue will be empty after this call returns.
	 */
	public void clear() {

		this.size =0;
		this.pointer =0;
	}

	/**
	 * Inserts all of the elements in this queue into an array.   
	 * @param a the array into which the elements of the queue are to be stored.
	 */
	public void toArray(T[] a) {
		if(a.length<size)
			System.out.println("copying only those elements that fit into the array");
		
		int length = Math.min(a.length, size);
		
		int index = pointer;
		for(int i =0;i<length;i++) {
			a[i] = queue[index];
			index++;
			index = index % bound;
			
		}

	}


	@Override
	public String toString() {
		
		if(size==0)
			{System.out.println("no elements in the queue");
			return null;
			}
		StringBuilder data = new StringBuilder("BoundedQueue [");

		for(int i =0;i<size;i++) {
			data.append(queue[(pointer+i) % bound]);
			data.append(" ,");
		}
		data.setCharAt(data.length()-1, ']');
		return data.toString();
	}

//Creating an instance of the bounded queue class and testing its methods
	public static void main(String [] args){
		System.out.println("enter the bounded queue size");
		Scanner sc=new Scanner(System.in);
		int p=sc.nextInt();
		BoundedQueue<Integer> bqueue = new BoundedQueue<>(p);
		System.out.println("enter your choice");
		
		whileloop:
			while(sc.hasNext()) {
				int ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			{
			System.out.println("testing the offer() method of the queue by inserting 1,2,3,4,5");
			bqueue.offer(1);
			bqueue.offer(2);
			bqueue.offer(3);
			bqueue.offer(4);
			bqueue.offer(5);
			System.out.println("elements in the queue are:");
			System.out.println(bqueue.toString());
			System.out.println("------------");
			break;
			}
		case 2:{
		System.out.println("testing the size() method of the queue");
		System.out.println(bqueue.size());
		System.out.println("------------");
		break;
		}
		case 3:
		{
		System.out.println("testing the isEmpty() method of the queue");
		System.out.println(bqueue.isEmpty());
		System.out.println("------------");
		break;
		}
		
		case 4:
		{
		System.out.println("testing the peek() method of the queue");
		System.out.println(bqueue.peek());
		System.out.println("------------");
		break;
		}
		case 5:{
		System.out.println("testing the poll() method of the queue");
		System.out.println(bqueue.poll());
		System.out.println("------------");
		break;
		}
		case 6:{
		System.out.println("testing the toArray() method of the queue");
		System.out.println("enter the array size");
		int q=sc.nextInt();
		Integer arr[] = new Integer[q];
		bqueue.toArray(arr);
		int x;
		x=(arr.length<bqueue.size())?arr.length:bqueue.size();
		System.out.println("elements inserted into the array from the queue");
		for(int i=0;i<x;i++)
		{
			System.out.println(arr[i].intValue());
		}
		System.out.println("------------");
		break;
		}
		case 7:{
		System.out.println("testing the clear() method of the queue");
		bqueue.clear();
		System.out.println("elements in the queue are:");
		System.out.println(bqueue.toString());
		System.out.println("------------");
		}
		default:
			break whileloop;
		}
			}
		
	}	

}
