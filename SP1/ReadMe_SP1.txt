# DoublyLinkedList

DoublyLinkedList class extends SinglyLinkedList class.

## Compiling

Use the javac command to compile the java files

Go to the src folder or to the folder where the java package is located and execute the below command

```
javac mxd180035/*.java
```
## Executing

Execute the complied code using the java command

```
java mxd180035/DoublyLinkedList
```

## Testing

Tests are sequence of numbers 
1 - Check if there is next element print it else terminate
2 - Check if there is previous element print it  else terminate
3 - Add the next number in test case to the list after current element 
any other number to exit

##Test Cases & Output

*Test Case 1 :
1 1 2 1 2 1 1 2 1 0
*Output :
1
2
1
2
1
2
3
2
3
10: 1 2 3 4 5 6 7 8 9 10 

*Test Case 2 :
1 1 2 1 2 3 15 1 2 1 0
*Output :
1
2
1
2
1
11: 1 15 2 3 4 5 6 7 8 9 10 
15
1
15
11: 1 15 2 3 4 5 6 7 8 9 10 

*Test Case 3 :
3 20 1 0
*Output :
11: 20 1 2 3 4 5 6 7 8 9 10 
20
11: 20 1 2 3 4 5 6 7 8 9 10
 

## Note

Remove method is not included in test cases because , the remove method in SinglyLinkedList won't work for DoublyLinkedList
as previous link is not updated.
So the remove method has to be overridden in the DoublyLinkedList Class.

 
## Done by 

Venkateswar Reddy Kaluva (VXK190000)
Mani Chandana Dyda (MXD180035)

