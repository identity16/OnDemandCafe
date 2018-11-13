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
	
	private int			clickedCount = 0;
	private Ingredient	ingredient;
	
	public BaseChoiceControl() {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_base_choice.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int getClickedCount() {
		return clickedCount;
	}

	public void setClickedCount(int clickedCount) {
		this.clickedCount = clickedCount;
	}
}
