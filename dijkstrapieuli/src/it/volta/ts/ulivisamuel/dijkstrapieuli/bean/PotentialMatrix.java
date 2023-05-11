package it.volta.ts.ulivisamuel.dijkstrapieuli.bean;

public class PotentialMatrix
{
	private String potentialMatrix[][];
	private String fields[];
	
	//---------------------------------------------------------------------------------------------
	
	public PotentialMatrix()
	{
		potentialMatrix = null;
		fields			= null;
	}
	
	//---------------------------------------------------------------------------------------------
	
	public String[][] getPotentialMatrix() 
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
	
	

	public void setPotentialMatrix(String[][] potentialMatrix) 
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
