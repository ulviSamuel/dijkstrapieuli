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
		test();
		//nodesRequest();
		scanner.close();
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void test()
	{
		try {
			bizDijkstra.initAdjacencyMatrix("A,B,C,D,E,F,G,");
		} catch (NodesException e) {
			System.out.println("\nSi è verificato un errore: " + e.getMessage());
		}
		try {
			bizDijkstra.fillAdjacencyMatrix(0, "B2,C8");
			bizDijkstra.fillAdjacencyMatrix(1, "A2,D2,E6");
			bizDijkstra.fillAdjacencyMatrix(2, "A8,D2,F3");
			bizDijkstra.fillAdjacencyMatrix(3, "C2,B2,F9");
			bizDijkstra.fillAdjacencyMatrix(4, "B6,G5");
			bizDijkstra.fillAdjacencyMatrix(5, "C3,D9,G1");
			bizDijkstra.fillAdjacencyMatrix(6, "F1,E5");
			
		} catch (NodesException e) {
			System.out.println("\nSi è verificato un errore: " + e.getMessage());
		}
		try {
			bizDijkstra.calculateMinimumRoute("F", "F");
		} catch (NodesException e) {
			System.out.println("\nSi è verificato un errore: " + e.getMessage());
		}
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void nodesRequest()
	{
		String  mess  = "\nInserisci i nodi appartenenti alla rete (es. 'A,B,C,D,E', almeno 4 nodi) altrimenti clicca invio per "
				      + "uscire dal programma";
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
					nodesConnectionAndWeightRequest();
				} catch (NodesException e) {
					System.out.println("\nSi è verificato un errore: " + e.getMessage());
					goOn = true;
				}
			}
		}
		while(goOn);
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void nodesConnectionAndWeightRequest()
	{
		String  mess            = "Inserisci i collegamenti diretti del nodo indicato con gli altri nodi del sistema (almeno 1).\nPer "
				                + "ogni nodo collegato specifica poi il peso (>0) del percorso altrimenti premi invio per uscire dal programma"
				                + "\n(es. il nodo a cui vanno aggiunti i collegamenti ï¿½ il nodo A. I collegamenti diretti sono 'B2,C4,E9' "
				                + "dove\nla lettera corrisponde a un nodo direttamente collegato e il numero associato corrisponde al peso "
				                + "del percorso)";
		String  nodesSplitted[] = bizDijkstra.getNodi();
		String  connections     = null;
		boolean goOn            = false;
		for(int idx = 0; idx < nodesSplitted.length; ++idx)
		{
			do
			{
				goOn = false;
				System.out.println("\nNodo: " + nodesSplitted[idx]);
				connections = Util.leggiString(scanner, mess, false, null);
				if(connections != null)
				{
					try {
						bizDijkstra.fillAdjacencyMatrix(idx, connections);
					} catch (NodesException e) {
						System.out.println("\nSi ï¿½ verificato un errore: " + e.getMessage());
						goOn = true;
					}
				}
				else
					return;
			}
			while(goOn);
		}
		startNodeAndFinalNodeRequest();
	} 
	
	//---------------------------------------------------------------------------------------------
	
	private void startNodeAndFinalNodeRequest()
	{
		boolean goOn  = false;
		do
		{
			goOn  = false;
			String startNode = startNodeRequest();
			if(startNode != null)
			{
				String destNode = finalNodeRequest();
				if(destNode != null)
				{
					try {
						bizDijkstra.calculateMinimumRoute(startNode, destNode);
					} catch (NodesException e) {
						System.out.println("\nSi è verificato un errore: " + e.getMessage());
						goOn = true;
					}
				}
			}
		}
		while(goOn);
	}
	
	//---------------------------------------------------------------------------------------------
	
	private String startNodeRequest()
	{
		String mess         = "\nInserisci il nodo di partenza da cui calcolare il percorso minimo (es. 'A')";
		String startNodeReq = Util.leggiString(scanner, mess, false, null);
		return startNodeReq;
	}
	
	//---------------------------------------------------------------------------------------------
	
	private String finalNodeRequest()
	{
		String mess         = "\nInserisci il nodo di destinazione da cui calcolare il percorso minimo (es. 'F')";
		String startNodeReq = Util.leggiString(scanner, mess, false, null);
		return startNodeReq;
	}
}
