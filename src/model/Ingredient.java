package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {

	private StringProperty name;
	private IntegerProperty cost;
	private IntegerProperty stockAmount;
	
	public void setName(String name)
	{
		this.name.set(name);
	}
	
	public void setCost(int cost)
	{
		this.cost.set(cost);
	}
	
	public void setNumber(int stockAmount)
	{
		this.stockAmount.set(stockAmount);
	}
	
	public String getName()
	{
		return (name.get());
	}
	
	public int getCost()
	{
		return (cost.get());
	}
	
	public int getstockAmount()
	{
		return (stockAmount.get());
	}
}
