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
	
	/**
	 * 
	 * @param actorsFilename path
	 * @param actressesFilename path
	 * @throws IOException
	 */
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		this.visitedMovies = new HashMap<String, MovieNode>();
		this.nodes = new ArrayList<PerformerNode>();
		parse(actorsFilename);
		parse(actressesFilename);
	}
	
	/**
	 * This function scans through the file and builds
	 * both Movie and Actor nodes on the fly.
	 * 
	 * @param String filename to parse
	 * @throws IOException
	 */
	private void parse(String fileName) throws IOException {
		try {
			
			final Scanner scanner = new Scanner(new File(fileName), "ISO-8859-1");

			boolean searching = false;
			String actorName = null;
			ArrayList<String> movieList = new ArrayList<String>();
			ArrayList<PerformerNode> nodeList = new ArrayList<PerformerNode>();
			
			//Begin Parsing
			while(scanner.hasNext()) {
				String currentLine = scanner.nextLine();
				
				if(!searching && currentLine.contains("Name") && currentLine.contains("Titles")) {
					scanner.nextLine();				
					searching = true;
				}
				
				if (searching){
					
					if(currentLine.contains("-----------------------------------------------------------------------------")) {
						// end of parsing
						searching = false;
						break;
					}
					
					if(currentLine.equals("")) {
						// done with actor
						
						if(!movieList.isEmpty()) {
							nodeList.add(buildNodes(actorName, movieList));
						}
						
						movieList.clear();
						actorName = null;
						
					} else if(!currentLine.substring(0,1).equals("\t")) {
						
						// New actor
						for(int i = 0; i < currentLine.length() - 1; i++) {
							if(currentLine.substring(i, i+1).equals("\t")) {
								
								actorName = currentLine.substring(0, i);
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

			if(!nodeList.isEmpty()) {
				this.nodes.addAll(nodeList);
			}
			
		} catch(IOException error) {
			throw error;
		}		
	}
	
	/**
	 * @param actor's name
	 * @param list of movie name's the actor is in
	 * @return the constructed performer node with name: actor
	 */
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
	
	/**
	 * 
	 * @param the currentLine of the scanner
	 * @return the name of the movie (as long as it's not a TV show)
	 */
	private String getMovie(String currentLine) {
		String movieName = null;
		if(!currentLine.contains("\"") && !currentLine.contains("(TV)")) {
				int end = currentLine.indexOf(')');
				movieName = currentLine.substring(0, end+1);
		}
		return movieName;
	}

	/**
	 * 
	 * @param  null
	 * @return ArrayList of PerformerNodes from parsed files
	 */
	@Override
	public Collection<? extends Node> getNodes() {
		return this.nodes;
	}

	/**
	 * @param  the name of the node being searched
	 * @return the node searched for
	 */
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
