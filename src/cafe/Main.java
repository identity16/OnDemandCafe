package cafe;
import java.io.IOException;
import java.util.Stack;

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
	private Pane	baseSelectMenu;
	private Scene scene;
	private Stack<Scene> scenestack;
	public static Main instance;

	public Main() {
		instance = this;
	}

	@Override
	public void	start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("On Demand Cafe");

		scenestack = new Stack<Scene>();

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
			loader.setLocation(Main.class.getResource("/cafe/view/title.fxml"));
			titleRoot = loader.load();

			scene = new Scene(titleRoot);
			scene.setOnKeyPressed(this);
			scenestack.add(scene);
			primaryStage.setScene(scenestack.peek());
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
			loader.setLocation(getClass().getResource("/cafe/view/menu.fxml"));
			menuRoot = loader.load();

			scene.setRoot(menuRoot);

			scenestack.add(scene);
			primaryStage.setScene(scenestack.peek());
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initBaseSelectMenu()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/cafe/view/menu_baseingredient.fxml"));
			baseSelectMenu = loader.load();

			scene.setRoot(baseSelectMenu);

			scenestack.add(scene);
			primaryStage.setScene(scenestack.peek());
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public Stack<Scene> getSceneStack()
	{
		return scenestack;
	}

	// Run Program
	public static void main(String[] args) {
		launch(args);
	}
}
