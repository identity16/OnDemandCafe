package model;

import javafx.beans.property.StringProperty;

public class CoffeeBean extends Ingredient{
	
	private StringProperty origin;

	public CoffeeBean(String name, int cost, int amount) {
		super(name, cost, amount);
	}

	public CoffeeBean(Ingredient ingredient, int amount) {
		super(ingredient, amount);
	}

	public void setOrigin(String origin)
	{
		this.origin.set(origin);
	}
	
	public String getOrigin()
	{
		return (origin.get());
	}
}
