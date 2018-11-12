package cafe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cafe.controller.ui.MenuControl;
import cafe.model.Beverage;
import cafe.model.Menu;
import cafe.model.MenuBoard;

public class MenuController implements Initializable {
	@FXML TilePane menuPane;
	@FXML ScrollPane scrollPane;
	@FXML ListView orderListView;

	private ObservableList<Beverage> orderedBeverages;

	public MenuController() {
		orderedBeverages = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrollPane.setStyle("-fx-background-color:transparent;");	// ScrollPane border 투명화

		insertTestData();

		List<Menu> menuList = MenuBoard.getInstance().getMenuList();

		for(Menu menu : menuList) {
			menuPane.getChildren().add(new MenuControl(menu));
		}

	}

	// 테스트 함수
	private void insertTestData() {
		MenuBoard menuBoard = MenuBoard.getInstance();

		Beverage b1 = new Beverage(menuBoard.getMenu("아메리카노"));

		Beverage b2 = new Beverage(menuBoard.getMenu("아메리카노"));
		b2.setIngredientAmount("샷", 3);

		Beverage b3 = new Beverage(menuBoard.getMenu("에스프레소 콘파냐"));
		b3.setHot(true);
		b3.setExtra(true);

		orderedBeverages.add(b1);
		orderedBeverages.add(b2);
		orderedBeverages.add(b3);
	}
}
