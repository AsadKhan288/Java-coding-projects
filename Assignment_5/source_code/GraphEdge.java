/**
 * GraphEdge Class
 * Asad-Home
 * CS 2210
 * Assignment 5
 * 
 * This class implements an edge of the graph 
 */
public class GraphEdge {
	
	private GraphNode node_u;
	private GraphNode node_v;
	private char bus_Line;
	
	
	// The constructor for the class
	//
	// The first two parameters( u, v) are the end points of the edge
	// The last parameter is the bus line to which the street represented
	// by the edge belongs
	//
	// Each bus line has a name that consists of a single character
	// Either a digit or a letter, except 'S' and 'D' which are used to represent
	// the starting point and destination
	GraphEdge(GraphNode u, GraphNode v, char busLine){
		
		node_u = u;
		node_v = v;
		bus_Line = busLine;
		
	}
	
	// Returns the first end point of the edge
	public GraphNode firstEndpoint() {
		
		return node_u;
	}
	
	// Returns the second end point of the edge
	public GraphNode secondEndpoint() {
		
		return node_v;
	}
	
	// Returns the name of the busLine to which the edge belongs
	public char getBusLine() {
		
		return bus_Line;
	}

}
