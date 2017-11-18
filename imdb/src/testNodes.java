import java.util.ArrayList;
import java.util.Collection;



public class testNodes implements Node{

	private String name;
	private ArrayList<Node> neighbors;
	public testNodes(String name, ArrayList<Node> neighbors)
	{
		this.name = name;
		this.neighbors= neighbors;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<? extends Node> getNeighbors() {
		return this.neighbors;
	}
	public String toString()
	{
		return this.getName();
	}
	public void setNeighbors(ArrayList<Node> neighbors)
	{
		this.neighbors=neighbors;
	}
	public boolean equals(Node o)
	{
		if(this.name.equals(o.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
