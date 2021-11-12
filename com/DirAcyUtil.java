package com;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.libs.GraphNode;



class Edge implements GraphNode
{
	int source, dest;

	Graph graph = null;
	
	
	
	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
	
	public Set<GraphNode> getDirectlyLinkedNodes()	{
			if (this.graph == null)	
				return null;
			else	{
				Set set = new HashSet();			
				for (Edge edge: graph.adjList.get(source))
				{
					set.add(edge.dest);
				}
				return set;
			}	
	}

	public void setGraph(Graph graph)	{
		this.graph = graph;
	}	
}

class Graph
{
	List<List<Edge>> adjList = null;

	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList();

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList());
		}

		for (Edge edge: edges) {
			adjList.get(edge.source).add(edge);

		}
	}
}

public class DirAcyUtil
{

	static int result;
	static boolean discovered = false;
	private static void CalculateUntilRoot(Graph graph, int v, int time, int destination)
	{
		for (Edge edge: graph.adjList.get(v))
		{
			//System.out.println("---" + edge.source + " " + edge.dest + " time " + time);
			
			if (edge.dest == destination)	{
				if (!discovered)	{
					discovered = true;
					//System.out.println("match");
						
					result = time;
					return;
				}	
			}	else	{	
					if (!discovered)
					CalculateUntilRoot(graph, edge.dest, time+1, destination);			
			}			
		}
	}

	public static void getShortestPathDistance(Graph graph, int source, int destination, int N)
	{

		int time = 1;

		for (Edge edge: graph.adjList.get(source))
		{
			if(!discovered)	{
				time = 1;
			}	else{
				break;
			}	
			//System.out.println(edge.source + " " + edge.dest);
			if (edge.dest == destination)	{
				discovered = true;
				break;
			}	else	{	
				if(!discovered)	{
					CalculateUntilRoot(graph, edge.dest,  time+1,  destination);
					time = result;
				}
				
			}
		}


		if (discovered)	{
			System.out.println(source + " reach " + destination + " with " + time + " steps");
		}	else{
			System.out.println(source + " unable to reach " + destination);
		}	
		
	}

	// Find the cost of Shortest Path in Directed Acyclic Graph
	public static void main(String[] args)
	{
		// test case data
		List<Edge> edges = Arrays.asList(
								new Edge(0, 6), //means 0 -> 6
								new Edge(1, 2),
								new Edge(1, 4), 
								new Edge(1, 6),
								new Edge(3, 0), 
								new Edge(3, 4),
								new Edge(5, 1), 
								new Edge(7, 0),
								new Edge(7, 3),
								new Edge(7, 5)
							);


		// Set number of vertices in the graph
		final int N = 8;

		// create a graph from given edges
		Graph graph = new Graph(edges, N);

		// source vertex
		int source = 7;
		int destination = 2;
		// find Shortest distance of all vertices from given source
		getShortestPathDistance(graph, source, destination, N);
	}
}