package mxd180035;

import java.util.Random;


/**
 *  Merge Sort Analysis 
 * 
 * 
 * @author Mani Chandana Dyda(MXD180035)
 *
 * @version 1.0
 *
 */

public class SP3 {

	public static Random random = new Random();
	public static int numTrials = 50;

	private static int threshold = 64;


	public static void main(String[] args) {

		int[] inputArray = {16000000,32000000,64000000,128000000,256000000,512000000};
		int[] choiceArray = {3,4};
		
		for(int choice : choiceArray) {
			System.err.println("~~~~~~~~~~~~~~~~~");
			for(int n : inputArray) {
				
				System.out.println("#################");
				runTest(n,choice);
			}
		}
		
	}

	public static void runTest(int n , int choice) {
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = i;
		}
		System.out.println("Input Size - "+n);
		Timer timer = new Timer();
		switch(choice) {
		case 1:
			System.out.println("Take - 2");
			for(int i=0; i<numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort(arr,2);
			}
			break;
		case 2:
			System.out.println("Take - 3");
			for(int i=0; i<numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort(arr,3);
			}
			break;
		case 3:
			System.out.println("Take - 4");
			for(int i=0; i<numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort(arr,4);
			}
			break;
		case 4:
			System.out.println("Take - 6");
			for(int i=0; i<numTrials; i++) {
				Shuffle.shuffle(arr);	
				mergeSort(arr,6);
			}

			break;
		}

		timer.end();
		timer.scale(numTrials);
		System.out.println("Choice: " + choice + "\n" + timer);
	}
	
	public static void insertionSort(int[] arr, int strt, int end) {

		int n = end-strt+1;
		int i =strt+1;
		for (; i < strt+n; ++i) { 
			int key = arr[i]; 
			int j = i - 1; 

			while (j >= strt && arr[j] > key) { 
				arr[j + 1] = arr[j]; 
				j = j - 1; 
			} 
			arr[j + 1] = key; 
		} 

	}


	private static void mergeSort(int[] arr,int take) {

		int[] arrB = new int[arr.length];

		switch (take) {

		case 2:

			mergeSortTake2(arr,arrB,0,arr.length-1);
			break;

		case 3:
			System.arraycopy(arr,0,arrB,0,arr.length);
			mergeSortTake3(arr,arrB,0,arr.length-1);
			break;
		case 4:
			System.arraycopy(arr,0,arrB,0,arr.length);
			mergeSortTake4(arr,arrB,0,arr.length-1);
			break;
		case 6:
			mergeSortTake6(arr);
			break;
		default:
			System.err.println("invalid option for take selection"+take);
			break;


		}




	}

	private static void merge(int[] arrA, int[] arrB, int strt, int mid, int end,int take) {

		if(take ==2) {
			System.arraycopy(arrA, strt, arrB, strt, end-strt+1);	
		}

		int i = strt;
		int k = strt;
		int j = mid + 1;
		while ((i <= mid) && (j <= end)){
			if (arrB[i] <= arrB[j]) {
				arrA[k++] = arrB[i++];
			}else {
				arrA[k++] = arrB[j++];
			}
		}
		while (i <= mid) {
			arrA[k++] = arrB[i++];
		} 
		while (j <= end) {
			arrA[k++] = arrB[j++];
		} 

	}

	private static void mergeSortTake2(int[] arrA, int[] arrB, int strt, int end) {

		int mid;
		if(strt < end) {
			mid =strt +(end-strt)/2; 

			mergeSortTake2(arrA , arrB, strt, mid);
			mergeSortTake2(arrA, arrB, mid+1, end);
			merge(arrA,arrB,strt,mid,end,2);
		}


	}
	public static void mergeSortTake3(int[] arrA, int[] arrB, int strt, int end) {


		int mid;
		if(strt < end) {
			mid =strt +(end-strt)/2; 

			mergeSortTake3(arrB, arrA, strt, mid);
			mergeSortTake3(arrB, arrA, mid+1, end);
			merge(arrA,arrB,strt,mid,end,3);
		}

	}


	public static void mergeSortTake4(int[] arrA, int[] arrB, int strt, int end) {

		if(end-strt+1 < threshold) {
			insertionSort(arrA, strt,end);
		}else {
			int mid;
			if(strt < end) {
				mid =strt +(end-strt)/2; 

				mergeSortTake4(arrB, arrA, strt, mid);
				mergeSortTake4(arrB, arrA, mid+1, end);
				merge(arrA,arrB,strt,mid,end,4);
			}
	
		}


		

	}

	public static void mergeSortTake6(final int[] arrA) {

		int[] arrB = new int[arrA.length];
		int n = arrA.length;
		int[] inp =arrA;

		//case 1) n < threshold
		//case 2) threshold < n < 2*threshold (Math.min handles this)

		for(int j = 0; j < n; j = j+threshold) {
			insertionSort(inp, j,Math.min(j+threshold-1,n-1));
		}

		for(int i = threshold; i < n; i = 2*i) {
			for(int j= 0; j < n; j = j+2*i) {
				// 1st Min is if odd number of bloks 
				merge(arrB,inp , j, Math.min(j+i-1,n-1), Math.min(j+2*i-1,n-1),6);
			}
			
			int[] tmp= inp ; inp = arrB; arrB = tmp; 	
		}

		if (arrA !=inp ) {
			System.arraycopy(inp, 0, arrA, 0, arrA.length);
		}



	}

	/** @author rbk : based on algorithm described in a book
	 */


	/* Shuffle the elements of an array arr[from..to] randomly */
	public static class Shuffle {

		public static void shuffle(int[] arr) {
			shuffle(arr, 0, arr.length-1);
		}

		public static<T> void shuffle(T[] arr) {
			shuffle(arr, 0, arr.length-1);
		}

		public static void shuffle(int[] arr, int from, int to) {
			int n = to - from  + 1;
			for(int i=1; i<n; i++) {
				int j = random.nextInt(i);
				swap(arr, i+from, j+from);
			}
		}

		public static<T> void shuffle(T[] arr, int from, int to) {
			int n = to - from  + 1;
			Random random = new Random();
			for(int i=1; i<n; i++) {
				int j = random.nextInt(i);
				swap(arr, i+from, j+from);
			}
		}

		static void swap(int[] arr, int x, int y) {
			int tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		static<T> void swap(T[] arr, int x, int y) {
			T tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		public static<T> void printArray(T[] arr, String message) {
			printArray(arr, 0, arr.length-1, message);
		}

		public static<T> void printArray(T[] arr, int from, int to, String message) {
			System.out.print(message);
			for(int i=from; i<=to; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
		}
	}
}

