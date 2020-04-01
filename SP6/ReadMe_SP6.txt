# Detect Odd Cycle using BFS


## Compiling

Use the javac command to compile the java files

Go to the package and edit the java file to Change the input String to required input or use a file as input.

Go to the src folder or to the folder where the java package is located and execute the below command

```
javac vxk190000/*.java
```
## Executing


Execute the complied code using the java command

```
java vxk190000/BFS 
or 
java vxk190000/BFS testcases.txt
```


##Test Cases & Output

*Test Case 1 :
Input : "9 9  1 2 2   1 3 3  1 7 3  2 4 6  3 4 4   4 5 1   5 6 -1  5 7 -1  8 9 6  1"
Output: {0,1,2,3,4,5,6,7,8,9}

*Test Case 2 :
Input: "5 5  1 2 2   1 3 3  2 4 6  4 5 1  3 5 3  1"
Output:
	Cycle of odd length :: 1->3->5->4->2->1
 

*Test Case 3 :
Input: "7 7  1 2 2   1 3 3  2 4 3  3 4 6  5 6 2  5 7 2  6 7 2 1";
Output:
	Cycle of odd length :: 5->7->6->5
 
 
*Test Case 4 :(Bipartite)
Input: "9 10  1 3 1  1 5 2  1 6 3  2 5 8  2 6 -1  3 4 2  4 8 6  7 8 2  3 9 4  7 5 -2 1";
Output:
	Cycle of odd length :: graph doesnot contain a odd length cycle(Bipartite)


*Test Case 5 :(disconnected with one odd and one even length cycle)
Input: "9 9  1 5 3  1 6 2  2 5 5  2 6 -1  3 4 8  4 8 1  7 8 5  3 9 -3  7 9 4 1"
Output:
	Cycle of odd length :: 3->9->7->8->4->3
 
 
## Done by 

Venkateswar Reddy Kaluva (VXK190000)
Mani Chandana Dyda (MXD180035)

