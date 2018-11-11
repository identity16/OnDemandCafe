import java.io.IOException;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Main extends Application implements EventHandler<KeyEvent>{

	private Stage		primaryStage;
	private AnchorPane	TitleView;
	private AnchorPane	MenuList;
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
			initMenuList();
		}
	}
	
	public void initTitleView()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TitleView.fxml"));
			TitleView = (AnchorPane) loader.load();
			
			scene = new Scene(TitleView);
			scene.setOnKeyPressed(this);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initMenuList()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MenuList.fxml"));
			MenuList = (AnchorPane) loader.load();
			
			scene.setRoot(MenuList);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage()
	{
		return (primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}