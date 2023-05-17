package it.volta.ts.pieuli.dijkstrapieuli.bean;

public class AdjacencyMatrix 
{
	private int    adjacencyMatrix[][];
	private String fields[];
	
	//---------------------------------------------------------------------------------------------
	
	public AdjacencyMatrix()
	{
		adjacencyMatrix = null;
		fields          = null;
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
	
	public String[] getFields() 
	{
		return fields;
	}
	
	

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) 
	{
		this.adjacencyMatrix = adjacencyMatrix;
	}
	
	public void setValue(int row, int column, int value)
	{
		adjacencyMatrix[row][column] = value;
	}

	public void setFields(String fields[])
	{
		this.fields = fields;
	}
}
