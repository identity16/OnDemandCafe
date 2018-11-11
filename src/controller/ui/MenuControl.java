package controller.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Menu;

import java.io.IOException;

public class MenuControl extends StackPane {
	@FXML private Label name;
	@FXML private StackPane customLabel;
	@FXML private Rectangle rectangle;

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

			// 특정 컨트롤 Mouse Event 무효화
			customLabel.setMouseTransparent(true);
			name.setMouseTransparent(true);

			// Mouse Event Handling
			rectangle.setOnMouseEntered(event -> {
				rectangle.setFill(Color.rgb(243, 156, 18));
				name.setText(String.valueOf(menu.getPrice()));
			});

			rectangle.setOnMouseExited(event -> {
				rectangle.setFill(Color.rgb(0xE0, 0xE0, 0xE0));
				name.setText(menu.getName());
			});

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML public void handleMouseEntered(MouseEvent e) {

	}

	@FXML public void handleMouseExited(MouseEvent e) {

	}

	// Menu Getter
	public Menu getMenu() {
		return menu;
	}
}
