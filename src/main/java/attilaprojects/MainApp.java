package attilaprojects;

import attilaprojects.graphics.SceneBuilder;
import attilaprojects.student.StudentList;
import attilaprojects.teacher.TeacherList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.getIcons().add(new Image("file:src/main/resources/NSA.png"));

        StudentList.getInstance().loadStudentList();
        TeacherList.getInstance().loadTeacherList();

        SceneBuilder sceneBuilder = new SceneBuilder(primaryStage);
        primaryStage.setScene(sceneBuilder.loginScene());
        primaryStage.setTitle("Neptun Subject Application");
        primaryStage.show();

        primaryStage.setOnCloseRequest(WindowEvent -> {
            StudentList.getInstance().saveStudentList();
            TeacherList.getInstance().saveTeacherList();
        });
    }
    public static void main( String[] args )
    {
        StudentList studentList = new StudentList();
        TeacherList teacherList = new TeacherList();
        launch(args);
    }


}
