import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 3; you should definitely add more tests!
 */
public class GraphPartialTester {
	Graph actorsGraph, moviesGraph;
	GraphSearchEngine searchEngine;

	@Test(timeout=5000)
	/**
	 * Verifies that there is no shortest path between a specific and actor and actress.
	 */
	public void findShortestPath () {
		final Node actor1 = actorsGraph.getNodeByName("actor1");
		
		final Node actress2 = actorsGraph.getNodeByName("actress2");
	
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress2);
		
		assertNull(shortestPath);
		
		  // there is no path between these people
	}
	
	@Before
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {
		actorsGraph = new IMDBActorsGraph("actresses_first_10000_lines.list", "actresses_first_10000_lines.list");
		moviesGraph = new IMDBMoviesGraph("actresses_first_10000_lines.list", "actresses_first_10000_lines.list");
		searchEngine = new GraphSearchEngineImpl();
	}

	@Test
	/**
	 * Just verifies that the graphs could be instantiated without crashing.
	 */
	public void finishedLoading () {
		assertTrue(true);
		// Yay! We didn't crash
	}

	@Test
	/**
	 * Verifies that a specific movie has been parsed.
	 */
	public void testSpecificMovie () {
		testFindNode(moviesGraph, "Movie1 (2001)");
	}
	@Test
	public void testGenericGraph()
	{
		GraphSearchEngine testGraph = new GraphSearchEngineImpl();
		Node n1 = new testNodes("n1", null);
		Node n2 = new testNodes("n2", null);
		Node n3 = new testNodes("n3", null);
		Node n4 = new testNodes("n4", null);
		Node n5 = new testNodes("n5", null);
		ArrayList<Node> list1= new ArrayList<>();
		list1.add(n2);
		list1.add(n3);
		ArrayList<Node> list2= new ArrayList<>();
		list2.add(n4);
		list2.add(n1);
		ArrayList<Node> list3= new ArrayList<>();
		list3.add(n5);
		list3.add(n1);
		ArrayList<Node> list4= new ArrayList<>();
		list4.add(n2);
		list4.add(n5);
		ArrayList<Node> list5= new ArrayList<>();
		list5.add(n4);
		list5.add(n3);
		((testNodes)n1).setNeighbors(list1);
		((testNodes)n2).setNeighbors(list2);
		((testNodes)n3).setNeighbors(list3);
		((testNodes)n4).setNeighbors(list4);
		((testNodes)n5).setNeighbors(list5);

		Node n6 = new testNodes("n6", null);
		System.out.println("Node 1 path: " + testGraph.findShortestPath(n1, n6));
		System.out.println("Node 1 - Node 5 path: " + testGraph.findShortestPath(n1, n5));
		
		
		
		
		
	}
	@Test
	/**
	 * Verifies that a specific actress has been parsed.
	 */
	public void testSpecificActress () {
		testFindNode(actorsGraph, "Actress2");
	}

	/**
	 * Verifies that the specific graph contains a node with the specified name
	 * @param graph the Graph to search for the node
	 * @param name the name of the Node
	 */
	private static void testFindNode (Graph graph, String name) {
		final Collection<? extends Node> nodes = graph.getNodes();
		boolean found = false;
		for (Node node : nodes) {
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
	}
	
	
	
}
