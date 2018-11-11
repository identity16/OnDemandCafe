package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private static Inventory instance;
	private List<Ingredient> ingredientList;

	// Singleton Pattern
	private Inventory () {
		ingredientList = new ArrayList<>();

		// File Input => Add Loaded Ingredients

	}

	public static Inventory getInstance() {
		if(instance == null) {
			instance = new Inventory();
		}

		return instance;
	}

	// 재료 검색
	public Ingredient getIngredient(String name) {
		if(name == null) return null;

		for(Ingredient ingredient : ingredientList) {
			if(name.equals(ingredient.getName())) {
				return ingredient;
			}
		}

		return null;
	}

	// 재료 추가
	public void addIngredient(String name, int cost, int initStockAmount) {
		if(getIngredient(name) == null) {
			ingredientList.add(new Ingredient(name, cost, initStockAmount));
		}
	}

	// 재료 추가
	public void removeIngredient(String name) {

	}
}
