package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.IngredientControlFactory;
import cafe.model.Beverage;
import cafe.model.Ingredient;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ExtraIngredientController implements Initializable {
	@FXML private VBox root;
	@FXML private ToggleGroup isHot;
	@FXML private TextField menuNameField;
	@FXML private Label priceLabel;
	@FXML private Button prevBtn;
	@FXML private Button nextBtn;
	@FXML private CheckBox isExtraCheck;

	private ObservableList<Ingredient> extraIngredientList;
	private Beverage beverage;
	private Menu initMenu;

	public ExtraIngredientController() {
		extraIngredientList = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 값 초기화
		Platform.runLater(() -> {
			initMenu = (Menu) root.getUserData();

			if(!initMenu.isDummy()) {
				menuNameField.setText(initMenu.getName());
				extraIngredientList.addAll(initMenu.getExtraIngredients());
				priceLabel.setText(initMenu.getPrice() + "원");
			}
		});

		menuNameField.setDisable(true);

		// Event Handling

		isExtraCheck.selectedProperty().addListener(
				(observable, oldValue, newValue) -> priceLabel.setText(calcFinalPrice(extraIngredientList) + "원"));

		// TODO: Extra Ingredient 수량 변경 시 이벤트 처리


		prevBtn.setOnAction(event -> SceneChanger.getInstance().back());

		nextBtn.setOnAction(event -> {
			beverage = new Beverage(initMenu);
			beverage.setExtra(isExtraCheck.isSelected());
			beverage.setHot(isHot.getSelectedToggle().getUserData().equals("true"));

			SceneChanger.getInstance().back();
			SceneChanger.getInstance().back();
			SceneChanger.getInstance().back();
			SceneChanger.getInstance().next(SceneChanger.Location.MENU, beverage);
		});
	}

	// 사이즈 포함 가격 (extra 고려)
	private int calcFinalPrice(List<Ingredient> extraIngredients) {
		int price = calcPrice(extraIngredients);

		price += isExtraCheck.isSelected() ? Menu.getSizePrice() : 0;

		return price;
	}

	// 사이즈 제외 음료 가격 (extra 포함)
	private int calcPrice(List<Ingredient> extraIngredients) {
		Menu origMenu = MenuBoard.getInstance().getMenu(initMenu.getName());
		List<Ingredient> origList = origMenu.getExtraIngredients();

		int price = origMenu.getPrice();

		// 재료 이름으로 정렬
		origList.sort(Comparator.comparing(Ingredient::getName));
		extraIngredients.sort(Comparator.comparing(Ingredient::getName));

		for(int i=0; i<extraIngredients.size(); i++) {
			int amountDiff = extraIngredients.get(i).getAmount() - origList.get(i).getAmount();

			price += origList.get(i).getCost() * amountDiff;
		}

		return price;
	}
}
