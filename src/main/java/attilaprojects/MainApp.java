package attilaprojects;

import attilaprojects.course.CourseList;
import attilaprojects.course.courseenrollment.EnrollmentList;
import attilaprojects.graphics.SceneBuilder;
import attilaprojects.student.StudentList;
import attilaprojects.teacher.TeacherList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.getIcons().add(new Image("file:src/main/resources/NSA.png"));

        StudentList.getInstance().loadStudentList();
        TeacherList.getInstance().loadTeacherList();
        CourseList.getInstance().loadCourseList();
        EnrollmentList.getInstance().loadStudentList();

        SceneBuilder sceneBuilder = new SceneBuilder(primaryStage);
        primaryStage.setScene(sceneBuilder.loginScene());
        primaryStage.setTitle("Neptun Subject Application");
        primaryStage.show();

        primaryStage.setOnCloseRequest(WindowEvent -> {
            StudentList.getInstance().saveStudentList();
            TeacherList.getInstance().saveTeacherList();
            CourseList.getInstance().saveCourseList();
            EnrollmentList.getInstance().saveEnrollmentList();
        });
    }
    public static void main( String[] args )
    {
        launch(args);
    }


}
