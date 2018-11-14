package cafe.controller.ui;

import java.io.IOException;

import cafe.model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BaseChoiceControl extends VBox{
	
	@FXML public Label		nameLabel;
	@FXML public Label		costLabel;
	@FXML public Circle	choiceCircle;
	@FXML public HBox		amountContainer;
	
	private boolean		isClicked = false;
	private Ingredient	ingredient;
	
	public BaseChoiceControl(Ingredient ingredient) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_base_choice.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.ingredient = ingredient;
		
		try {
			fxmlLoader.load();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getIsClicked() {
		return isClicked;
	}

	public void setIsClicked(boolean clickedCount) {
		this.isClicked = clickedCount;
	}
	
	public Ingredient getIngredient()
	{
		return ingredient;
	}
}
