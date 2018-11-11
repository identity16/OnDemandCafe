package controller.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.Menu;

import java.io.IOException;

public class MenuControl extends StackPane {
	@FXML private Label name;
	private Menu menu;

	public MenuControl(Menu menu) {
		this.menu = menu;

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ui/control_menu.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();

			name.setText(menu.getName());
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public Menu getMenu() {
		return menu;
	}
}
