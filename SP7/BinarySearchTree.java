/**
 *  BinarySearchTree class which extends the Comparable class.
 * 
 * 
 * @author Mani Chandana Dyda(MXD180035) & Venkateswar Reddy kaluva(VXK190000) 
 *
 *
 * @param <T> the type of elements held in this collection.
 */

package mxd180035;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	        this.left = left;
	        this.right = right;
        }
    }

    Entry<T> root;
    int size;

    public BinarySearchTree() {
	root = null;
	size = 0;
    }

    Stack<Entry<T>> stk;
    public Entry<T> search(T x){
        stk = new Stack<Entry<T>>();
        stk.push(null);
        return search(root,x);
    }
    public Entry<T> search(Entry<T> t, T x){
        int comp = t.element.compareTo(x);
        if(t == null || comp == 0)
            return t;
        while(true){
            comp = t.element.compareTo(x);
            if(comp > 0){
                if(t.left == null)  break;
                stk.push(t);
                t = t.left;
            }
            else if(comp == 0)    break;
            else if (t.right == null)   break;
            else{
                stk.push(t);
                t = t.right;
            }
        }
        return t;
    }
    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
	    Entry<T> node = search(x);
	    int comp = node.element.compareTo(x);
	    if(node == null || comp!=0)
	        return false;
        return true;
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {
        Entry<T> node = root;
        while(node!=null){
            int comp = node.element.compareTo(x);
            if(comp == 0)
                return node.element;
            else if(comp < 0)
                node = node.right;
            else
                node = node.left;
        }
	    return null;
    }

    /** TO DO: Add x to tree.
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) {
	    if(size == 0){
	        root = new Entry<T>(x,null,null);
	        size++;
	        return true;
        }
	    else{
	        Entry<T> node = search(x);
	        int comp = node.element.compareTo(x);
	        if(comp == 0){
                node.element = x;
                return false;
            }

	        else if(comp > 0)
	            node.left = new Entry<T>(x,null,null);
	        else
	            node.right = new Entry<T>(x,null,null);
	        size++;
	        return true;
        }
    }

    /** TO DO: Remove x from tree.
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
        if(size == 0)
            return null;
        Entry<T> node = search(x);
        int comp = node.element.compareTo(x);
        if(comp!=0)   return null;

        //atmost 1 child
        if(node.left == null || node.right == null)
            splice(node);
        else{
            stk.push(node);
            Entry<T> minRight = search(node.right , x);
            node.element = minRight.element;
            splice(minRight);
            size--;

        }
        return x;
    }
    
    //helper method for remove() if the node contains only one child
    public void splice(Entry<T> node){
        Entry<T> parent = stk.peek();
        Entry<T> child = (node.left==null)?node.right : node.left;
        if(parent == null)
            root = child;
        else if(parent.left == node)
            parent.left = child;
        else
            parent.right = child;
    }

    public T min() {
        Entry<T> node = root;
        if(node == null)
            return null;
        while(node.left!=null)
            node= node.left;
        return node.element;

    }

    public T max() {
        Entry<T> node = root;
        if(node == null)
            return null;
        while(node.right!=null)
            node= node.right;
        return node.element;
    }

    Object[] arr;
    int idx;

    public void inorder(Entry<T> root){
        if(root!=null){
            inorder(root.left);
            arr[idx++] = root.element;
            inorder(root.right);
        }
    }
    @SuppressWarnings("unchecked")
    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	this.arr = new Comparable[size];
	idx = 0;
    inorder(root);
	return (Comparable<T>[]) this.arr;
    }

    public Iterator<T> iterator() {
	return null;
    }

    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
        Scanner in = new Scanner(System.in);
       while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                System.out.print("Maximum ");
                System.out.println(t.max());
               // System.out.println(t.contains(-x));
                //System.out.println(t.get(x));
                t.printTree();
            } else if(x < 0) {
            	System.out.print("Remove " + x + " : ");
                t.remove(-x);
            	System.out.print("Minimum " );
            	System.out.println(t.min());
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }
        }
    }


    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
