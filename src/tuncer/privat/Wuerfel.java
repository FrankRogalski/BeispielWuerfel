package tuncer.privat;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Wuerfel extends Application {
	private Würfel würfel = new Würfel(100, 100, 100);
	private CameraFX3D cam;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		if (!Platform.isSupported(ConditionalFeature.SCENE3D)) {
			throw new RuntimeException("Dein System ist veraltet!");
		}
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 1000, 1000, true, SceneAntialiasing.BALANCED);
		
		Group group = new Group(this.würfel, new AmbientLight());
		
		SubScene subScene = new SubScene(group, scene.getWidth(), scene.getHeight(), true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK);
		
		root.getChildren().addAll(subScene);

		cam = new CameraFX3D(subScene, true);
		cam.setZoomSpeed(50);
		cam.setNearClip(10);
		cam.setFarClip(1000000);
		cam.setZ(-5000);
		subScene.setCamera(this.cam);
		
		primaryStage.setTitle("WürfelFX3D");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}