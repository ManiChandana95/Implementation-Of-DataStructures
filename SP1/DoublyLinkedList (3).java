package vxk190000;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *  Doubly Linked List class which extends the SinglyLinkedList class.
 * 
 * 
 * @author Venkateswar Reddy kaluva(VXK190000) &  Mani Chandana Dyda(MXD180035)
 *
 * @version 1.0
 * @since 
 *
 * @param <T> the type of elements held in this collection.
 */
public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

	/**
	 * DoublyLinkedList.Entry Class , Extends SinglyLinkedList.Entry Class
	 *
	 * @param <E> the type of elements held in this collection.
	 */
	static class Entry<E> extends SinglyLinkedList.Entry<E> {

		SinglyLinkedList.Entry<E> prev;
		/**
		 * Constructs an Object of DoublyLinkedList.Entry instance.
		 * 
		 * @param x the element to be held in the list.
		 * @param next the pointer to the next Node in the list.
		 * @param prev the pointer to the previous Node in the list.
		 */
		Entry(E x, Entry<E> next, Entry<E> prev) {
			super(x, next);
			this.prev = prev;
		}

	}


	/**
	 * Constructs an Object of DoublyLinkedList instance.
	 */
	public DoublyLinkedList(){
		tail = head = new Entry<>(null, null,null);
		size = 0;
	}


	/**
	 * 
	 * Iterator implementation
	 * 
	 * @return Returns an iterator over the elements in this list in proper sequence.
	 * 
	 */
	public ListIterator<T> iterator()  { return new DLLIterator(); }

	/**
	 * Iterator Class which extends the SLLIterator and the ListIterator interface.
	 */
	protected class DLLIterator extends SLLIterator implements ListIterator<T> {

		DLLIterator() {
			super();
		}

		/**
		 * Checks if the current element has a previous element in the DoublyLinkedList.
		 */
		public boolean hasPrevious() {
			/*
			 * since we are using a dummy header , to avoid printing null element when
			 * iterating in reverse order we are checking if the element is null
			 */
			return ((Entry<T>)cursor).prev != null && ((Entry<T>)cursor).prev.element != null;
		}


		/**
		 * Returns the previous element in the list and moves the cursor position backwards.
		 * This method may be called repeatedly to iterate through the list backwards
		 * @return the previous element in the list
		 * @throws NoSuchElementException - if the iteration has no previous element
		 */
		public T previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			cursor =((Entry<T>)cursor).prev;
			prev = cursor;
			ready = true;
			return cursor.element;
		}


		/**
		 * Insert a new element in the list next to the element where cursor is pointing.
		 * 
		 * @param newElem the element to be inserted
		 */
		public void add(T newElem) {
			Entry<T> newEntry = new Entry<>(newElem,null,null);
			newEntry.prev = cursor;
			newEntry.next = cursor.next;
			((Entry<T>)cursor.next).prev = newEntry;
			cursor.next = newEntry;

			size++;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub

		}

	} //End of DLL iterator class 

	/**
	 * Adds a new element at the end of the list
	 * @param x element to added to the list
	 */
	public void add(T x) {
		add(new Entry<>(x, null,null));
	}


	private void add(Entry<T> ent) {
		tail.next = ent;
		ent.prev = tail;
		tail = tail.next;
		size++;
	}

	public static void main(String[] args) throws NoSuchElementException {
		int n = 10;
		if(args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
		for(int i=1; i<=n; i++) {
			lst.add(Integer.valueOf(i));
		}
		lst.printList();

		ListIterator<Integer> it = lst.iterator();
		Scanner in = new Scanner(System.in);
		whileloop:
			while(in.hasNext()) {
				int com = in.nextInt();
				switch(com) {
				case 1:  // Move to next element and print it
					if (it.hasNext()) {
						System.out.println(it.next());
					} else {
						break whileloop;
					}
					break;

				case 2:  // Move to previous element and print it
					if (it.hasPrevious()) {
						System.out.println(it.previous());
					} else {
						break whileloop;
					}
					break;
				case 3:  // add element
					it.add(in.nextInt());
					lst.printList();
					break;
				default:  // Exit loop
					break whileloop;
				}
			}
		lst.printList();

	}
}