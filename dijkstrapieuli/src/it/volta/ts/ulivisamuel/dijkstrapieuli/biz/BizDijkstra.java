package it.volta.ts.ulivisamuel.dijkstrapieuli.biz;

import it.volta.ts.ulivisamuel.dijkstrapieuli.bean.AdjacencyMatrix;
import it.volta.ts.ulivisamuel.dijkstrapieuli.exceptions.NodesException;

public class BizDijkstra 
{
	private AdjacencyMatrix adjacencyMatrix;
	
	//---------------------------------------------------------------------------------------------
	
	public BizDijkstra()
	{
		adjacencyMatrix = new AdjacencyMatrix();
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void initAdjacencyMatrix(String nodes) throws NodesException
	{
		String nodesSplitted[] = nodes.split(",");
		if(nodesSplitted.length >= 4)
		{
			int nNodes = nodesSplitted.length;
			adjacencyMatrix.setAdjacencyMatrix(new int[nNodes][nNodes]);
		}
		else
			throw new NodesException("numero di nodi inseriti non sufficiente.");
	}
}
