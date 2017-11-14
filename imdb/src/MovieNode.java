import java.util.Collection;

public class MovieNode implements Node{
	private String name;
	private Collection<?extends Node> neighbors;
	
	public MovieNode()
	{
		
	}
	public MovieNode(String name, Collection<?extends Node> actors)
	{
		this.name =name;
		this.neighbors=actors;
		
	}
	public String getName()
	{
		return this.name;
	}
	public Collection<? extends Node> getNeighbors()
	{
		return this.neighbors ;
	}

}
