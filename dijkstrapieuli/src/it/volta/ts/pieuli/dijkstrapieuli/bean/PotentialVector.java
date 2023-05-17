package it.volta.ts.pieuli.dijkstrapieuli.bean;

public class PotentialVector
{
	private String potentialVector[];
	private String fields[];
	
	//---------------------------------------------------------------------------------------------
	
	public PotentialVector()
	{
		potentialVector = null;
		fields			= null;
	}
	
	//---------------------------------------------------------------------------------------------
	
	public String[] getPotentialVector() 
	{
		return potentialVector;
	}
	
	public String getValue(int column)
	{
		return potentialVector[column];
	}
	
	public String[] getFields() 
	{
		return fields;
	}
	
	

	public void setPotentialVector(String[] potentialVector) 
	{
		this.potentialVector = potentialVector;
	}
	
	public void setValue(int column, String value)
	{
		potentialVector[column] = value;
	}

	public void setFields(String fields[])
	{
		this.fields = fields;
	}
}
