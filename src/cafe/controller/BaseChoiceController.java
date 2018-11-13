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

public class BaseChoiceController implements Initializable{
	@FXML TilePane ingredientPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Ingredient> ingredientList = Inventory.getInstance().getIngredientList();
		
		for(Ingredient ingredient : ingredientList) {
			ingredientPane.getChildren().add(new BaseChoiceControl(ingredient));
		}
	}
}
