/** Starter code for SP5
 *  @author rbk
 */

// change to your netid
package mxd180035;

import mxd180035.Graph.Vertex;
import mxd180035.Graph.Edge;
import mxd180035.Graph.GraphAlgorithm;
import mxd180035.Graph.Factory;
import mxd180035.Graph.Timer;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;


	public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	    private LinkedList<Vertex> visitedList;
	    int count_cc;    //number of connected components
	    static boolean isCycle;
	    int first;
	    
	    public DFS(Graph g) {
	        super(g, new DFSVertex(null));
	        count_cc = 0;
	        isCycle = false;
	    }

	    public static class DFSVertex implements Factory {
	        int top;    // to keep track of back edges
	        Vertex parent;
	        int cno;
	        boolean visited;

	        public DFSVertex(Vertex u) {
	        	this.top = 0;
	        	this.parent = null;
	            this.visited = false;
	            this.cno = 0;
	        }

	        public DFSVertex make(Vertex u) { return new DFSVertex(u); }
	    }

	    //helper function for finding whether the graph has a cycle and also finding the component of a given vertex 
	    public void DFS_visit(Vertex u){
	        get(u).visited = true;
	        for(Edge edg: g.incident(u)){
	            Vertex v = edg.otherEnd(u);
	            if(!get(v).visited){
	                get(v).cno = get(u).cno;
	                get(v).parent = u;
	                DFS_visit(v);
	            }
	            else{
	                if(get(v).top == 0){
	                    isCycle = true;
	                }
	            }
	        }
	        get(u).top = first--;
	        visitedList.addFirst(u);
	    }

	 // Find the number of connected components of the graph g by running dfs.
	    // Enter the component number of each vertex u in u.cno.
	    // Note that the graph g is available as a class field via GraphAlgorithm.
	    public int connectedComponents() {
	        dfs();
	        return count_cc;
	    }
	    
	 // After running the connected components algorithm, the component no of each vertex can be queried.
	    public int cno(Vertex u) {
	        return get(u).cno;
	    }
	  
	    //helper function for finding the depth first search of a graph
	    public void dfs(){
	        first = g.size();

	        for(Vertex u : g){
	        	get(u).parent = null;
	            get(u).visited = false;
	            get(u).top = 0;
	        }

	        visitedList = new LinkedList<Vertex>();
	        for(Vertex u : g){
	            if(!get(u).visited){
	                get(u).cno = ++count_cc;
	                DFS_visit(u);
	            }
	        }
	    }

	  //function for finding the depth first search of a graph
	    public static DFS depthFirstSearch(Graph g) {
	        DFS dfsobj = new DFS(g);
	        dfsobj.dfs();
	        return dfsobj;
	    }

	    // Member function to find topological order
	    public List<Vertex> topologicalOrder1() {
	        dfs();
	        if(isCycle){
	            return null;
	        }
	        return visitedList;
	    }

	    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
	    public static List<Vertex> topologicalOrder1(Graph g) {
	        DFS d = new DFS(g);
	        return d.topologicalOrder1();
	    }

	    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
	    public static List<Vertex> topologicalOrder2(Graph g) {
	        return null;
	    }

	    public static void main(String[] args) throws Exception {
	    	String string = "5 5   1 2 0   2 3 0   1 4 0   1 5 0   3 5 0 0";
	        Scanner sc;
	        // If there is a command line argument, use it as file from which
	        // input is read, otherwise use input from string.
	        sc = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

	        // Read graph from input
	        Graph grph = Graph.readGraph(sc,true);
	        grph.printGraph(false);
	        
	        DFS d = new DFS(grph);
	        int numCC = d.connectedComponents();

	        System.out.println("Number of connected components: "+ numCC+"\n u \t cno");
	        for(Vertex u: grph){
	            System.out.println(u+"\t"+d.cno(u));
	        }
	        System.out.println("");

	        System.out.println("Output of dfs: \n Node \t Top \t Parent \n");
	        for(Vertex u: grph){
	            System.out.println(u+"\t"+d.get(u).top+"\t"+d.get(u).parent);
	        }
	        System.out.println("");

	        List<Vertex> tlst = d.topologicalOrder1();
	        System.out.println("topological order:\n");

	        if(tlst == null){
	            System.out.println("does not exist as the graph has a cycle");
	        }
	        else{
	            for(Vertex u: tlst){
	                System.out.print(u+"\t");
	            }
	            System.out.println("");
	        }

	    }
	}
