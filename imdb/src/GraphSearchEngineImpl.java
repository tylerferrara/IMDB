import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	@Override
	public List<Node> findShortestPath(Node s, Node t) {
		List<? extends Node> visited = null;
		Queue<? extends Node> todo = null;
		int distance;
		
		todo.add(s);
		while (todo.size() > 0) {
			Node n = todo.poll();
			visited.add(n);
			
			if (n.equals(t)) {
				break;
			} else {
				for (Node n1 : n.getNeighbors()) {
					if (!todo.contains(n1) && !visited.contains(n1)) {
						todo.add(n1);
						distance ++; //Not incrementing correctly
					}
				}
			}
		}
		
		Stack<Node> path = null;
		Node n0 = t;
		
		for (int i = distance; i >= 0;) {
			path.push(n0);
			n0 = getNext(n0, s, i);
			i--;
		}
		
		List<Node> finalPath = null;
		
		while(path.size() > 0) {
			finalPath.add(path.pop());
		}
		
		return finalPath;
	}
	
	private int getDistance(Node s, Node t) {
		List<? extends Node> visited = null;
		Queue<? extends Node> todo = null;
		int distance;
		
		todo.add(s);
		while (todo.size() > 0) {
			Node n = todo.poll();
			visited.add(n);
			
			if (n.equals(t)) {
				break;
			} else {
				for (Node n1 : n.getNeighbors()) {
					if (!todo.contains(n1) && !visited.contains(n1)) {
						todo.add(n1);
						distance ++; //Not incrementing correctly
					}
				}
			}
		}
		
		return distance;
	}
	
	private Node getNext(Node s, Node t, int i) {
		for (Node n : s.getNeighbors()) {
			if (getDistance(n, t) == i - 1) {
				return n;
			}
		}
		return null;
	}

}
