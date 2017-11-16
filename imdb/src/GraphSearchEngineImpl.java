import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	@Override
	public List<Node> findShortestPath(Node s, Node t) {
		
		Map<Node,Integer> valueMap = new HashMap<Node, Integer>();
		List<Node> visited = new LinkedList<>();
		LinkedList<Node> nodesToVisit = new LinkedList<>();
		nodesToVisit.add(s);
		boolean targetFound = false;
	
		int distance = 0;
		List<Node> finalPath = new LinkedList<>();
		while(nodesToVisit.size()>0)
		{
			
			Node n = nodesToVisit.remove();
			for(Node subNode: n.getNeighbors())
			{
				nodesToVisit.add(subNode);
			}
			visited.add(n);
			
			if(n!= null && n.equals(t))
			{
				targetFound = true;
				break;
			}
			else
			{
				
				for(Node node : nodesToVisit)
				{
					
					if(!nodesToVisit.contains(node) && !visited.contains(node))
					{
						nodesToVisit.add(node);
						valueMap.put(node, distance);
					}
				}
				distance++;
			}
		}
		if(targetFound)
		{
			Node n0 = t;
			for(int i = valueMap.get(t); i> 0; i--)
			{
				for(Node n1 : n0.getNeighbors())
				{
					if(valueMap.get(n1)== i -1)
					{
						finalPath.add(n1);
						n0=n1;
					}
				}
			}
		}
		LinkedList<Node> temp = new LinkedList<>();
		for(int i =finalPath.size()-1; i >= 0;i++)
		{
			temp.add(finalPath.get(i));
		}
		finalPath=temp;
		return finalPath;
	}
		List<? extends Node> visited = new ArrayList<>();		//Might want to make a LinkedList
		Queue<? extends Node> todo = new LinkedList<>(); 		//Need to create a data type that implements queue, (Queue is an interface?)
		Map<? extends Node, Integer> distance = null;
		boolean found = false;
		
		todo.add(s);
		distance.put(s, Integer.valueOf(0));
		
		while (todo.size() > 0) {
			Node n = todo.poll();
			visited.add(n); 
			if (n.equals(t)) {
				found = true;
				break;
			} else {
				//Add sub-nodes to todo, increase depth of search
				for (Node n1 : n.getNeighbors()) {
					if (!todo.contains(n1) && !visited.contains(n1)) {   
						todo.add(n1);
						if(!distance.containsKey(n1))
							distance.put(n1, Integer.valueOf(distance.get(n).intValue() + 1));							
					}
				}
			}
		}
		if (found) {
			Stack<? extends Node> bPath = null;
			bPath.push(t);
			Node n0 = t;
			int step = distance.get(t).intValue();
			Node min = null;
			int minD = Integer.MAX_VALUE;
			while (step - 1 >= 0) {
				for (Node n1 : n0.getNeighbors()) {
					if (distance.get(n1).intValue() < minD) {
						min = n1;
						minD = distance.get(n1).intValue();
					}
				}
				step = distance.get(min).intValue();
				bPath.push(min);
				n0 = min;
			}
			List<? extends Node> fPath = null;	
			while (!bPath.isEmpty()) {
				fPath.add(bPath.pop());
			}	
			return fPath;
		} else {
			return null;
		}
	}



