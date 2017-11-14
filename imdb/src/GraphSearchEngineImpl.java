import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	@Override
	public List<Node> findShortestPath(Node s, Node t) {
		List<? extends Node> visited = new ArrayList<>();		//Might want to make a LinkedList
		Queue<? extends Node> todo = null; 						//Need to create a data type that implements queue, (Queue is an interface?)
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
			while (step > 0) {
				for (Node n1 : n0.getNeighbors()) {
					if (distance.get(n1).intValue() == distance.get(n0).intValue() - 1) {
						bPath.push(n1);
						step--;
						n0 = n1;
						break; //I'm assuming this will just break the for loop and not the while loop
					}
				}
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


}
