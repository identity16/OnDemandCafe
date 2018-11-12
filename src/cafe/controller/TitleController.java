package cafe.controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import cafe.Main;
import cafe.SceneChange;
import cafe.SceneChange.Location;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class TitleController implements Initializable {
	
	@FXML private Label			pressSpaceLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), pressSpaceLabel);
		fadeTransition.setFromValue(0.0);
		fadeTransition.setToValue(1.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();
		
		/*.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.SPACE)
			{
				SceneChange.getInstance().nextSceneChange(Location.MENU);
			}
		});*/
	}
}