package cafe.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminTitleController implements Initializable {
	@FXML private Button btnMenu;
	@FXML private Button btnIngre;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				handleBtnMenuAction(event);
			}
		});
		btnIngre.setOnAction(event->handleBtnIngreAction(event));
	}
	public void handleBtnMenuAction(ActionEvent event) {
		System.out.println("Menu화면으로 이동");
	}
	public void handleBtnIngreAction(ActionEvent event) {
		System.out.println("Ingredient화면으로 이동");
	}
	
}
