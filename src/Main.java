import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Main extends Application implements EventHandler<KeyEvent>{

	private Stage	primaryStage;
	private Pane 	titleRoot;
	private Pane	menuRoot;
	private static Scene scene;

	@Override
	public void	start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("On Demand Cafe");

		initTitleView();
	}

	@Override
	public void handle(KeyEvent event)
	{
		if (event.getCode() == KeyCode.SPACE)
		{
			initMenuView();
		}
	}

	public void initTitleView()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/title.fxml"));
			titleRoot = loader.load();

			scene = new Scene(titleRoot);
			scene.setOnKeyPressed(this);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initMenuView()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/menu.fxml"));
			menuRoot = loader.load();

			scene.setRoot(menuRoot);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
