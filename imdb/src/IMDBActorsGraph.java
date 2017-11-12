import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class IMDBActorsGraph implements Graph {
	
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		try {
			
			final Scanner scanner = new Scanner(new File(actorsFilename), "ISO-8859-1");

			boolean searching = false;
			boolean endOfActor = false;
			
			while(scanner.hasNext()) {
				if(!searching && 0 <= scanner.nextLine().indexOf("THE ACTORS LIST")) {
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
					scanner.nextLine();
					System.out.println("Time to search...");					
					searching = true;
				}
				
				String currentLine;
				
				if (searching){
					currentLine = scanner.nextLine();
					if(currentLine.equals("")){
						endOfActor = true;
						currentLine = scanner.nextLine();
					}
					// finding actor names
					if(endOfActor) {
						endOfActor = false;
						for(int i = 0; i < currentLine.length() - 1; i++) {
							if(currentLine.substring(i, i+1).equals("\t")) {
								String actorName = currentLine.substring(0, i).replaceAll(",", "");
								
								currentLine = currentLine.substring(i);
								break;
							}
						}
 					}
				}
			}
			scanner.close();
			System.out.println("FINISHED");
			
		} catch(IOException error) {
			throw error;
		}
	}
	
	private void getActors() {
		
	}

	@Override
	public Collection<? extends Node> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNodeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
