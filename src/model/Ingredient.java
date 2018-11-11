package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {

	private StringProperty name;
	private IntegerProperty cost;
	
	public void setName(String name)
	{
		this.name.set(name);
	}
	
	public void setCost(int cost)
	{
		this.cost.set(cost);
	}
	
	public String getName()
	{
		return name.get();
	}
	
	public int getCost()
	{
		return cost.get();
	}
}
