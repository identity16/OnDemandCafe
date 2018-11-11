package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static int basePrice;					// 기본 가격

	private String name;							// 메뉴명
	private List<Ingredient> baseIngredients;		// 베이스 재료
	private List<Ingredient> extraIngredients;		// 추가 재료
	private int price;								// 메뉴 가격

	public Menu(String name) {
		this.name = name;
		this.baseIngredients = new ArrayList<>();
		this.extraIngredients = new ArrayList<>();
		this.price = Menu.basePrice;
	}

	// 재료 추가 / 제거
	public void addBaseIngredient(Ingredient ingredient) {
		baseIngredients.add(ingredient);
	}

	public void removeBaseIngredient(Ingredient ingredient) {
		baseIngredients.remove(ingredient);
	}

	public void addExtraIngredient(Ingredient ingredient) {
		extraIngredients.add(ingredient);
	}

	public void removeExtraIngredient(Ingredient ingredient) {
		extraIngredients.remove(ingredient);
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
}
