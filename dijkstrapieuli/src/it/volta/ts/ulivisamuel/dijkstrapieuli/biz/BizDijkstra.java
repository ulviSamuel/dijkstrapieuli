package it.volta.ts.ulivisamuel.dijkstrapieuli.biz;

import java.util.ArrayList;
import java.util.List;

import it.volta.ts.ulivisamuel.dijkstrapieuli.bean.AdjacencyMatrix;
import it.volta.ts.ulivisamuel.dijkstrapieuli.bean.PotentialVector;
import it.volta.ts.ulivisamuel.dijkstrapieuli.events.DijkstraConsoleListener;
import it.volta.ts.ulivisamuel.dijkstrapieuli.events.DijkstraEvent;
import it.volta.ts.ulivisamuel.dijkstrapieuli.exceptions.NodesException;

public class BizDijkstra 
{
	private DijkstraConsoleListener consoleListener;
	private AdjacencyMatrix         adjacencyMatrix;
	private PotentialVector         potentialVector;
	
	//---------------------------------------------------------------------------------------------
	
	public BizDijkstra()
	{
		consoleListener = null;
		adjacencyMatrix = new AdjacencyMatrix();
		potentialVector = new PotentialVector();
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
	
	public void initAdjacencyMatrix(String nodes) throws NodesException
	{
		String nodesSplitted[] = nodes.split(",");
		int    nNodes  = nodesSplitted.length;
		String errMess = isNodesNamesAccepted(nodesSplitted, nNodes);
		if(errMess.equals(""))
		{
			adjacencyMatrix.setFields(nodesSplitted);
			adjacencyMatrix.setAdjacencyMatrix(new int[nNodes][nNodes]);
			consoleListener.showMessage(new DijkstraEvent("\nOperazione andata a buon fine, tabella delle adiacenze istanziata."));
		}
		else
			throw new NodesException(errMess);
	}
	
	//---------------------------------------------------------------------------------------------
	
	private String isNodesNamesAccepted(String nodesSplitted[], int nNodes)
	{
		List<String> differentNodes = new ArrayList<String>();
		for(int idx = 0; idx < nNodes; ++idx)
		{
			if(nodesSplitted[idx].length() != 1)
				return "nome dei nodi troppo lungo.";
			try {
				Integer.parseInt(nodesSplitted[idx]);
				return "i nodi hanno un numero come nome.";
			}
			catch(NumberFormatException e){}
			boolean equalNodes = false;
			for(String node : differentNodes)
			{
				if(nodesSplitted[idx].equals(node))
				{
					equalNodes = true;
					break;
				}
			}
			if(equalNodes)
				return "ci sono nodi non univoci.";
			differentNodes.add(nodesSplitted[idx]);
		}
		if(differentNodes.size() <4)
			return "numero di nodi inseriti non sufficiente.";
		return "";
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void fillAdjacencyMatrix(int nodePos, String connections) throws NodesException
	{
		String nodesSplitted[] = connections.split(",");
		if(nodesSplitted.length > 0)
		{
			for(int idx = 0; idx < nodesSplitted.length; ++idx)
			{
				if(nodesSplitted[idx].length() >= 2)
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
							throw new NodesException("nodi collegati non trovati.");
					}
					else
						throw new NodesException("peso dei persorsi inaccettabile.");
				}
				else
					throw new NodesException("mancano dei campi.");
			}
			consoleListener.showMessage(new DijkstraEvent("\nOperazione andata a buon fine, collegamenti aggiunti alla tabella."));
		}
		else
			throw new NodesException("numero di collegamenti inseriti non sufficiente.");
	}
	
	//---------------------------------------------------------------------------------------------
	
	public void calculateMinimumRoute(String startingNode, String destinationNode) throws NodesException
	{
		int startingNodePos = searchNodePosition(startingNode);
		if(!startingNode.equals(destinationNode))
		{
			if(startingNodePos != -1)
			{
				int destinationNodePos = searchNodePosition(destinationNode);
				if(destinationNodePos != -1)
				{
					potentialVector.setFields(adjacencyMatrix.getFields());
					initPotentialVector();
					potentialVector.setValue(startingNodePos, "0fix");
					dijkstraAlgorithm(startingNodePos, destinationNodePos);
					consoleListener.showMessage(new DijkstraEvent("\nPercorso minimo: " + getMinPath(startingNodePos, destinationNodePos)));
				}
				else
					throw new NodesException("nodo di arrivo non trovato.");
			}
			else
				throw new NodesException("nodo di partenza non trovato.");
		}
		else
			throw new NodesException("non si può calcolare il percorso minimo partendo da un nodo e arrivando allo stesso.");
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void initPotentialVector()
	{
		int nNodes = potentialVector.getFields().length;
		String potentialVectorTmp[] = new String[nNodes];
		for(int idx = 0; idx < nNodes; ++idx)
			potentialVectorTmp[idx] = "inf";
		potentialVector.setPotentialVector(potentialVectorTmp);
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void dijkstraAlgorithm(int startingNodePos, int destinationNodePos)
	{
		if(startingNodePos == destinationNodePos)
			return;
		else
		{
			addAdjacentPotensials(startingNodePos);
			int minNodePotPos = searchAndFixMinNodePotentialPos();
			dijkstraAlgorithm(minNodePotPos, destinationNodePos);
		}
	}
	
	//---------------------------------------------------------------------------------------------
	
	private String getMinPath(int startingNodePos, int destinationNodePos)
	{
		String path = "";
		int    jdx  = destinationNodePos;
		for(int idx = 0; idx < adjacencyMatrix.getFields().length; ++idx)
		{
			if(idx != jdx && !potentialVector.getValue(idx).equals("inf") 
					      && adjacencyMatrix.getValue(idx, jdx) != 0)
			{
				int minNodePot = Integer.parseInt(potentialVector.getValue(idx).split("fix")[0]);
				int potential  = minNodePot + adjacencyMatrix.getValue(idx, jdx);
				if(Integer.toString(potential).equals(potentialVector.getValue(jdx).split("fix")[0]))
				{
					path = " -> " + potentialVector.getFields()[jdx] + path;
					jdx = idx;
					idx = -1;
					if(jdx == startingNodePos)
					{
						path = potentialVector.getFields()[jdx] + path;
						break;
					}
				}
			}
		}
		return path;
	}
	
	//---------------------------------------------------------------------------------------------
	
	private int searchAndFixMinNodePotentialPos()
	{
		int minPos         = -1;
		int minPot         = Integer.MAX_VALUE;
		String potVector[] = potentialVector.getPotentialVector();
		for(int idx = 0; idx < potentialVector.getFields().length; ++idx)
		{
			try {
				int value = Integer.parseInt(potVector[idx]);
				if(value < minPot)
				{
					minPot = value;
					minPos = idx;
				}
			} catch (NumberFormatException e) {}
		}
		potentialVector.setValue(minPos, potentialVector.getValue(minPos) + "fix");
		return minPos;
	}
	
	//---------------------------------------------------------------------------------------------
	
	private void addAdjacentPotensials(int minNodePotPos)
	{
		for(int idx = 0; idx < potentialVector.getFields().length; ++idx)
		{
			if(adjacencyMatrix.getValue(idx, minNodePotPos) != 0)
			{
				if(!potentialVector.getValue(idx).contains("fix"))
				{
					int minNodePot = Integer.parseInt(potentialVector.getValue(minNodePotPos).split("fix")[0]);
					int potential  = minNodePot + adjacencyMatrix.getValue(idx, minNodePotPos);
					try {
						int exPot = Integer.parseInt(potentialVector.getValue(idx));
						if(potential < exPot)
							potentialVector.setValue(idx, potential + "");
					} catch (NumberFormatException e) {
						potentialVector.setValue(idx, potential + "");
					}
				}
			}
		}
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
