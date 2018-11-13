package cafe.model;

import java.util.*;

public class MenuBoard {
	private static MenuBoard instance;
	private List<Menu> menuList;

	// Singleton
	private MenuBoard () {
		menuList = new ArrayList<>();

		// File Input => Add Loaded Menu

		/*Test Data*/
		insertTestData();
	}

	public static MenuBoard getInstance() {
		if(instance == null) {
			instance = new MenuBoard();
		}

		return instance;
	}


	// 메뉴 검색
	public Menu getMenu(String name) {			// 이름으로
		if(name == null) return null;

		for(Menu menu : menuList) {
			if(name.equals(menu.getName())) {
				return menu;
			}
		}

		return null;
	}

	public Menu getMenu(List<Ingredient> baseIngredients) {		// 베이스 재료 목록으로
		if(baseIngredients == null) {
			return null;
		}

		List<Ingredient> tmpBase = new ArrayList<>(baseIngredients);

		tmpBase.sort(Comparator.comparing(Ingredient::getName));

		for(Menu menu : menuList) {
			List<Ingredient> menuBase = menu.getBaseIngredients();
			if(tmpBase.size() != menuBase.size()) continue;

			menuBase.sort(Comparator.comparing(Ingredient::getName));


			int i=0, len = tmpBase.size();
			for(; i<len; i++) {
				// 재료 이름이 같은지 비교
				if(!menuBase.get(i).getName().equals(
						tmpBase.get(i).getName())) break;
			}

			// 재료 이름이 전부 같으면 메뉴 리턴
			if(i == len) {
				return menu;
			}
		}

		return null;
	}

	// 메뉴 추가
	public void addMenu(Menu menu) {
		addMenu(menu, menu.getCalcPrice());
	}

	public void addMenu(Menu menu, int price) {
		if(getMenu(menu.getName()) == null) {
			menuList.add(menu);
			menu.setPrice(price);
		}
	}

	// 메뉴 삭제
	public void removeMenu(String name) {
		Menu menu = getMenu(name);
		if(menu != null) {
			menuList.remove(menu);
		}
	}

	// Menu List Getter
	public List<Menu> getMenuList() {
		return menuList;
	}

	// 테스트 함수
	private void insertTestData() {
		Menu m1 = new Menu("에스프레소", false);
		Menu m2 = new Menu("아메리카노", false);
		Menu m3 = new Menu("비엔나", false);
		Menu m4 = new Menu("에스프레소 콘파냐", false);
		Menu m5 = new Menu("비둘기야~ 비둘기! 비둘기! 비둘기! 비둘기! 비둘기! 비둘기! 비둘기! 비둘기!");
		Menu m6 = new Menu("커스텀 메뉴1");
		Menu m7 = new Menu("커스텀 메뉴2");
		Menu m8 = new Menu("커스텀 메뉴3");
		Menu m9 = new Menu("기본 메뉴1", false);
		Menu m10 = new Menu("기본 메뉴2", false);
		Menu m11 = new Menu("기본 메뉴3", false);

		m1.addBaseIngredient("샷");

		m2.addBaseIngredient("샷");
		m2.addBaseIngredient("물");
		m2.addExtraIngredient("샷");
		m2.addBaseIngredient("휘핑크림");
		m2.removeBaseIngredient("휘핑크림");

		m3.addBaseIngredient("샷");
		m3.addBaseIngredient("물");
		m3.addBaseIngredient("휘핑크림");
		m3.addExtraIngredient("샷");

		m4.addBaseIngredient("샷");
		m4.addBaseIngredient("휘핑크림");

		m5.addBaseIngredient("샷");
		m5.addBaseIngredient("물");
		m5.addBaseIngredient("데운우유");

		m6.addBaseIngredient("샷");
		m6.addBaseIngredient("데운우유");

		m7.addBaseIngredient("휘핑크림");
		m7.addExtraIngredient("휘핑크림");

		m8.addBaseIngredient("물");

		m9.addBaseIngredient("데운우유");

		m10.addBaseIngredient("물");
		m10.addBaseIngredient("데운우유");

		m11.addBaseIngredient("휘핑크림");
		m11.addBaseIngredient("물");


		addMenu(m1, 1300);
		addMenu(m2);
		addMenu(m3);
		addMenu(m4);
		addMenu(m5);
		addMenu(m6);
		addMenu(m7);
//		addMenu(m8, 9000);
		addMenu(m9);
		addMenu(m10);
		addMenu(m11);
	}
}
