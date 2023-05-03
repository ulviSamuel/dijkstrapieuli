package it.volta.ts.ulivisamuel.dijkstrapieuli.bean;

import java.util.List;

public class Node
{
	private int           potential;
	private List<Node>    relatedNodes;
	private List<Integer> weights;
	
	//---------------------------------------------------------------------------------------------
	
	public Node(int potential, List<Node> relatedNodes, List<Integer> weights)
	{
		super();
		this.potential = potential;
		this.relatedNodes = relatedNodes;
		this.weights = weights;
	}
	
	//---------------------------------------------------------------------------------------------

	public int getPotential()
	{
		return potential;
	}

	public List<Node> getRelatedNodes()
	{
		return relatedNodes;
	}

	public List<Integer> getWeights() 
	{
		return weights;
	}

	
	
	public void setPotential(int potential) 
	{
		this.potential = potential;
	}

	public void setRelatedNodes(List<Node> relatedNodes)
	{
		this.relatedNodes = relatedNodes;
	}

	public void setWeights(List<Integer> weights)
	{
		this.weights = weights;
	}
}
