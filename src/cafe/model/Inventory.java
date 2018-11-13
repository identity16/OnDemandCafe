package cafe.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private static Inventory instance;
	private List<Ingredient> ingredientList;

	// Singleton
	private Inventory () {
		ingredientList = new ArrayList<>();

		// File Input => Add Loaded Ingredients
		// TODO: 파일 입력으로 재료 목록 받아오기

		/*Test Data*/
		insertTestData();
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
		addIngredient(name, cost, initStockAmount, false);
	}

	public void addIngredient(String name, int cost, int initStockAmount, boolean isCoffee) {
		if(getIngredient(name) == null) {
			if(isCoffee) {
				ingredientList.add(new CoffeeBean(name, cost, initStockAmount));
			} else {
				ingredientList.add(new Ingredient(name, cost, initStockAmount));
			}
		}
	}

	// 재료 삭제
	public void removeIngredient(String name) {
		Ingredient ingredient = getIngredient(name);
		if(ingredient != null) {
			ingredientList.remove(ingredient);
		}
	}

	// Ingredient List Getter
	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	private void insertTestData() {
		addIngredient("샷", 500, 500, true);
		addIngredient("물", 0, 1000);
		addIngredient("휘핑크림", 500, 100);
		addIngredient("초코시럽", 700, 100);
		addIngredient("데운우유", 1000, 200);

		getIngredient("샷").setAmount(800);
	}
}
