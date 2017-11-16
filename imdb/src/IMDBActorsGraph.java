import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
			
			this.nodes = nodeList;
			
			System.out.println("FINISHED");
			
		} catch(IOException error) {
			throw error;
		}		
	}
	
	private PerformerNode buildNodes(String actor, ArrayList<String> movies) {
		//Make actor without any neighbors
		PerformerNode myNewNode = new PerformerNode(actor);
		
		//build movies based on its name
		
			//Collection for movie nodes (once they are built)
			ArrayList<MovieNode> movieCollection = new ArrayList<MovieNode>();
		
		for(String movie: movies) {
			
			if(this.visitedMovies.containsKey(movie)) {
				// OLD NODE
				MovieNode oldMovieNode = this.visitedMovies.get(movie);
				ArrayList<PerformerNode> oldMovieNeighbors = oldMovieNode.getNeighbors();
				
					//update moive neighbors to add currect actor
				oldMovieNeighbors.add(myNewNode);
				oldMovieNode.setNeighbors(oldMovieNeighbors);
				movieCollection.add(oldMovieNode);
				
			} else {
				// NEW NODE
				MovieNode newMovieNode = new MovieNode(movie);
				ArrayList<PerformerNode> oneActor = new ArrayList<PerformerNode>();
				newMovieNode.setNeighbors(oneActor);
				movieCollection.add(newMovieNode);
				this.visitedMovies.put(movie, newMovieNode);
			}
			
		}
		
		//add movieCollection to My New Node
		myNewNode.setNeighbors(movieCollection);
		
		return myNewNode;
	}
	
	
	private String getMovie(String currentLine) {
		String movieName = null;
		if(!currentLine.contains("\"") && !currentLine.contains("(TV)")) {
				
				int end = currentLine.indexOf(')');
				movieName = currentLine.substring(0, end+1);
			
		}
		return movieName;
	}
	
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
			if(p.getName().equals(name)) {
				System.out.println("All Movies For: " + name);
				ArrayList<String> temp = new ArrayList<String>();
				for(MovieNode m: p.getNeighbors()) {
					temp.add(m.getName());
				}
				System.out.println(temp);
			}
		}
	}
	
	public void justATest() {
		MovieNode n = this.visitedMovies.get("Honey 2 (2011)");
		ArrayList<String> w = new ArrayList<String>();
		for(PerformerNode p: n.getNeighbors()) {
			w.add(p.getName());
		}
		System.out.println(w);
	}

	@Override
	public Collection<? extends Node> getNodes() {
		return this.nodes;
	}

	@Override
	public Node getNodeByName(String name) {
		PerformerNode myNode = null;
		for(PerformerNode node: this.nodes) {
			if(node.getName().equals(name)) {
				myNode = node;
				break;
			}
		}
		return myNode;
	}

}
