package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import model.Beverage;
import controller.ui.MenuControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import model.Menu;
import model.MenuBoard;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

		List<Menu> menuList = MenuBoard.getInstance().getMenuList();

		for(Menu menu : menuList) {
			menuPane.getChildren().add(new MenuControl(menu));
		}
	}
}
