package cafe.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cafe.Main;
import cafe.controller.ui.BaseChoiceControl;
import cafe.model.Ingredient;
import cafe.model.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddBaseDialogController implements Initializable {
	@FXML TilePane	ingredientPane;
	@FXML Button	cancelBtn;
	@FXML Button	addBtn;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addBtn.setOnAction(event -> {
						
		for (Node node : ingredientPane.getChildren())
		{
			BaseChoiceControl baseChoice = (BaseChoiceControl) node;
			
			if (baseChoice.getIsClicked() == true)
			{
			}
		}	
			addBtn.getScene().getWindow().hide();
		});
		
		cancelBtn.setOnAction(event -> {
			cancelBtn.getScene().getWindow().hide();
		});
	}
	
	public void initDialog(List<Ingredient> ingredients) {
		List<Ingredient> ingredientList = Inventory.getInstance().getIngredientList();
		//To do 다시 새로 짜야될듯
		for(Ingredient ingredient : ingredientList) {
			for (Ingredient compareIngredient : ingredients)
			{
				if (compareIngredient.isDummy() == false)				//파라미터로 들어온 재료 목록에 추가 목록 메뉴가 이미 있으면 안함
				{
					if (ingredient.getName().equals(compareIngredient.getName()))
						break;
				}
				else
				{
					BaseChoiceControl baseChoice = new BaseChoiceControl(ingredient);
					
					baseChoice.nameLabel.setText(ingredient.getName());
					baseChoice.costLabel.setText(String.valueOf(ingredient.getCost()));
	
					baseChoice.amountContainer.setVisible(false);
	
					baseChoice.nameLabel.setMouseTransparent(true);
					baseChoice.costLabel.setMouseTransparent(true);
		
					baseChoice.choiceCircle.setOnMouseEntered(event -> {
						if (baseChoice.getIsClicked() == false)
							baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
					});
					baseChoice.choiceCircle.setOnMouseExited(event -> {
						if (baseChoice.getIsClicked() == false)
							baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
					});
					baseChoice.choiceCircle.setOnMouseClicked(event -> {
						if (baseChoice.getIsClicked() == false)
						{
							baseChoice.choiceCircle.setFill(Color.rgb(243, 156, 18));
							baseChoice.setIsClicked(true);
						}
						else
						{
							baseChoice.choiceCircle.setFill(Color.rgb(221, 221, 221));
							baseChoice.setIsClicked(false);
						}
					});
		
					
					ingredientPane.getChildren().add(baseChoice);
				}
			}
		}
	}
}
