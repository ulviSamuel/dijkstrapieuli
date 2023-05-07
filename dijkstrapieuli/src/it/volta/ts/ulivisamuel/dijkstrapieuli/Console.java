package it.volta.ts.ulivisamuel.dijkstrapieuli;

import java.util.Scanner;

import it.volta.ts.ulivisamuel.dijkstrapieuli.biz.BizDijkstra;
import it.volta.ts.ulivisamuel.dijkstrapieuli.events.DijkstraConsoleListener;
import it.volta.ts.ulivisamuel.dijkstrapieuli.exceptions.NodesException;
import it.volta.ts.ulivisamuel.dijkstrapieuli.util.Util;

public class Console 
{
	private Scanner     scanner;
	private BizDijkstra bizDijkstra;
	
	//---------------------------------------------------------------------------------------------
	
	public Console()
	{
		bizDijkstra = new BizDijkstra();
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void execute()
	{
		scanner = new Scanner(System.in);
		bizDijkstra.setDijkstraConsoleListener(new DijkstraConsoleListener());
		nodesRequest();
		scanner.close();
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void nodesRequest()
	{
		String  mess  = "\nInserisci i nodi appartenenti alla rete (es. 'A,B,C,D,E', almeno 4 nodi) altrimenti clicca invio per "
				      + "annullare l'operazione";
		String  nodes = null;
		boolean goOn  = false;
		do
		{
			goOn  = false;
			nodes = Util.leggiString(scanner, mess, false, null);
			if(nodes != null)
			{
				try {
					bizDijkstra.initAdjacencyMatrix(nodes);
				} catch (NodesException e) {
					System.out.println("\nSi è verificato un errore: " + e.getMessage());
					goOn = true;
				}
			}
		}
		while(goOn);
	}
	
	//---------------------------------------------------------------------------------------------
	
	
}
