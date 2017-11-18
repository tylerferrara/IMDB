import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test IMDBActorsGraph and IMDBMoviesGraph.
 */
public class DataLoadTest {
	Graph actorsGraph, moviesGraph;

	@Test(timeout=5000)
	public void finishedLoading () {
		System.out.println("CS210XGRDR +2 finishedLoading");
	}

	@Test(timeout=5000)
	public void loadedApproximatelyCorrectNumberOfActors () {
		final int TOLERANCE = 100;
		final int CORRECT = 2184;
		System.out.println(actorsGraph.getNodes().size());
		assertTrue(Math.abs(actorsGraph.getNodes().size() - CORRECT) <= TOLERANCE);
		System.out.println("CS210XGRDR +3 loadedApproximatelyCorrectNumberOfActors");
	}

	@Test(timeout=5000)
	public void loadedApproximatelyCorrectNumberOfMovies () {
		final int TOLERANCE = 100;
		final int CORRECT = 4519;
		System.out.println(moviesGraph.getNodes().size());
		assertTrue(Math.abs(moviesGraph.getNodes().size() - CORRECT) <= TOLERANCE);
		System.out.println("CS210XGRDR +3 loadedApproximatelyCorrectNumberOfMovies");
	}

	@Test(timeout=5000)
	public void testSpecificActor1 () {
		testFindNode(actorsGraph, "2 Chainz");
	}

	@Test(timeout=5000)
	public void testSpecificActor2 () {
		testFindNode(actorsGraph, "Abad, Javier (III)");
	}
	
	@Test(timeout=5000)
	public void testSpecificActor3 () {
		testFindNode(actorsGraph, "Aaker, Jedediah");
	}

	@Test(timeout=5000)
	public void testSpecificActress2 () {
		testFindNode(actorsGraph, "Abboud, Sereen");
	}
	
	@Test(timeout=5000)
	public void testSpecificActress3 () {
		testFindNode(actorsGraph, ";laskdjflsdkfj");
	}

	@Test(timeout=5000)
	public void testSpecificMovie1 () {
		testFindNode(moviesGraph, "What a Way to Go (1977)");
	}

	@Test(timeout=5000)
	public void testSpecificMovie2 () {
		testFindNode(moviesGraph, "Pele's Appeal (1990)");
	}
	
	@Test(timeout=5000)
	public void testSpecificMovie3 () {
		testFindNode(moviesGraph, "Carlo & Ester (1994)");
	}

	private static void testFindNode (Graph graph, String name) {
		Collection<? extends Node> nodes = graph.getNodes();
		boolean found = false;
		for (Node node : nodes) {
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
		System.out.println("CS210XGRDR +2 testFind" + name.replace(" ", ""));
	}

	@Before
	public void setUp () throws IOException {
		String path = "/Users/tylerferrara/Documents/CS210X/Project3";
		actorsGraph = new IMDBActorsGraph(path + "/actors_first_10000_lines.list", path + "/actresses_first_10000_lines.list");
		moviesGraph = new IMDBMoviesGraph(path + "/actors_first_10000_lines.list", path + "/actresses_first_10000_lines.list");
	}
}
