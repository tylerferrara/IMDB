import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class IMDBActorsGraph implements Graph {
	
	private ArrayList<PerformerNode> nodes; 
	
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		try {
			
			final Scanner scanner = new Scanner(new File(actorsFilename), "ISO-8859-1");

			boolean searching = false;
			String actorName = null;
			ArrayList<String> movieList = new ArrayList<String>();
			ArrayList<PerformerNode> nodeList = new ArrayList<PerformerNode>();
			
			while(scanner.hasNext()) {
				if(!searching && 0 <= scanner.nextLine().indexOf("THE ACTORS LIST")) {
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
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
		ArrayList<PerformerNode> Perform = new ArrayList<PerformerNode>();
		Perform.add(Actor);
		
		ArrayList<MovieNode> Movies = new ArrayList<MovieNode>();
		for(String movie: movies) {
			Movies.add(new MovieNode(movie, Perform));
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


	@Override
	public Collection<? extends Node> getNodes() {
		return this.nodes;
	}

	@Override
	public Node getNodeByName(String name) {
		// Find node with given name (key)
		//
		// Implimentation depends on data type
		//
		return null;
	}

}
