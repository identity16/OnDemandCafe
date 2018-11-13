package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.OrderControlFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cafe.controller.ui.MenuControl;
import cafe.model.Beverage;
import cafe.model.Menu;
import cafe.model.MenuBoard;

public class MenuController implements Initializable {
	@FXML TilePane menuPane;
	@FXML ScrollPane scrollPane;
	@FXML ListView<Beverage> orderListView;
	@FXML Label totalPriceLabel;
	@FXML Button btnComplete;
	@FXML Button btnCancel;

	private ObservableList<Beverage> orderedBeverages;

	public MenuController() {
		orderedBeverages = FXCollections.observableArrayList();
		insertTestData();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scrollPane.setStyle("-fx-background-color:transparent;");	// ScrollPane border 투명화

		List<Menu> menuList = MenuBoard.getInstance().getMenuList();

		for(Menu menu : menuList) {
			menuPane.getChildren().add(new MenuControl(menu));
		}

		totalPriceLabel.setText(String.valueOf(calcTotalPrice()));

		orderListView.setItems(orderedBeverages);
		orderListView.setPlaceholder(new Label("주문된 음료가 없습니다."));
		orderListView.setCellFactory(new OrderControlFactory());

		// Add Event Listeners
		for(Beverage beverage : orderedBeverages) {
			// 수량이 바뀌면 가격 합 갱신
			beverage.amountProperty().addListener((observable, oldValue, newValue) -> totalPriceLabel.setText(String.valueOf(calcTotalPrice())));
		}

		orderedBeverages.addListener((ListChangeListener<? super Beverage>) c -> {	// 리스트에 변화가 생기면 가격 합 갱신
			totalPriceLabel.setText(String.valueOf(calcTotalPrice()));
		});

		btnComplete.setOnAction(event -> {
			// TODO: 주문 완료 화면 넘기기
		});
		btnCancel.setOnAction(event -> SceneChanger.getInstance().back());
	}

	// 총 음료 가격 합계 계산
	private int calcTotalPrice() {
		int totalPrice = 0;

		for(Beverage b : orderedBeverages) {
			totalPrice += b.getPrice() * b.getAmount();
		}

		return totalPrice;
	}

	// 테스트 데이터 삽입
	private void insertTestData() {
		MenuBoard menuBoard = MenuBoard.getInstance();

		Beverage b1 = new Beverage(menuBoard.getMenu("아메리카노"));

		Beverage b2 = new Beverage(menuBoard.getMenu("아메리카노"));
		b2.setIngredientAmount("샷", 3);
		b2.setName(b2.getName() + "(ICE)*");


		Beverage b3 = new Beverage(menuBoard.getMenu("에스프레소 콘파냐"));
		b3.setHot(true);
		b3.setExtra(true);
		b3.setName(b3.getName() + "(HOT, Extra)");

		Beverage b4 = new Beverage(menuBoard.getMenu("에스프레소"));
		b4.setName(b4.getName() + "(ICE)");

		orderedBeverages.add(b1);
		orderedBeverages.add(b2);
		orderedBeverages.add(b3);
		orderedBeverages.add(b4);
	}
}
