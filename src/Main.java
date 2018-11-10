import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();

		Group root = new Group();
		Scene scene = new Scene(root);

		// Set Stage Properties
		primaryStage.setTitle("On Demand Cafe");		// Title
		primaryStage.setScene(scene);					// Scene

		primaryStage.show();
	}

	// Run Program
	public static void main(String[] args) {
		launch(args);
	}
}
