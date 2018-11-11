package model;

import javafx.beans.property.StringProperty;

public class CoffeeBean extends Ingredient{
	
	private StringProperty origin;
	
	public void setOrigin(String origin)
	{
		this.origin.set(origin);
	}
	
	public String getOrigin()
	{
		return (origin.get());
	}
}
