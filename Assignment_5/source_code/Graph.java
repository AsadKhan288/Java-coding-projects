/**
 * Graph Class
 * Asad-Home
 * CS 2210
 * Assignment 5
 * 
 * This class implements an undirected graph using an
 * adjacency matrix
 */
import java.util.*;
import java.util.Iterator;

public class Graph implements GraphADT{
	
	//the associated adjacency matrix
	private GraphEdge[][] adj_Matrix;
	
	//Array of nodes 
	private GraphNode [] g_nodes;
	
	
	// This constructor creates a graph with n nodes and no edges
	// The name of the nodes are 0,1,...,n-1
	//
	public Graph(int n) {
		
		//Instantiate matrix with GraphEdge class
		adj_Matrix = new GraphEdge[n][n];
		
		//Instantiate nodes(the vertices) with GraphNode class
		g_nodes = new GraphNode[n];
		
		//create 'n' vertices
		for (int i=0; i < n; i++) {
			
			g_nodes[i] = new GraphNode(i);
		}
	}
	
	// This method adds an edge connecting the nodes u and v, belonging to the
	// specified bus line
	//
	// A GraphException is thrown if the node does not exist OR if in the graph 
	// there is already an edge connecting the given nodes
	//
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException
	{
		
		// Examine given nodes to see if they have a value for Name that is less than 0,
		// 		OR if the value is equal to or larger than the size of the vertex array ( does not belong in array )
		//			if either of the nodes do, return an error message
		if (u.getName() < 0|| v.getName() < 0 || u.getName() >= g_nodes.length || v.getName() >= g_nodes.length)
			
		{
			throw new GraphException("Error: node(s) does not belong in array ");
			
		}
		
		// Examine matrix to see if an edge is already present between 
		// the given nodes using  the private method (CheckEdge)
		if (CheckEdge(u,v) || CheckEdge(v,u)) {
			
			// If an edge is present between the given nodes, return error message
			throw new GraphException("Edge already exists");
		}
		
		//Insert edge both ways into matrix for symmetry
		adj_Matrix[u.getName()][v.getName()] = new GraphEdge(u,v,busLine);
		adj_Matrix[v.getName()][u.getName()] = new GraphEdge(v,u,busLine);
	}
	
	// This method returns the node with the specified name
	// If no node with this name exists, a GraphException is thrown
	// 
	public GraphNode getNode(int name) throws GraphException{
		
		//If the node should not exist in array, throw exception
		if (name<0|| name>= g_nodes.length) 
		{
			
			throw new GraphException("Node could not be retrieved");
		}
		else 
		{
			//Return the respective node from the array
			return g_nodes[name];
			
		}
	}
	
	// This method returns a Java iterator storing all the edges incident on
	// GraphNode 'u'. Null is returned if 'u' does not have any edges incident on it
	//
	//GraphException is thrown if the GraphNode 'u' does not exist
	//
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException
	{
		
		// If GraphNode 'u' does not exist , throw exception
		if(u.getName()<0 || u.getName() >=g_nodes.length)
		{
			throw new GraphException("Node does not exist");
		}
		else 
		{
			// Instantiate a stack containing elements of type GraphEdge
			// The elements in the stack will represent the incident edges
			Stack<GraphEdge> inc_Edges = new Stack<GraphEdge>();
			
			// iterate through matrix for the length of the node array
			for (int n = 0; n <g_nodes.length; n++)
			{

				// If edge incident to given node is present (not null)
				// Push the incident edge to inc_Edges
				//
				if(adj_Matrix[u.getName()][n] != null)
				{
					inc_Edges.push(adj_Matrix[u.getName()][n]);
				}
			}
				// If no incident edges have been found and the stack does not have
				// any elements, return null
				//
				// Otherwise, return the iterator of the stack inc_Edges
				if(inc_Edges.isEmpty()) 
				{
					return null;
				}
						
				return inc_Edges.iterator();
						
			}
			
		}

	
	// This method returns the edge connecting GraphNodes u and v
	// GraphException is thrown if there is no edge between u and v
	//
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		
		// If nodes u or v do not exist OR if the nodes do exist 
		// and are not adjacent, the method areAdjacent will throw a GraphException
		if(!areAdjacent(u,v))
		{
			throw new GraphException("Error: could not retrieve edge");
			
		}
		return adj_Matrix[u.getName()][v.getName()];
		
	}
	
	// This method returns True if GraphNodes u and v are adjacent and
	// False otherwise
	//
	// If GraphNodes u or v do not exist, GraphException is thrown
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		
		if (u.getName() < 0|| v.getName() < 0 || u.getName() >= g_nodes.length || v.getName() >= g_nodes.length) {
			
			throw new GraphException("Error: nodes do not exist");
			
		}
		else 
		{
			// Using CheckEdge, true is returned if there is an edge that exists between the nodes u and v,
			// false if there isn't an edge present
			//
			if (CheckEdge(u,v)) 
			{
				return true;
			}
			// if CheckEdge method returns False, an edge does not exist, 
			// Thus, false is returned
			else 
			{
				
				return false;
			}
		}
			
	}
	
	
	// Private method to check if there already exists an edge 
	// between the GraphNodes u and v 
	//
	// Return True if it does exist, False otherwise
	
	private boolean CheckEdge(GraphNode u, GraphNode v) {
		
		// Edge does exist
		if (adj_Matrix[u.getName()][v.getName()] != null) {
			
			return true;
		}
		
		// Edge does not exist
		else {
		
		return false;
		}
		
	}
	
	
	
	
	
	
		
	
	

}
