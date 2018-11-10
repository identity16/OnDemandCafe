import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox root = FXMLLoader.load(getClass().getResource("/view/index.fxml"));
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
