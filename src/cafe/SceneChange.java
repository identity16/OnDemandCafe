package cafe;

import java.io.IOException;
import java.util.Stack;

import cafe.model.Inventory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneChange {
	
	private Stage			primaryStage;
	private Scene			t_scene;
	private Pane			t_pane;
	private Stack<Scene>	scenestack;
	
	
	public static enum Location {
		TITLE("/cafe/view/title.fxml"),
		MENU("/cafe/view/menu.fxml"),
		BASE("/cafe/view/base_ingredients.fxml");
		
		final private String name;
		
		private Location(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	private static SceneChange instance;
	
	private SceneChange() {
		instance = this;
		this.primaryStage = Main.instance.getPrimaryStage();
		scenestack = new Stack<Scene>();
	}
	
	public static SceneChange getInstance() {
		if(instance == null) {
			instance = new SceneChange();
		}

		return instance;
	}
	
	public void nextSceneChange(Location destScene) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(destScene.getName()));
			t_pane = loader.load();

			t_scene = new Scene(t_pane);
			if (destScene == Location.TITLE)
			{
				t_scene.setOnKeyPressed(event -> {
					if (event.getCode() == KeyCode.SPACE)
					{
						SceneChange.getInstance().nextSceneChange(Location.MENU);
					}
				});
			}
			scenestack.add(t_scene);
			primaryStage.setScene(scenestack.peek());
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void prefSceneChange()
	{
		scenestack.pop();
		primaryStage.setScene(scenestack.peek());
		primaryStage.show();
	}
}
