package cafe.controller.ui;

import cafe.Main;
import cafe.model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Popup;

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
			if(item.isDummy()) {
				nameLabel.setText("+");
				deleteBtn.setVisible(false);

				nameLabel.setOnMouseClicked(event -> {
					Popup addingredient = new Popup();
					try {
						addingredient.getContent().add(FXMLLoader.load(getClass().getResource("/cafe/view/base_choice.fxml")));
						addingredient.show(Main.instance.getPrimaryStage());
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("재료 추가!!");
				});
			} else {
				nameLabel.setText(item.getName());
				nameLabel.setOnMouseClicked(null);
				deleteBtn.setVisible(true);
			}

			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		}
	}
}
