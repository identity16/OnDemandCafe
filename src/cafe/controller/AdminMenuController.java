package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.IngredientControlFactory;
import cafe.controller.ui.MenuControl;
import cafe.model.Ingredient;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle ;

public class AdminMenuController implements Initializable {

    @FXML private ListView<Ingredient> ingreListView;
    @FXML private AnchorPane root;

    @FXML private Button btnCan, btnSave;
    @FXML private Button btnOpt;
    @FXML private TilePane menuPane;

    @FXML private TextField editMenuPrice;
    @FXML private TextField editBasicPrice;
    @FXML private TextField txtMenuField;
    @FXML private RadioButton radioBasic;
    @FXML private RadioButton radioCustom;

    private List<Menu> menuList = MenuBoard.getInstance().getMenuList();
    private ObservableList<Ingredient> baseIngredientList;

    private StringProperty menuNameProperty, menuPriceProperty;

    private Boolean isMenuExist = true;

    public AdminMenuController() {
        baseIngredientList = FXCollections.observableArrayList();

        menuNameProperty = new SimpleStringProperty(null);
        menuPriceProperty = new SimpleStringProperty(null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* 재료 리스트 관련 이벤트 */

        // 메뉴 이름,가격 텍스트필드 바인딩
        menuNameProperty.bindBidirectional(txtMenuField.textProperty());
        menuPriceProperty.bindBidirectional(editMenuPrice.textProperty());
        //        // Event Handling
        baseIngredientList.addListener((ListChangeListener<Ingredient>) c -> {			// 재료 변경 이벤트
            // 첫 번째 Null(추가 버튼) 뺀 subList

            if(baseIngredientList.size() == 0){}
            List<Ingredient> withoutNull = baseIngredientList.subList(1, baseIngredientList.size());

            Menu existingMenu = MenuBoard.getInstance().getMenu(withoutNull);
            isMenuExist = existingMenu != null;

            if(!isMenuExist) {
                editMenuPrice.clear();
                txtMenuField.clear();
            } else {
                menuNameProperty.setValue(existingMenu.getName());
                menuPriceProperty.setValue(""+existingMenu.getPrice());
            }
        });


        /* 메뉴 리스트 관련 초기화 */
        listingMenu(menuList, false);

        /* 버튼 이벤트 관련 초기화 */
        btnCan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCan(event);
            }
        });
        btnSave.setOnAction(event -> handleSave(event));
        radioBasic.setOnAction(event -> handleBasic(event));
        radioCustom.setOnAction(event -> handleCustom(event));

        /* 기타 옵션들 관련 초기화 */
        final ToggleGroup group = new ToggleGroup();
        radioBasic.setToggleGroup(group);
        radioCustom.setToggleGroup(group);
        radioBasic.setSelected(true);

        editBasicPrice.setText(String.valueOf(Menu.getBasePrice()));

    }

    private void handleCan(ActionEvent event) {
        SceneChanger.getInstance().back();
    }

    private void handleSave(ActionEvent event) {

        String basicPrice = editBasicPrice.getText();
        Menu.setBasePrice(Integer.parseInt(basicPrice));

    }
    private void handleBasic(ActionEvent event) {
        listingMenu(menuList, false);
    }
    private void handleCustom(ActionEvent event) {
        listingMenu(menuList, true);
    };

    private void listingMenu(List<Menu> menuList, boolean isCustom) {     //메뉴를 나열해주는 함수
        menuPane.getChildren().clear();
        for (Menu menu : menuList) {
            if (isCustom == menu.isCustom()) {

                MenuControl control = new MenuControl(menu);
                menuPane.getChildren().add(control);
                control.rectangle.setOnMouseClicked(event -> {
                    listingIngre(menu);
                });
            }
        }
    }
    private void listingIngre(Menu menu) {
        // 값 초기화
        Platform.runLater(() -> {
            if(!menu.isDummy()) {
                ingreListView.getItems().removeAll();
                menuNameProperty.setValue(menu.getName());
                baseIngredientList.addAll(menu.getBaseIngredients());
            }
        });

        ingreListView.setItems(baseIngredientList);
        ingreListView.setPlaceholder(new Label("재료가 없습니다."));
        ingreListView.setCellFactory(new IngredientControlFactory());

    }
    private int calcBasePrice(List<Ingredient> baseIngredients) {
        int price = Menu.getBasePrice();

        for(Ingredient ingredient : baseIngredients) {
            price += ingredient.getCost() * ingredient.getAmount();
        }

        return price;
    }
}
