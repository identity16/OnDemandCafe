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

import java.net.URL;
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

		for(int i=0; i<29; i++)
			menuPane.getChildren().add(new MenuControl());

	}
}
