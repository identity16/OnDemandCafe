package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.IngredientControlFactory;
import cafe.model.Ingredient;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseIngredientController implements Initializable {
	@FXML VBox root;
	@FXML ListView<Ingredient> ingredientListView;
	@FXML TextField menuNameField;
	@FXML Button nextBtn;
	@FXML Button prevBtn;

	private ObservableList<Ingredient> ingredientList;
	private StringProperty menuNameProperty;

	public BaseIngredientController() {
		ingredientList = FXCollections.observableArrayList();
		menuNameProperty = new SimpleStringProperty(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 선택된 메뉴
		Menu initMenu = (Menu) root.getUserData();

		// 값 초기화
		menuNameProperty.setValue(initMenu.getName());
		ingredientList.addAll(initMenu.getBaseIngredients());

		// 메뉴 이름, 텍스트필드 바인딩
		menuNameProperty.bindBidirectional(menuNameField.textProperty());

		// Event Handling
		ingredientList.addListener((ListChangeListener<Ingredient>) c -> {
			Menu temp = MenuBoard.getInstance().getMenu(ingredientList);

			if(temp == null) {
				menuNameProperty.setValue("");
			} else {
				menuNameProperty.setValue(temp.getName());
			}
		});

		menuNameProperty.addListener((observable, oldValue, newValue) -> {
			if("".equals(newValue)) {
				menuNameField.setDisable(false);
			} else {
				menuNameField.setDisable(true);
			}
		});

		menuNameField.textProperty().addListener((observable, oldValue, newValue) -> {
			if("".equals(newValue)) {
				// 메뉴명 빈 칸
				nextBtn.setDisable(true);

				// TODO: 빈칸이라는 표시(Dialog)
			} else if(MenuBoard.getInstance().getMenu(newValue) != null) {
				// 이미 있는 메뉴명
				nextBtn.setDisable(true);

				if(!menuNameField.isDisable()) {
					// TODO: 이미 있는 메뉴명이라는 표시(Dialog)
				}
			} else {
				nextBtn.setDisable(false);
			}
		});

		prevBtn.setOnAction(event -> SceneChanger.getInstance().back());

		nextBtn.setOnAction(event -> {
			String name = menuNameProperty.getValue();
			Menu result;
			Menu existingMenu = MenuBoard.getInstance().getMenu(name);


			if(existingMenu == null) {	// 새로운 메뉴이면
				result = new Menu(name);

				result.getBaseIngredients().addAll(ingredientList);
				// 새로운 메뉴는 샷 추가만 가능
				result.addExtraIngredient("샷");
			} else {
				result = new Menu(existingMenu);
			}

			System.out.println(result);

			// TODO: 다음 화면으로 전환
		});

		ingredientListView.setItems(ingredientList);
		ingredientListView.setPlaceholder(new Label("재료가 없습니다."));
		ingredientListView.setCellFactory(new IngredientControlFactory());
	}
}
