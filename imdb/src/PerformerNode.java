import java.util.ArrayList;

public class PerformerNode implements Node{
	
	private String name;
	private ArrayList<MovieNode> neighbors;
	
	public PerformerNode() {
		
	}
	
	public PerformerNode(String name) {
		this.name = name;
	}
	
	public PerformerNode(String name, ArrayList<MovieNode> neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}
	
	public void setNeighbors(ArrayList<MovieNode> neighbors) {
		this.neighbors = neighbors;
	}
	
	public String getName() {
		return this.name;
	}

	public ArrayList<MovieNode> getNeighbors() {
		return this.neighbors;
	}
	
}
