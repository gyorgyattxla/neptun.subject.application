package attilaprojects;

import attilaprojects.graphics.SceneBuilder;
import attilaprojects.student.StudentList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Hello world!
 *
 */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.getIcons().add(new Image("file:src/main/resources/NSA.png"));

        StudentList.getInstance().loadStudentList();

        SceneBuilder sceneBuilder = new SceneBuilder(primaryStage);
        primaryStage.setScene(sceneBuilder.loginScene());
        primaryStage.setTitle("Neptun Subject Application");
        primaryStage.show();

        primaryStage.setOnCloseRequest(WindowEvent -> {
            StudentList.getInstance().saveStudentList();
        });
    }
    public static void main( String[] args )
    {
        StudentList studentList = new StudentList();
        launch(args);
    }


}
