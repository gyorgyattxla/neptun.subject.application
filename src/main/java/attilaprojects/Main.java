package attilaprojects;

import attilaprojects.graphics.SceneBuilder;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage){
        primaryStage.getIcons().add(new Image("file:src/main/resources/NSA.png"));

        SceneBuilder sceneBuilder = new SceneBuilder();
        primaryStage.setScene(sceneBuilder.loginScene());
        primaryStage.setTitle("Neptun Subject Application");
        primaryStage.show();
    }
    public static void main( String[] args )
    {
        launch(args);
    }


}
