package mxd180035;

import idsa.BFSOO;
import idsa.Graph;
import idsa.Graph.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/** Breadth-first search

 *  @author Mani Chandana Dyda(MXD180035) 

 */


public class BFS extends BFSOO {


	/**
	 * Default constructor to return instance of BFS class
	 * @param g is graph to initialize the class with
	 */
	public BFS(Graph g) {
		super(g);
	}


	/**
	 * Method to detect the odd cycle in a graph given the source vertex
	 * 
	 * @param g is the graph
	 * @param src is the vertex from where we start
	 * @param colors is array to stores the colors of the vertices(checking bipartite) 
	 * @return list of vertices that form a cycle and null when the graph is bipartite
	 */
	private static List<Vertex> oddCycle(Graph g,Vertex src, int[] colors) {
		LinkedList<Vertex> output = new LinkedList<Vertex>();
		colors[src.getIndex()] = 1; 

		// Create a queue (FIFO) of vertices and  
		// enqueue source vertex for BFS traversal 
		LinkedList<Vertex> q = new LinkedList<Vertex>(); 
		q.add(src); 

		int[] parents = new int[g.size()];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = -1;
		}
		// Run while there are vertices in queue  
		// (Similar to BFS) 
		while (!q.isEmpty()) 
		{ 
			// Dequeue a vertex from queue        
			Vertex u = q.peek();
			q.pop(); 

			for(Edge e : g.incident(u)) {
				Vertex v = e.otherEnd(u);

				// Return list with 1 element if there is a self-loop
				if(v.equals(u)) {
					output = new LinkedList<Vertex>();
					output.add(u);
					return output;
				}
				// destination v is not colored
				if(colors[v.getIndex()] == -1) {
					// Assign alternate color to this 
					// adjacent v of u 
					colors[v.getIndex()] = 1 - colors[u.getIndex()]; 
					parents[v.getIndex()] = u.getIndex();
					q.push(v);

				}

				// destination v is colored with same 
				// color as u 
				else if (colors[v.getIndex()] ==  colors[u.getIndex()]) {

					int l = v.getIndex();
					int f = u.getIndex();
					while(parents[l] != -1 || parents[f] != -1) {

						if(parents[f] != -1) {
							output.addFirst(g.getVertex(f+1));
							f = parents[f];
						} 
						if(parents[l] != -1) {
							output.addLast(g.getVertex(l+1));
							l = parents[l];
						} 

						if(f == l) {		
							break;
						}

					}
					output.addFirst(g.getVertex(f+1));
					output.addLast(g.getVertex(l+1));

					return output;
				} 
			}

		} 

		// All adjacent vertices can be colored with alternate color(bipartite) 
		return null; 
	}



	/**
	 * Wrapper method to detect the odd cycle in a graph checking all vertices in a disconnected graph
	 * @param g graph
	 * @return list of vertices that form a cycle and null when the graph is bipartite
	 */
	public static List<Vertex> oddCycle(Graph g) {

		Vertex[] vertexArray = g.getVertexArray();
		int colorArr[] = new int[vertexArray.length]; 
		for (int i = 0; i < vertexArray.length; i++) {
			colorArr[i] = -1;
		} 
		List<Vertex> output ;
		// This code is to handle disconnected graph 
		for (int i = 0; i < vertexArray.length; i++) {
			if (colorArr[i] == -1) {
				output = oddCycle(g, vertexArray[i], colorArr);
				//return the first odd length cycle.
				if ( output != null) {
					return output;
				} 
			}   
		} 
		return null; 

	}

	public static void main(String[] args) throws Exception {
		
//		String string = "9 9  1 2 2   1 3 3  1 7 3  2 4 6  3 4 4   4 5 1   5 6 -1  5 7 -1  8 9 6  1";
//		String string = "5 5  1 2 2   1 3 3  2 4 6  4 5 1  3 5 3  1";
//		String string = "7 7  1 2 2   1 3 3  2 4 3  3 4 6  5 6 2  5 7 2  6 7 2 1";
//		String string = "9 10  1 3 1  1 5 2  1 6 3  2 5 8  2 6 -1  3 4 2  4 8 6  7 8 2  3 9 4  7 5 -2 1";
		String string = "9 9  1 5 3  1 6 2  2 5 5  2 6 -1  3 4 8  4 8 1  7 8 5  3 9 -3  7 9 4 1";
		Scanner in;
		
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
		// Read graph from input
		Graph g = Graph.readGraph(in);
		int s = in.nextInt();

		// Create an instance of BFS and run bfs from source s
		BFSOO b = breadthFirstSearch(g, s);

		g.printGraph(false);

		System.out.println("Output of BFS:\nNode\tDist\tParent\n----------------------");
		for(Vertex u: g) {
			if(b.getDistance(u)== INFINITY) {
				System.out.println(u + "\tInf\t--");
			} else {
				System.out.println(u + "\t" + b.getDistance(u) + "\t" + b.getParent(u));
			}
		}


		List<Vertex> list = oddCycle(g);
		System.out.println("**************************************");
		System.out.print("Cycle of odd length :: ");
		if(list == null) {
			System.out.println("graph doesnot contain a odd length cycle(Bipartite)");
		}else {
			for(Vertex u: list) {
				System.out.print(u + "->");
			}
			System.out.println();
		}
		//Testing
		// draw in https://csacademy.com/app/graph_editor/ and verify the output



	}
}

/* Sample run:
______________________________________________
Graph: n: 7, m: 8, directed: true, Edge weights: false
1 :  (1,2) (1,3)
2 :  (2,4)
3 :  (3,4)
4 :  (4,5)
5 :  (5,1)
6 :  (6,7)
7 :  (7,6)
______________________________________________
Output of BFS:
Node	Dist	Parent
----------------------
1	0	null
2	1	1
3	1	1
4	2	2
5	3	4
6	Inf	--
7	Inf	--
 */
