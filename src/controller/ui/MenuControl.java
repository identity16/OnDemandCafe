package controller.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuControl extends VBox {
	@FXML private TextField textField;

	public MenuControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ui/control_menu.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
