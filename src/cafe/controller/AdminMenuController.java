package cafe.controller;

import cafe.SceneChanger;
import cafe.controller.ui.MenuControl;
import cafe.model.Menu;
import cafe.model.MenuBoard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle ;

public class AdminMenuController implements Initializable {

    @FXML private Button btnCan, btnSave;
    @FXML private Button btnOpt;
    @FXML private TilePane menuPane;
    @FXML private VBox ingrePane;
    @FXML private TextField editMenuPrice;
    @FXML private TextField editBasicPrice;
    @FXML private RadioButton radioBasic;
    @FXML private RadioButton radioCustom;

    List<Menu> menuList = MenuBoard.getInstance().getMenuList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final ToggleGroup group = new ToggleGroup();
        radioBasic.setToggleGroup(group);
        radioCustom.setToggleGroup(group);
        radioBasic.setSelected(true);

        editBasicPrice.setText(String.valueOf(Menu.getBasePrice()));

        listingMenu(menuList, false);

        btnCan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCan(event);
            }
        });
        btnSave.setOnAction(event -> handleSave(event));
        radioBasic.setOnAction(event -> handleBasic(event));
        radioCustom.setOnAction(event -> handleCustom(event));

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
    }

    private void listingMenu(List<Menu> menuList, boolean isCustom) {     //메뉴를 나열해주는 함수
        menuPane.getChildren().clear();
        for (Menu menu : menuList) {
            if (isCustom == menu.isCustom())
                menuPane.getChildren().add(new MenuControl(menu));
        }
    }
}
