import java.util.ArrayList;
import java.util.Collection;

public class MovieNode implements Node{
	private String name;
	private ArrayList<PerformerNode> neighbors;
	
	public MovieNode() {
		
	}
	
	public MovieNode(String name) {
		this.name = name;
	}
	
	public MovieNode(String name, ArrayList<PerformerNode> neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}
	
	public void setNeighbors(ArrayList<PerformerNode> neighbors) {
		this.neighbors = neighbors;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<PerformerNode> getNeighbors() {
		return this.neighbors;
	}

}
