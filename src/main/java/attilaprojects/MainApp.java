package attilaprojects;

import attilaprojects.classes.CourseList;
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
        CourseList.getInstance().loadCourseList();

        SceneBuilder sceneBuilder = new SceneBuilder(primaryStage);
        primaryStage.setScene(sceneBuilder.loginScene());
        primaryStage.setTitle("Neptun Subject Application");
        primaryStage.show();

        primaryStage.setOnCloseRequest(WindowEvent -> {
            StudentList.getInstance().saveStudentList();
            TeacherList.getInstance().saveTeacherList();
            CourseList.getInstance().saveCourseList();
        });
    }
    public static void main( String[] args )
    {
        StudentList studentList = new StudentList();
        TeacherList teacherList = new TeacherList();
        CourseList courseList = new CourseList();
        launch(args);
    }


}
