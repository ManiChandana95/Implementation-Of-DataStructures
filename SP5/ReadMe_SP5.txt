# DFS

Implementing DFS class 

## Compiling

Use the javac command to compile the java files

Go to the src folder or to the folder where the java package is located and execute the below command

```
javac mxd180035/*.java
```
## Executing

Execute the complied code using the java command

```
java mxd180035/DFS
```

## Testing
Give the input string as below which represents a graph.The first two digits represent the number of vertices and edges respectively.The other sets of 3 
numbers represent the edges with first two digits representing the from and to vertices of the edge followed by the digit representing the weight of the 
edge.(or) we can also input the file having this string as a command line argument.

Graph.readGraph(sc,true);//the argument true represents that the graph is directed.The graph is undirected by default, that is, need not specify this 
parameter.


#TestCase 1
string = "5 5   1 2 0   2 3 0   1 4 0   1 5 0   3 5 0 0";
Graph grph = Graph.readGraph(sc,true);

Output:

Graph: n: 5, m: 5, directed: true, Edge weights: false
1 :  (1,2) (1,4) (1,5)
2 :  (2,3)
3 :  (3,5)
4 : 
5 : 
______________________________________________
Number of connected components: 1
 u 	 cno
1	1
2	1
3	1
4	1
5	1

Output of dfs: 
 Node 	 Top 	 Parent 

1	1	null
2	3	1
3	4	2
4	2	1
5	5	3

topological order:

1	4	2	3	5	
---------------------------------------------------------------------------------------------------------------
#TestCase 2
string = "5 5   1 2 0   2 3 0   1 4 0   1 5 0   3 5 0 0";
Graph grph = Graph.readGraph(sc);

Output:

Graph: n: 7, m: 8, directed: false, Edge weights: false
1 :  (1,2) (1,3) (5,1)
2 :  (1,2) (2,4)
3 :  (1,3) (3,4)
4 :  (2,4) (3,4) (4,5)
5 :  (4,5) (5,1)
6 :  (6,7) (7,6)
7 :  (6,7) (7,6)
______________________________________________
Number of connected components: 2
 u 	 cno
1	1
2	1
3	1
4	1
5	1
6	2
7	2

Output of dfs: 
 Node 	 Top 	 Parent 

1	3	null
2	4	1
3	7	4
4	5	2
5	6	4
6	1	null
7	2	6

topological order:

does not exist as the graph has a cycle










