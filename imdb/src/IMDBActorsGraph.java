import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

public class IMDBActorsGraph implements Graph {
	
	private ArrayList<PerformerNode> nodes; 
	private HashMap<String, MovieNode> visitedMovies;
	
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		visitedMovies = new HashMap<String, MovieNode>();
		parse(actorsFilename);
		parse(actressesFilename);
	}
	
	private void parse(String fileName) throws IOException {
		try {
			
			final Scanner scanner = new Scanner(new File(fileName), "ISO-8859-1");

			boolean searching = false;
			String actorName = null;
			ArrayList<String> movieList = new ArrayList<String>();
			ArrayList<PerformerNode> nodeList = new ArrayList<PerformerNode>();
			
			//Begin Parsing
			while(scanner.hasNext()) {
				if(!searching && 0 <= scanner.nextLine().indexOf("Name			Titles")) {
					scanner.nextLine();
					System.out.println("Time to search...");					
					searching = true;
				}
				
				String currentLine;
				
				if (searching){
					
					currentLine = scanner.nextLine();
					
					if(currentLine.equals("")) {
						// done with actor
						
						if(!movieList.isEmpty()) {
							System.out.println(actorName);
							System.out.println(movieList);
							System.out.println();
							
							nodeList.add(buildNodes(actorName, movieList));
						}
						
						movieList.clear();
						actorName = null;
						
					} else if(!currentLine.substring(0,1).equals("\t")) {
						
						// New actor
						
						for(int i = 0; i < currentLine.length() - 1; i++) {
							if(currentLine.substring(i, i+1).equals("\t")) {
								
								actorName = currentLine.substring(0, i).replaceAll(",", "");
								currentLine = currentLine.substring(i).replaceAll("\t", "");
								String movie = getMovie(currentLine);
								if(movie != null) {
									movieList.add(movie);
								}
								break;
							}
						}
						
					} else {
						// Add movies to actor
						currentLine = currentLine.replaceAll("\t", "");
						String movie = getMovie(currentLine);
						if(movie != null) {
							movieList.add(movie);
						}
					}
				}
			}
			scanner.close();
			
			if(!movieList.isEmpty()) {
				System.out.println(actorName);
				System.out.println(movieList);
				System.out.println();
				
				nodeList.add(buildNodes(actorName, movieList));
			}
			
			nodes = nodeList;
			
			ArrayList<String> collection = new ArrayList<String>();
			for(PerformerNode n: this.nodes) {
				collection.add(n.getName());
			}
			System.out.println(collection);
			
			System.out.println("FINISHED");
			
		} catch(IOException error) {
			throw error;
		}		
	}
	
	private PerformerNode buildNodes(String actor, ArrayList<String> movies) {
		// Create PerformerNode
		PerformerNode Actor = new PerformerNode(actor);
		
		// Create MovieNodes
		ArrayList<PerformerNode> thisPerformer = new ArrayList<PerformerNode>();
		thisPerformer.add(Actor);
		
		ArrayList<MovieNode> Movies = new ArrayList<MovieNode>();
		for(String movieTitle: movies) {
			if(visitedMovies.containsKey(movieTitle)) {
				// Update movie to have this actor as it's neighbor
				// Prevents duplicate Movies
				MovieNode tempMovie = visitedMovies.get(movieTitle);
				ArrayList<PerformerNode> mNeighbors = tempMovie.getNeighbors();
				mNeighbors.add(Actor); // <=== although Actor will mutate, it will always point to the same location
				tempMovie.setNeighbors(mNeighbors);
				Movies.add(tempMovie);
			} else {
				// NEW MOVIE
				MovieNode newMovie = new MovieNode(movieTitle, thisPerformer);
				Movies.add(newMovie);
				visitedMovies.put(movieTitle, newMovie);
			}
		}
		
		Actor.setNeighbors(Movies);
		
		return Actor;
	}
	
	private String getMovie(String currentLine) {
		String movieName = null;
		if(!currentLine.contains("\"") && !currentLine.contains("(TV)")) {
				
				int end = currentLine.indexOf(')');
				movieName = currentLine.substring(0, end+1);
			
		}
		return movieName;
	}
	
	//TAKE THIS OUT!!!!!!!!!!
	public void testMovieNodes() {
		for(Entry<String, MovieNode> m: this.visitedMovies.entrySet()) {
			MovieNode tempMovie = m.getValue();
			ArrayList<PerformerNode> n = tempMovie.getNeighbors();
			if(n.size() > 1) {
				System.out.println();
				System.out.println("=========NAME======== : " + tempMovie.getName());
				ArrayList<String> temp = new ArrayList<String>();
				for(PerformerNode p: n) {
					temp.add(p.getName());
				}
				System.out.println(temp);
			}
		}
		
	}
	
	public void testPerformerNode(String name) {
		for(PerformerNode p: this.nodes) {
			
		}
	}

	@Override
	public Collection<? extends Node> getNodes() {
		return this.nodes;
	}

	@Override
	public Node getNodeByName(String name) {

		return null;
	}

}
