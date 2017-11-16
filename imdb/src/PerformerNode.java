import java.util.ArrayList;

public class PerformerNode implements Node{
	
	private String name;
	private ArrayList<MovieNode> neighbors;
	/**
	 * Constructor for PerformerNode
	 */
	public PerformerNode() {
		
	}
	/**
	 * Constructor for PerformerNode
	 * @param name Name of PerformerNode
	 */
	public PerformerNode(String name) {
		this.name = name;
	}
	/**
	 * Constructor for PerformerNode
	 * @param name Name of PerformerNode
	 * @param neighbors Set of adjacent Nodes
	 */
	public PerformerNode(String name, ArrayList<MovieNode> neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}
	/**
	 * setNeighbors() Sets adjacent Nodes
	 * @param neighbors Set of adjacent Nodes
	 */
	public void setNeighbors(ArrayList<MovieNode> neighbors) {
		this.neighbors = neighbors;
	}
	/**getName()
	 * @return Returns name of PerformerNode
	 */
	public String getName() {
		return this.name;
	}
	/**getNeighbors() Gets the set of adjacent nodes
	 * @return Returns set of nodes adjacent
	 */
	public ArrayList<MovieNode> getNeighbors() {
		return this.neighbors;
	}
	/**
	 * equals() Compares if n1 is equal to this
	 * @param n1 Node 1
	 * @return
	 */
	public boolean equals(Node n1)
	{
		if(this.name.equals(n1.getName()))
		{
			return true;
		}
		else
			return false;
	}
	
}
