package cafe.controller;

import cafe.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle ;

public class AdminIngreController implements Initializable {

    @FXML private Button btnCan;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCan(event);
            }
        });
    }

    public void handleCan(ActionEvent event) {
        SceneChanger.getInstance().back();
    }
}