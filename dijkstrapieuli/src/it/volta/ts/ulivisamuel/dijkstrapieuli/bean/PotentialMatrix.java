package it.volta.ts.ulivisamuel.dijkstrapieuli.bean;

public class PotentialMatrix
{
	private String potentialMatrix[][];
	private String fields[];
	
	//---------------------------------------------------------------------------------------------
	
	public PotentialMatrix()
	{
		potentialMatrix = null;
		setFields(null);
	}
	
	//---------------------------------------------------------------------------------------------
	
	public String[][] getAdjacencyMatrix() 
	{
		return potentialMatrix;
	}
	
	public String getValue(int row, int column)
	{
		return potentialMatrix[row][column];
	}
	
	public String[] getFields() 
	{
		return fields;
	}
	
	

	public void setAdjacencyMatrix(String[][] potentialMatrix) 
	{
		this.potentialMatrix = potentialMatrix;
	}
	
	public void setValue(int row, int column, String value)
	{
		potentialMatrix[row][column] = value;
	}

	public void setFields(String fields[])
	{
		this.fields = fields;
	}
}
