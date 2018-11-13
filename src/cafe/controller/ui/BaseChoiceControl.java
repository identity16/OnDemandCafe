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
	
	@FXML private Label		nameLabel;
	@FXML private Label		costLabel;
	@FXML private Circle	choiceCircle;
	@FXML private HBox		amountContainer;
	
	private int			clickedCount = 0;
	private Ingredient	ingredient;
	
	public BaseChoiceControl(Ingredient ingredient) {
		this.ingredient = ingredient;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cafe/view/ui/control_basechoice.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
			
			nameLabel.setText(ingredient.getName());
			costLabel.setText(String.valueOf(ingredient.getCost()));
			
			amountContainer.setVisible(false);
			
			nameLabel.setMouseTransparent(true);
			costLabel.setMouseTransparent(true);
			
			choiceCircle.setOnMouseEntered(event -> {
				if (clickedCount % 2 == 0)
					choiceCircle.setFill(Color.rgb(243, 156, 18));
				else
					choiceCircle.setFill(Color.rgb(221, 221, 221));
			});
			choiceCircle.setOnMouseExited(event -> {
				if (clickedCount % 2 == 0)
					choiceCircle.setFill(Color.rgb(221, 221, 221));
				else
					choiceCircle.setFill(Color.rgb(243, 156, 18));
			});
			choiceCircle.setOnMouseClicked(event -> {
				if (clickedCount % 2 == 0)
					choiceCircle.setFill(Color.rgb(243, 156, 18));
				else
					choiceCircle.setFill(Color.rgb(221, 221, 221));
				clickedCount++;
			});
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
