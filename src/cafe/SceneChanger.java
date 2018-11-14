package cafe;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneChanger {
	
	private Stage			primaryStage;
	private Stack<Scene>	sceneStack;
	
	public enum Location {
		TITLE("/cafe/view/title.fxml"),
		MENU("/cafe/view/menu.fxml"),
		BASE("/cafe/view/base_ingredients.fxml"),
		EXTRA("/cafe/view/extra_ingredients.fxml"),
		COFFE_BEAN("/cafe/view/coffee_bean.fxml"),
		RESULT("/cafe/view/order_result.fxml"),
		ADMIN("/cafe/view/adminTitle.fxml"),
		ADMENU("/cafe/view/adminMenu.fxml"),
		ADING("/cafe/view/adminIngre.fxml");
		
		final private String name;
		
		Location(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	private static SceneChanger instance;
	
	private SceneChanger() {
		instance = this;
		this.primaryStage = Main.instance.getPrimaryStage();
		sceneStack = new Stack<>();
	}
	
	public static SceneChanger getInstance() {
		if(instance == null) {
			instance = new SceneChanger();
		}

		return instance;
	}
	
	public void next(Location dest) {
		next(dest, null);
	}

	public void next(Location dest, Object obj) {
		try {
			// FXML 로드
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(dest.getName()));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);

			pane.setUserData(obj);

			if (dest == Location.TITLE)		// 타이틀 화면일 때,
			{
				// 키보드 이벤트 설정
				scene.setOnKeyPressed(event -> {
					if (event.getCode() == KeyCode.SPACE)
					{
						this.next(Location.MENU);
					}
					else if (event.getCode() == KeyCode.ENTER)
						this.next(Location.ADMIN);
				});
			}

			sceneStack.add(scene);
			primaryStage.setScene(sceneStack.peek());
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 뒤로 가기
	public void back()
	{
		if(!sceneStack.empty()) {
			sceneStack.pop();
			primaryStage.setScene(sceneStack.peek());
			primaryStage.show();
		}
	}

	public void backToTitle() {
		while(sceneStack.size() > 1) {
			back();
		}
	}
}
