package cafe.controller.ui;

import cafe.model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class IngredientControl extends ListCell<Ingredient> {
	@FXML Label nameLabel;
	@FXML Button deleteBtn;

	IngredientControl() {
		loadFXML();
	}

	private void loadFXML() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_ingredient.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

			// Event Handling
			deleteBtn.setOnAction(event -> {
				// 리스트에서 Ingredient 제거
				getListView().getItems().remove(getItem());
			});
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void updateItem(Ingredient item, boolean empty) {
		super.updateItem(item, empty);

		if(empty) {
			setText("");
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		} else {
			nameLabel.setText(item.getName());
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		}
	}
}
