package model;

import java.util.ArrayList;
import java.util.List;

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
	public Menu getMenu(String name) {
		if(name == null) return null;

		for(Menu menu : menuList) {
			if(name.equals(menu.getName())) {
				return menu;
			}
		}

		return null;
	}

	// 메뉴 추가
	public void addMenu(Menu menu) {
		if(getMenu(menu.getName()) == null) {
			menuList.add(menu);
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

	private void insertTestData() {
		Menu m1 = new Menu("에스프레소");
		Menu m2 = new Menu("아메리카노");
		Menu m3 = new Menu("비엔나");

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

		addMenu(m1);
		addMenu(m2);
		addMenu(m3);
	}
}
