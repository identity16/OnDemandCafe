package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static int basePrice;					// 湲곕낯 媛�寃�
	private static final Inventory INVENTORY = Inventory.getInstance();		// 李쎄퀬

	private String name;							// 硫붾돱紐�
	private List<Ingredient> baseIngredients;		// 踰좎씠�뒪 �옱猷�
	private List<Ingredient> extraIngredients;		// 異붽� �옱猷�
	private int price;								// 硫붾돱 媛�寃�
	private static int sizePrice = 1500;
	
	public Menu(String name) {
		this.name = name;
		this.baseIngredients = new ArrayList<>();
		this.extraIngredients = new ArrayList<>();
		this.price = Menu.basePrice;
	}

	// �옱猷뚮챸�쑝濡� �옱猷� �깘�깋
	private Ingredient findBaseIngredient(String name) {
		return findIngredient(baseIngredients, name);
	}

	private Ingredient findExtraIngredient(String name) {
		return findIngredient(extraIngredients, name);
	}

	private Ingredient findIngredient(List<Ingredient> list, String name) {
		if(name == null) return null;

		for (Ingredient ingredient : list) {
			if (name.equals(ingredient.getName()))
				return ingredient;
		}

		return null;
	}

	// �옱猷� 異붽� / �젣嫄�
	public void addBaseIngredient(String name) {
		Ingredient invIngredient = INVENTORY.getIngredient(name);		// 李쎄퀬 �옱猷�
		if(invIngredient == null) return;	// 李쎄퀬�뿉 �뾾�뒗 �옱猷�

		if(findBaseIngredient(name) == null) {
			baseIngredients.add(new Ingredient(invIngredient, 1));
		}
	}

	public void removeBaseIngredient(String name) {
		Ingredient ingredient = findBaseIngredient(name);

		if(ingredient != null) {
			baseIngredients.remove(ingredient);
		}
	}

	public void addExtraIngredient(String name, int defaultNumber) {
		Ingredient invIngredient = INVENTORY.getIngredient(name);		// 李쎄퀬 �옱猷�
		if(invIngredient == null) return;	// 李쎄퀬�뿉 �뾾�뒗 �옱猷�

		if(findExtraIngredient(name) == null) {
			extraIngredients.add(new Ingredient(invIngredient, defaultNumber));
		}
	}

	public void removeExtraIngredient(String name) {
		Ingredient ingredient = findExtraIngredient(name);

		if(ingredient != null) {
			extraIngredients.remove(ingredient);
		}
	}

	// Getter / Setter
	public static int getBasePrice() {
		return basePrice;
	}

	public static void setBasePrice(int basePrice) {
		Menu.basePrice = basePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Ingredient> getBaseIngredients() {
		return this.baseIngredients;
	}

	public List<Ingredient> getExtraIngredients() {
		return this.extraIngredients;
	}

	public void setCalcPrice() {		// �썝�옱猷� 媛믪쓣 湲곕컲�쑝濡� 媛�寃� �옄�룞 怨꾩궛
		this.price = basePrice;

		for(Ingredient ingredient : this.baseIngredients) {
			this.price += ingredient.getCost() * ingredient.getAmount();
		}

		for(Ingredient ingredient : this.extraIngredients) {
			this.price += ingredient.getCost() * ingredient.getAmount();
		}
	}
	
	public static int getSizePrice()
	{
		return (sizePrice);
	}
}
