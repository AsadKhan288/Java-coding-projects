/**
 * BusLines Class
 * Asad-Home
 * CS 2210
 * Assignment 5
 * 
 * This class represents the city map with bus lines
 * 
 * A graph will be used to model the map and to find a path from the 
 * starting point and destination 
 */
import java.io.*;
import java.util.*;

public class BusLines {
	
	// This variable represents the scale factor used to display the map on the screen
	private int scaleFactor; 
	
	// This variable represents the number of bus line changes allowed in the trip from start to end
	private int line_changes;
	
	// This variable represents the width of the map (number of vertical streets in each row of the grid)
	private int Map_width;
	
	// This variable represents the length of the map (number of horizontal streets in each column of the grid)
	private int Map_length;
	
	// This variable represents a Graph from the class Graph
	private Graph tempGraph;
	
	// This variable holds the reference to the starting point node
	private GraphNode start_node;
	
	// This variable holds the reference to the destination point node
	private GraphNode dest_node;
	
	// This variable represents a stack of elements of type GraphNode
	private Stack<GraphNode> gNode_stack;
	
	// This variable stores a reference to the current node
	private int temp_node;
	
	// This variable stores a reference to the next node
	private int next_node;
	
	
	// Constructor for class BusLines
	//
	//
	//
	// Builds a city map with the bus lines from the given input file
	public BusLines(String inputFile) throws MapException{
		
		try {
			
			BufferedReader in_File = new BufferedReader(new FileReader(inputFile));
			String temp_Line;
			
			char bus_Line;
			
			// Read first line of the input file and store the width, length,
			// and number of allowed bus changes for the trip, in respective variables
			temp_Line = in_File.readLine();
			int a[] = new int[6];
			String[] Str =temp_Line.trim().split("\\s+");
			
			for (int i = 0; i < Str.length; i++)
			{
				a[i] = Integer.parseInt(Str[i]);
			}
			scaleFactor = a[0];
			Map_width = a[1];
			Map_length = a[2];
			line_changes = a[3];
			
			// Instantiate a new Graph, using the map's width and length
			tempGraph = new Graph(Map_length * Map_width);
			// Assign new GraphNode stack to variable
			gNode_stack = new Stack<GraphNode>();
			
			// Read next line 
			temp_Line = in_File.readLine();
			
			// Counters for the class//
			
			// Indicates the current node
			temp_node = 0;			
			// Indicates if current character is even
			int char_count = 0;		
			// Indicates if current row is even or odd
			int row_count = 0;
			// Indicates if current column is even or odd
			int col_count = 0;
			

			
			//loop through given input file for all the characters
			//
			//Inner for loop is for columns, Outer is for rows
			for (int i =0; i< (Map_length*2)-1; i++) {
				
				
				
				for(int j = 0; j< (Map_width*2)-1; j++)
				{
					//Assign blank value for bus_Line
					bus_Line = ' ';
					
	
					//If i is even, this ensures we are at a even row
					if (i%2 == 0)
					{
						//If value is 'S': it is the starting point
						if(temp_Line.charAt(j) == 'S')
						{
							// Assign as the starting node
							start_node = tempGraph.getNode((i/2*Map_width) + (j/2));
						}
						//If value is 'D': it is the destination point
						else if(temp_Line.charAt(j) == 'D') 
						{
							// Assign as the destination node
							dest_node = tempGraph.getNode((i / 2*Map_width) + (j/2));
						}
						// if value is '.': it is an intersection
						// No nodes are assigned
						else if(temp_Line.charAt(j) == '.')
						{
							
						}
						// If value is ' ': this represents houses
						// the temp_node counter will be incremented by 1, to jump to the next value
						else if(temp_Line.charAt(j) == ' ')
						{
							temp_node++;
						}
						
						// If value does not meet any of the above conditions
						// the value is either a letter or a digit (street) , thus an edge is inserted between the 
						// current & next node
						else
						{
							// Retrieve the bus line's name and assign to variable
							bus_Line = temp_Line.charAt(j);
							
							next_node = (temp_node + 1);
							
							//Edge inserted between the current and next node, with the specified bus Line name
							tempGraph.insertEdge(tempGraph.getNode(temp_node), tempGraph.getNode(next_node), bus_Line);
							
				
							temp_node++;
							
						}
					}
					
					
					//Since i is not even, this ensures we are at an Odd Row
					else 
					{
						//If j is an even number, this ensures we are at an Even column
						if (j%2 == 0) 
						{	
						
							// If value is representing houses(i.e. by a space, ' ') jump to the next value by 
							// incrementing the respective counters by 1
							if(temp_Line.charAt(j) == ' ')
							{
								col_count++;
								row_count++;
							}
							
							//Edge is inserted into graph between the current node and the 
							else 
							{
								bus_Line = temp_Line.charAt(j);
								tempGraph.insertEdge(tempGraph.getNode(row_count), tempGraph.getNode(col_count), bus_Line);
								col_count++;
								row_count++;
							}
						}
						//Since j is not an even number, this ensures we are at an Odd column
						else 
						{
							// if the value is a space, go to the next value
							if(temp_Line.charAt(j) == ' ')
							{
								
							}
							else 
							{
								bus_Line = temp_Line.charAt(j);
								tempGraph.insertEdge(tempGraph.getNode(row_count), tempGraph.getNode(col_count), bus_Line);
								col_count++;
								row_count++;
							}
						}
						
					}
					
				}
				
				// If the value of the counter is even (even character),
				// the counter is incremented
				if(char_count % 2 == 0)
				{
					temp_node++;
				}
				
				// If the value of the counter is zero, the value of the
				// the counter is set to the width of the map
				// This prevents out-of-bounds error
				if(char_count == 0)
				{
					col_count = Map_width;
				}
				char_count ++;
			
				
				
				//Read the next line in the input file
				temp_Line = in_File.readLine();
			}
			
			
		}
		catch (Exception e)
		{
			throw new MapException(": Map error caught");
		}
	}

	
	//This method returns the graph representing the map
	//
	//A MapException is thrown if the graph cannot be created
	Graph getGraph() throws MapException{
		
		if (tempGraph == null) {
			
			throw new MapException("Graph is null: it could not be retrieved");
		}
		return this.tempGraph;
	}
	


	//This method returns a Java iterator containing the nodes along the path
	//from the starting point to the destination, if such a path exists 
	//
	//If the path does not exist, this method returns null
	Iterator<GraphNode>trip(){
		
		try {
			
			//Iterator for incident edges
			Iterator<GraphEdge> inc_Edges = tempGraph.incidentEdges(start_node);
			
			//temp_Edge represents the current edge 
			GraphEdge temp_Edge = new GraphEdge(start_node, dest_node, 's');
			

			//While the iterator is not empty:
			//
			while(inc_Edges.hasNext())
			{
				//Assign current edge to the next edge 
				temp_Edge = inc_Edges.next();
				
				// private DFS traversal method called
				DFShelper(start_node, dest_node, temp_Edge);
				
				
				// If incident edges still present in stack,
				// Return the iterator 
				if(!gNode_stack.empty())
				{
					
					return gNode_stack.iterator();
				}
			}
			
			
		}
		catch (GraphException e)
		{
			System.out.println("GraphException caught: ");
		}
		
		//null is returned, since no path has been found
		return null;
	}
	
	// Private helper method  
	// 
	// The modified DFS traversal used in this method helps search  for the correct path
	//
	//
	//Returning True if a path has been found and False otherwise
	private boolean DFShelper(GraphNode start_node, GraphNode dest_node, GraphEdge temp_Edge)
	{
		//Starting node is inserted into the top of the stack
		gNode_stack.push(start_node);
		
		//If destination is the same as the starting point
		//true is returned 
		if(start_node == dest_node)
		{
			return true;
		}
		
		
		else 
		{
			try {

				//Starting node is marked true
				start_node.setMark(true);
				
				//Incident edges iterator for starting node
				Iterator<GraphEdge> i_edges = tempGraph.incidentEdges(start_node);
				
				//While iterator is not yet empty, loop for incident edges
				while(i_edges.hasNext()){
					
					// Assign next incident edge to variable
					GraphEdge disc_Edge = i_edges.next();
					
					
					//GraphNode alt_Node is second end point connected to disc_Edge 
					GraphNode alt_Node = disc_Edge.secondEndpoint();
					
					//If the (second) end point has not yet been marked as true
					//
					if(alt_Node.getMark() != true)
					{
						//Get the busLine that disc_Edge is on
						char disc_BusLine = disc_Edge.getBusLine();
						
						//Get the busLine that the current edge is on
						char temp_BusLine = temp_Edge.getBusLine();
						

						if (disc_BusLine != temp_BusLine)
						{
							// If the number of bus line changes allowed is greater than zero
							// Path will be attempted 
							if(line_changes > 0)
							{
								
								line_changes--;
								
								// Assign the current edge(temp_Edge) to be the other incident edge (disc_Edge)
								// and attempt to use this path to reach destination
								temp_Edge = disc_Edge;
								
								// Invoke method again recursively, 
								//
								// If destination has been found with the path
								// True is returned
								//
								// If path has not been found, an alternate bus line will be attempted
								if(DFShelper(alt_Node, dest_node, temp_Edge))
								{
									return true;
								}
								
								//Increment value back to original value
								line_changes++;
							}
						}
						
						else
						{
							// Assign the current edge(temp_Edge) to be the other incident edge (disc_Edge)
							// and attempt to use this path to reach destination
							temp_Edge = disc_Edge;
							
							// Path found
							// True is returned
							if (DFShelper(alt_Node, dest_node, temp_Edge))
							{
								return true;
							}
						}
					}
				}
				
				//Start node is labeled as false
				// 
				start_node.setMark(false);
				
				//Pop the node stack for the next node
				gNode_stack.pop();
				
			}
			catch (GraphException e)
			{
				System.out.println(" error present in graph");
			}
		}
		
		//No correct path can be found thus false is returned
		return false;
	}
	

}



