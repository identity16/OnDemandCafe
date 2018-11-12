package cafe.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static int basePrice = 1000;					// 기본 가격
	private static final Inventory INVENTORY = Inventory.getInstance();		// 창고

	private String name;							// 메뉴명
	private List<Ingredient> baseIngredients;		// 베이스 재료
	private List<Ingredient> extraIngredients;		// 추가 재료
	private int price;								// 메뉴 가격
	private boolean isCustom;						// 커스텀 메뉴 플래그
	private static int sizePrice = 1500;

	public Menu(String name) {
		this(name, true);
	}

	public Menu(String name, boolean isCustom) {
		this.name = name;
		this.baseIngredients = new ArrayList<>();
		this.extraIngredients = new ArrayList<>();
		this.price = Menu.basePrice;
		this.isCustom = isCustom;
	}

	// 재료명으로 재료 탐색
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

	// 재료 추가 / 제거
	public void addBaseIngredient(String name) {
		Ingredient invIngredient = INVENTORY.getIngredient(name);		// 창고 재료
		if(invIngredient == null) return;	// 창고에 없는 재료

		if(findBaseIngredient(name) == null) {
			Ingredient extra = findExtraIngredient(name);

			if(extra != null) {
				baseIngredients.add(extra);
			} else {
				baseIngredients.add(new Ingredient(invIngredient, 1));
			}
		}
	}

	public void removeBaseIngredient(String name) {
		Ingredient ingredient = findBaseIngredient(name);

		if(ingredient != null) {
			baseIngredients.remove(ingredient);
		}
	}

	public void addExtraIngredient(String name) {
		Ingredient invIngredient = INVENTORY.getIngredient(name);		// 창고 재료
		if(invIngredient == null) return;	// 창고에 없는 재료

		if(findExtraIngredient(name) == null) {
			Ingredient base = findBaseIngredient(name);

			if(base != null) {
				extraIngredients.add(base);
			} else {
				extraIngredients.add(new Ingredient(invIngredient, 0));
			}
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

	public int getCalcPrice() {						// 원재료 값을 기반으로 가격 자동 계산
		int price = basePrice;

		for(Ingredient ingredient : this.baseIngredients) {
			price += ingredient.getCost() * ingredient.getAmount();
		}

		for(Ingredient ingredient : this.extraIngredients) {
			price += ingredient.getCost() * ingredient.getAmount();
		}

		return price;
	}

	public static int getSizePrice()
	{
		return sizePrice;
	}

	public boolean isCustom() {
		return isCustom;
	}
}