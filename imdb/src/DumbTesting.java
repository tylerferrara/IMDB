import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DumbTesting {
	
	public static void main(String[] args) throws IOException {
		IMDBActorsGraph actors = new IMDBActorsGraph("/Users/tylerferrara/Documents/CS210X/Project3/actresses_first_10000_lines.list", "/Users/tylerferrara/Documents/CS210X/Project3/empty.txt");
//		IMDBActorsGraph actors = new IMDBActorsGraph("C:\\Users\\johnm\\Desktop\\test_list.txt", "C:\\Users\\johnm\\Desktop\\test_list.txt");
//		IMDBActorsGraph actors = new IMDBActorsGraph("C:\\Users\\tyler\\Desktop", "C:\\Users\\tyler\\Desktop");
		
		Collection<? extends Node> nodes = actors.getNodes();
		System.out.println(nodes);
		System.out.println("START THIS SHIT!!!");
		
//		for(Node node: nodes) {
//			System.out.println();
//			System.out.println(node.getName());
//			System.out.println("--------------------");
//			for(Node mNode: node.getNeighbors()) {
//				System.out.println(mNode.getName());
//			}
//		}
		
	}
	
}
