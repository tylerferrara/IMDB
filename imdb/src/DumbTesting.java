import java.io.IOException;
import java.util.ArrayList;

public class DumbTesting {
	
	public static void main(String[] args) throws IOException {
		IMDBActorsGraph actors = new IMDBActorsGraph("/Users/tylerferrara/Documents/CS210X/Project3/IMDB/actresses_test.list", "/Users/tylerferrara/Documents/CS210X/Project3/IMDB/actors_test.list");
//		IMDBActorsGraph actors = new IMDBActorsGraph("C:\\Users\\johnm\\Desktop\\test_list.txt", "C:\\Users\\johnm\\Desktop\\test_list.txt");
//		IMDBActorsGraph actors = new IMDBActorsGraph("C:\\Users\\tyler\\Desktop", "C:\\Users\\tyler\\Desktop");
		
		
		String actor1 = "Tyler";
		
		ArrayList<String> moviesForActor1 = new ArrayList<String>();
		moviesForActor1.add("Inception");
		moviesForActor1.add("Transformers");
		moviesForActor1.add("Matchbox");
		
		// Create PerformerNode
		PerformerNode Actor1 = new PerformerNode(actor1);
		
		// Create MovieNodes
		ArrayList<PerformerNode> Perform = new ArrayList<PerformerNode>();
		Perform.add(Actor1);
		
		ArrayList<MovieNode> Movies = new ArrayList<MovieNode>();
		for(String movie: moviesForActor1) {
			Movies.add(new MovieNode(movie, Perform));
		}
		
		Actor1.setNeighbors(Movies);
		
		System.out.println(Actor1.getName());
		
		System.out.println("____________");

		for(MovieNode m: Actor1.getNeighbors()) {
			System.out.println(m.getName());
		}
		
		actors.testMovieNodes();
		
		// TEST PERFORMER NODE	
		actors.testPerformerNode("\'Draico\' Johnson Dondraico");
		
		
		System.out.println();
		
		// A TEST
		actors.justATest();
		
	}
	
}
