package cafe.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cafe.controller.ui.BaseChoiceControl;
import cafe.model.Ingredient;
import cafe.model.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddBaseDialogController implements Initializable {
	@FXML TilePane ingredientPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Ingredient> ingredientList = Inventory.getInstance().getIngredientList();
		
		for(Ingredient ingredient : ingredientList) {
			BaseChoiceControl baseChoice = new BaseChoiceControl();

			baseChoice.nameLabel.setText(ingredient.getName());
			baseChoice.costLabel.setText(String.valueOf(ingredient.getCost()));

			baseChoice.amountContainer.setVisible(false);

			baseChoice.nameLabel.setMouseTransparent(true);
			baseChoice.costLabel.setMouseTransparent(true);

			baseChoice.choiceCircle.setOnMouseEntered(event -> {
				if (baseChoice.getClickedCount() % 2 == 0)
					baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
				else
					baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
			});
			baseChoice.choiceCircle.setOnMouseExited(event -> {
				baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
			});
			baseChoice.choiceCircle.setOnMouseClicked(event -> {
				if (baseChoice.getClickedCount() % 2 == 0)
					baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
				else
					baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
				baseChoice.setClickedCount(baseChoice.getClickedCount() + 1);
			});


			ingredientPane.getChildren().add(baseChoice);


		}
	}
}
