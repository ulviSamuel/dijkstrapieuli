package it.volta.ts.ulivisamuel.dijkstrapieuli.biz;

import it.volta.ts.ulivisamuel.dijkstrapieuli.bean.AdjacencyMatrix;
import it.volta.ts.ulivisamuel.dijkstrapieuli.events.DijkstraConsoleListener;
import it.volta.ts.ulivisamuel.dijkstrapieuli.events.DijkstraEvent;
import it.volta.ts.ulivisamuel.dijkstrapieuli.exceptions.NodesException;

public class BizDijkstra 
{
	private DijkstraConsoleListener consoleListener;
	private AdjacencyMatrix         adjacencyMatrix;
	
	//---------------------------------------------------------------------------------------------
	
	public BizDijkstra()
	{
		consoleListener = null;
		adjacencyMatrix = new AdjacencyMatrix();
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void setDijkstraConsoleListener(DijkstraConsoleListener consoleListener)
	{
		this.consoleListener = consoleListener;
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void initAdjacencyMatrix(String nodes) throws NodesException
	{
		String nodesSplitted[] = nodes.split(",");
		if(nodesSplitted.length >= 4)
		{
			int nNodes = nodesSplitted.length;
			adjacencyMatrix.setAdjacencyMatrix(new int[nNodes][nNodes]);
			consoleListener.showMessage(new DijkstraEvent("\nOperazione andata a buon fine, tabella delle adiacenze istanziata."));
		}
		else
			throw new NodesException("numero di nodi inseriti non sufficiente.");
	}
}
