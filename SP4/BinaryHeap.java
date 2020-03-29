// Starter code for SP9

// Change to your netid
package mxd180035;

import java.util.NoSuchElementException;

/**
 *  BinaryHeap class 
 * 
 * 
 * @author Mani Chandana Dyda(MXD180035)
 *
 * @version 1.0
 *
 * @param <T> the type of elements held in this collection.
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	T[] pq; // array to store elements
	int size; // size of the heap (No of elements in the heap)

	
	/**
     * Initializes an empty priority queue with the given maximum capacity.
     *
     * @param maxCapacity the initial capacity of this priority queue
     */
	// Constructor for building an empty priority queue using natural ordering of T
	public BinaryHeap(int maxCapacity) {
		
		this.pq = (T[]) new Comparable[maxCapacity];
		
		size = 0;
	}


	/**
	 *  Adds a new element to the priority queue
	 * @param x is the element to be added
	 * @return true once the element got added
	 */
	// add method: resize pq if needed
	public boolean add(T x) {

		// if filled resize the array
		if(pq.length == size+1) {
			resize();
		}
		
		pq[size] = x;
		// percolate the newly added up to maintain heap property
		percolateUp(size);
		size++;
		return true;

	}

	/**
	 * Adds a new Element to the priority queue
	 * @param x is the element to be added
	 * @return true once the element got added
	 */
	public boolean offer(T x) {
		
		return add(x);
	}

	
	/**
	 * Removes and returns the minimum element from the priority queue.
	 * @return smallest element on this priority queue
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	//throw exception if pq is empty
	public T remove() throws NoSuchElementException {
		// call poll function to get the minumum element and check if null
		T result = poll();
		if(result == null) {
			throw new NoSuchElementException("Priority queue is empty");
		} else {
			return result;
		}
	}

	// return null if pq is empty
	/**
	 * Removes and returns the minimum element from the priority queue.
	 * @return smallest element on this priority queue and null if priority queue empty
	 */
	public T poll() {

		if(size == 0) {
			return null;
		}else {
			T x = pq[0];
			pq[0] = pq[size - 1];
			size--;	
			// remove the minimum element and fix the heap property
			percolateDown(0);
			return x;
		}

	}

	/**
	 * Return the element with least priority
	 * @return the element with least priority
	 */
	public T min() { 
		return peek();
	}

	// return null if pq is empty
	/**
	 * Return the element with least priority
	 * @return the element with least priority and null when priority queue is empty
	 */
	public T peek() {
		
		if(size == 0) {
			return null;
		}else {
			
			return pq[0];
		}
	}

	/**
	 * get the index of the parent of the given index
	 * @param i the index of element which you want to find the parent
	 * @return the index of parent
	 */
	int parent(int i) {
		
		return (i-1)/2;
	}

	/**
	 * get the index of the left child of the given index
	 * @param i the index of element which you want to find the left child
	 * @return the index of left child
	 */
	int leftChild(int i) {
	
		return 2*i + 1;
	}

	/** 
	 * pq[index] may violate heap order with parent
	 * Fixes the heap order property by percolating up 
	 */
	void percolateUp(int index) {
		T x = pq[index];
		// comparing with zero and not == to handle string comparision cases
		while (index > 0 && (compare(pq[parent(index)],x) > 0)) { 
			pq[index] = pq[parent(index)];
			index= parent(index);
		}
		pq[index] = x;

	}

	
	/** 
	 * pq[index] may violate heap order with children
	 * Fixes the heap order property by percolating down 
	 */
	void percolateDown(int index) {

		T x = pq[index];
		int small = leftChild(index); // 2*i+ 1
		while (small <= size-1) {
			// comparing with zero and not == to handle string comparision cases
			if ((small < size-1) && compare(pq[small],pq[small+1]) >0 ){
				small = small + 1;
			}

			if (compare(x,pq[small]) < 0 ) {
				break;
			}
			pq[index] = pq[small];
			index= small;
			small = leftChild(index);
		}
		pq[index] = x;

	}

	/** use this whenever an element moved/stored in heap. Will be overridden by IndexedHeap */
	void move(int dest, Comparable x) {
		pq[dest] = (T) x;
	}

	/**
	 * Helper function to compare two elements
	 * @param a is the element to compare
	 * @param b is the element to be compared with
	 * @return {@code >0} if a > b , {@code <0 } if a < b , {@code 0} if a = b 
	 */
	int compare(Comparable a, Comparable b) {
		return ((T) a).compareTo((T) b);
	}

	
	/**
	 * Convert a normal array to a heap using percolation down method
	 */
	void buildHeap() {
		for(int i=parent(size-1); i>=0; i--) {
			percolateDown(i);
		}
	}

	 /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty,
     *         {@code false} otherwise
     */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue.
     */
	public int size() {
		return size;
	}

	/**
	 * Resize the array to double the size
	 * 
	 */
	void resize() {
		
		T[] tmp = (T[]) new Comparable[2*size];
		System.arraycopy(pq, 0, tmp, 0, size);
		pq = tmp;
	}

	public interface Index {
		public void putIndex(int index);
		public int getIndex();
	}

	public static class IndexedHeap<T extends Index & Comparable<? super T>> extends BinaryHeap<T> {
		/** Build a priority queue with a given array */
		IndexedHeap(int capacity) {
			super(capacity);
		}

		/** restore heap order property after the priority of x has decreased */
		void decreaseKey(T x) {
		}

		@Override
		void move(int i, Comparable x) {
			super.move(i, x);
		}
	}

	public static void main(String[] args) {
		
		//input array of any type which implements comparable interface
		//Test Case 2
		Integer[] arr = {0,9,7,5,3,1,8,6,4,2};
		BinaryHeap<Integer> h = new BinaryHeap<Integer>(arr.length);// initialize heap with size tequal to the input array
		System.out.println("--------Test Case 1 ---------");
		System.out.print("Before:");
		for(Integer x: arr) {
			h.offer(x);// loop through the array and add elements to the heap
			System.out.print(" " + x);
		}
		System.out.println();
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = h.poll();// loop through the array and remove elements to the heap and update the initial array
		}
		
		// loop through the array and print elements 
		// prints a sorted list of elements 
		System.out.print("After :");
		for(Integer x: arr) {
			System.out.print(" " + x);
		}
		System.out.println();
		
		//Test Case 2
		String[] arr2 = {"p","x","w","d","a","c","s"};
		
		BinaryHeap<String> h2 = new BinaryHeap<String>(arr2.length);// initialize heap with size equal to the input array
		System.out.println("--------Test Case 2 ---------");
		System.out.print("Before:");
		for(String x: arr2) {
			h2.offer(x);// loop through the array and add elements to the heap
			System.out.print(" " + x);
		}
		System.out.println();
		
		for(int i=0; i<arr2.length; i++) {
			arr2[i] = h2.poll();// loop through the array and remove elements to the heap and update the initial array
		}
		
		// loop through the array and print elements 
		// prints a sorted list of elements 
		System.out.print("After :");
		for(String x: arr2) {
			System.out.print(" " + x);
		}
		System.out.println();
		
	}
}
