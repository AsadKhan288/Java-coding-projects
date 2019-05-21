/**
 * GraphNode Class
 * Asad-Home
 * CS 2210
 * Assignment 5
 * 
 * This class implements a node of the graph
 */
public class GraphNode {
	
	private int node_name;
	private boolean node_mark;
	
	// This constructor creates an unmarked node with the specified name
	// The name of a node is an integer value between 0 and n-1, where
	// n is the number of nodes in the graph
	//
	// A particular node can be marked either True or False using setMark method
	// this is useful when traversing the graph, to be aware of which vertices
	// have been visited already

	GraphNode(int name){
		
		node_name = name;
		
	}
	
	// Marks the node with the specified value
	public void setMark(boolean mark){
		
		node_mark = mark;
		
	}
	
	// This method returns the value with which the node
	// has been marked
	public boolean getMark() {
		
		return node_mark;
	}
	
	//Returns the name of the node
	public int getName() {
		
		return node_name;
	}

}
