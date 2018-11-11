package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.StringProperty;

public class Beverage {
	
	private String name;
	private List<Ingredient> ingredients;
	private boolean isExtra;
	private boolean isHot;
	private int price;
	
	public Beverage(Menu menu)
	{
		this.name = menu.getName();
		this.isExtra = false;
		this.isHot = false;
		this.price = menu.getPrice();
		this.ingredients = new ArrayList<>();
		
		for(Ingredient ingredient : menu.getBaseIngredients())
			ingredients.add(ingredient);
		
		for(Ingredient ingredient : menu.getExtraIngredients())
		{
			if(!ingredients.contains(ingredient))
				ingredients.add(ingredient);
		}
	}
	
	public void setIsExtra(boolean isExtra)
	{
		this.isExtra = isExtra;
	}
	
	public boolean getIsExtra()
	{
		return (isExtra);
	}
	
	public void setisHot(boolean isHot)
	{
		this.isHot = isHot;
	}
	
	public void setIngredientAmount(String ingridentName, int amount)
	{
		findIngredient(ingridentName).setAmount(amount);
	}
	
	public int getIngredientAmount(String ingredientName)
	{
		return (findIngredient(ingredientName).getAmount());
	}
	
	private Ingredient findIngredient(String name) 
	{
		if(name == null) return null;

		for (Ingredient ingredient : ingredients) {
			if (name.equals(ingredient.getName()))
				return ingredient;
		}
		return null;
	}
	
	public int getPrice()
	{
		return (price);
	}
	
	public void updatePricebyS(Menu menu)
	{
		if (this.getIsExtra())
			price = price + Menu.getSizePrice();
		else
			price = price - Menu.getSizePrice();
	}
}
