package controller.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.Menu;

import java.io.IOException;

public class MenuControl extends StackPane {
	@FXML private Label name;
	@FXML private StackPane customLabel;
	private Menu menu;

	public MenuControl(Menu menu) {
		this.menu = menu;

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ui/control_menu.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();

			// Control 요소 세팅
			name.setText(menu.getName());
			customLabel.setVisible(menu.isCustom());	// On Demand 라벨 표시 여부

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public Menu getMenu() {
		return menu;
	}
}
