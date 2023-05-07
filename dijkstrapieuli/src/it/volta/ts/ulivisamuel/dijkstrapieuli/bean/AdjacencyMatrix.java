package it.volta.ts.ulivisamuel.dijkstrapieuli.bean;

public class AdjacencyMatrix 
{
	private int adjacencyMatrix[][];
	
	//---------------------------------------------------------------------------------------------
	
	public AdjacencyMatrix()
	{
		adjacencyMatrix = null;
	}
	
	//---------------------------------------------------------------------------------------------
	
	public int[][] getAdjacencyMatrix() 
	{
		return adjacencyMatrix;
	}
	
	public int getValue(int row, int column)
	{
		return adjacencyMatrix[row][column];
	}
	
	

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) 
	{
		this.adjacencyMatrix = adjacencyMatrix;
	}
	
	public void setValue(int row, int column, int value)
	{
		adjacencyMatrix[row][column] = value;
	}
}
