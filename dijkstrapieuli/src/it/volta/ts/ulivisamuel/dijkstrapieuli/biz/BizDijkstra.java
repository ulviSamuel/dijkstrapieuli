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
	
	public String[] getNodi()
	{
		return adjacencyMatrix.getFields();
	}
	
	//---------------------------------------------------------------------------------------------
	
	//TODO: fare in modo che i nomi dei nodi possano essere una sola lettera
	
	public void initAdjacencyMatrix(String nodes) throws NodesException
	{
		String nodesSplitted[] = nodes.split(",");
		if(nodesSplitted.length >= 4)
		{
			int nNodes = nodesSplitted.length;
			adjacencyMatrix.setFields(nodesSplitted);
			adjacencyMatrix.setAdjacencyMatrix(new int[nNodes][nNodes]);
			consoleListener.showMessage(new DijkstraEvent("\nOperazione andata a buon fine, tabella delle adiacenze istanziata."));
		}
		else
			throw new NodesException("numero di nodi inseriti non sufficiente.");
	}
	
	//TODO: ordinare campi in ordine crescende/dec
	
	//---------------------------------------------------------------------------------------------
	
	public void fillAdjacencyMatrix(int nodePos, String connections) throws NodesException
	{
		String nodesSplitted[] = connections.split(",");
		if(nodesSplitted.length > 0)
		{
			for(int idx = 0; idx < nodesSplitted.length; ++idx)
			{
				int weight = stringNodeWeightToInt(nodesSplitted[idx].substring(1));
				if(weight >= 1)
				{
					int positionConnection = searchNodePosition(nodesSplitted[idx].substring(0, 1));
					if(positionConnection != -1)
					{
						adjacencyMatrix.setValue(nodePos, positionConnection, weight);
						adjacencyMatrix.setValue(positionConnection, nodePos, weight);
					}
					else
						throw new NodesException("nodi non trovati.");
				}
				else
					throw new NodesException("peso dei persorsi inaccettabile.");
			}
			consoleListener.showMessage(new DijkstraEvent("\nOperazione andata a buon fine, collegamenti aggiunti alla tabella."));
		}
		else
			throw new NodesException("numero di collegamenti inseriti non sufficiente.");
	}
	
	//---------------------------------------------------------------------------------------------
	
	private int searchNodePosition(String node)
	{
		String fields[] = adjacencyMatrix.getFields();
		for(int idx = 0; idx < fields.length; ++idx)
			if(fields[idx].equals(node))
				return idx;
		return -1;
	}
	
	//---------------------------------------------------------------------------------------------
	
	private int stringNodeWeightToInt(String stringWeight)
	{
		int intWeight = 0;
		try {
			intWeight = Integer.parseInt(stringWeight);
		}
		catch(NumberFormatException e){
			intWeight = 0;
		}
		return intWeight;
	}
}
