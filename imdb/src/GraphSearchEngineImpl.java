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
	
		List<Node> visited = new ArrayList<>();		//Might want to make a LinkedList
		Queue<Node> todo = new LinkedList<>(); 		//Need to create a data type that implements queue, (Queue is an interface?)
		Map<Node, Integer> distance = new HashMap<Node,Integer>();
		boolean found = false;
		
		todo.add(s);
		distance.put(s, Integer.valueOf(0));
		
		while (todo.size() > 0 && todo.poll()!=null) {
			
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
			Stack<Node> bPath = new Stack<Node>();
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
			List<Node> fPath = new LinkedList<>();	
			while (!bPath.isEmpty()) {
				fPath.add(bPath.pop());
			}	
			return fPath;
		} else {
			return null;

		}
	}
}
