import java.util.ArrayList;
import java.util.Collection;

public class MovieNode implements Node{
	private String name;
	private ArrayList<PerformerNode> neighbors;
	
	
	/**
	 * Constructor for MovieNode
	 */
	public MovieNode() {
		
	}
	/**
	 * Constructor for MovieNode
	 * @param name Name of MovieNode
	 */
	public MovieNode(String name) {
		this.name = name;
	}
	/**
	 * Constructor for MovieNode
	 * @param name Name of movie title
	 * @param neighbors list of actors nodes adjacent to movie
	 */
	public MovieNode(String name, ArrayList<PerformerNode> neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}
	/**
	 * setNeighbors() sets the adjacent nodes of MovieNode 
	 * @param neighbors the set of adjacent nodes
	 */
	public void setNeighbors(ArrayList<PerformerNode> neighbors) {
		this.neighbors = neighbors;
	}
	/**getName() Returns the name of the movie
	 * @return Returns the name of the Movie
	 */
	public String getName() {
		return this.name;
	}
	/**getNeighbors() Returns adjacent nodes
	 * @return Returns adjacent nodes
	 */
	public ArrayList<PerformerNode> getNeighbors() {
		return this.neighbors;
	}

}
