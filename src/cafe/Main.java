package cafe;
import java.io.IOException;
import java.util.Stack;

import cafe.SceneChange.Location;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application {

	private Stage	primaryStage;
	public static Main instance;

	public Main() {
		instance = this;
	}

	@Override
	public void	start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("On Demand Cafe");
		primaryStage.setResizable(false);

		SceneChange.getInstance().nextSceneChange(Location.TITLE);
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	
	// Run Program
	public static void main(String[] args) {
		launch(args);
	}
}
