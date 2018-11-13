package cafe.controller;

import cafe.model.Beverage;
import cafe.model.CoffeeBean;
import cafe.model.Menu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoffeeBeanController implements Initializable {
	@FXML private VBox root;
	@FXML private TextField menuNameField;
	@FXML private Label priceLabel;
	@FXML private Button prevBtn;
	@FXML private Button nextBtn;

	private Beverage beverage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 값 초기화
		Platform.runLater(() -> {
			beverage = (Beverage) root.getUserData();

			if(beverage != null) {
				menuNameField.setText(beverage.getName());
				priceLabel.setText(beverage.getPrice() + "원");
			}
		});

		menuNameField.setDisable(true);
		try {
			for (CoffeeBean.Origin origin : CoffeeBean.Origin.values()) {
				FXMLLoader loader = FXMLLoader.load(getClass().getResource("/cafe/view/ui/control_base_choice.fxml"));

				origin.getName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
