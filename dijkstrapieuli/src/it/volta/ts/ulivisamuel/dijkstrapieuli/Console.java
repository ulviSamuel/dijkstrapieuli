package it.volta.ts.ulivisamuel.dijkstrapieuli;

import java.util.Scanner;

import it.volta.ts.ulivisamuel.dijkstrapieuli.biz.BizDijkstra;
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
		nodesRequest();
		scanner.close();
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void nodesRequest()
	{
		String mess = "\nInserisci i nodi appartenenti al sistema (es. 'A,B,C,D,E') altrimenti clicca invio per "
				    + "annullare l'operazione";
		String nodes = Util.leggiString(scanner, mess, false, null);
		//if(nodes != null)	
	}
	
	//---------------------------------------------------------------------------------------------
	
	//private void 
}
