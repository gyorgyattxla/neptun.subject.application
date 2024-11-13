package attilaprojects.graphics;


import attilaprojects.classes.Course;
import attilaprojects.logic.course.CourseHandler;
import attilaprojects.logic.login.LoginHandler;
import attilaprojects.logic.register.RegisterHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class SceneBuilder {

    private Stage primaryStage;
    private String displayedUsername;

    public SceneBuilder(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    RegisterHandler registerHandler = new RegisterHandler();
    LoginHandler loginHandler = new LoginHandler();
    CourseHandler courseHandler = new CourseHandler();

    String highlight =  "-fx-border-color: green;" +
            "-fx-border-width: 2px;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent";

    String buttonCSS =  "-fx-border-width: 2px;" +
            "-fx-background-radius: 5px;" +
            "-fx-background-color: #D3D3D3";

    String errorMassageCSS = "-fx-text-fill: red";

    public Scene loginScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Neptun Subject Application");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        Label errorMassage = new Label();
        errorMassage.setStyle(errorMassageCSS);

        TextField usernameField = new TextField();
        usernameField.setMaxWidth(175);
        usernameField.setPromptText("Username... ");
        usernameField.setStyle(highlight);

        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(175);
        passwordField.setPromptText("Password... ");
        passwordField.setStyle(highlight);

        Button studentLoginButton = new Button("Log in as Student");
        studentLoginButton.setMinSize(175,25);
        studentLoginButton.setMaxSize(175,25);
        studentLoginButton.setStyle(buttonCSS);
        studentLoginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean successfulLogin = loginHandler.loginAsStudent(username,password);
            if(successfulLogin){
                displayedUsername = usernameField.getText();
                primaryStage.setScene(studentHomeScene());
            }
            else errorMassage.setText("Incorrect username or password.");

        });

        Button teacherLoginButton = new Button("Log in as Teacher");
        teacherLoginButton.setMinSize(175,25);
        teacherLoginButton.setMaxSize(175,25);
        teacherLoginButton.setStyle(buttonCSS);
        teacherLoginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean successfulLogin = loginHandler.loginAsTeacher(username,password);
            if(successfulLogin){
                displayedUsername = usernameField.getText();
                primaryStage.setScene(teacherScene());
            }
            else errorMassage.setText("Incorrect username or password.");

        });

        Label registerPrompt = new Label("Don't have an account?");
        registerPrompt.setPadding(new Insets(0,0,-10,0));

        Button registerButton = new Button("Register");
        registerButton.setMinSize(175,25);
        registerButton.setMaxSize(175,25);
        registerButton.setStyle(buttonCSS);
        registerButton.setOnAction(e -> primaryStage.setScene(registerScene()));

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, studentLoginButton,
                teacherLoginButton, registerPrompt, registerButton, errorMassage);

        return new Scene(layout, 920,520);
    }

    public Scene registerScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Neptun Subject Application");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        TextField usernameField = new TextField();
        usernameField.setMaxWidth(175);
        usernameField.setPromptText("Username... ");
        usernameField.setStyle(highlight);

        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(175);
        passwordField.setPromptText("Password... ");
        passwordField.setStyle(highlight);

        Label errorMassage = new Label();
        errorMassage.setStyle(errorMassageCSS);

        Button studentRegisterButton = new Button("Register as Student");
        studentRegisterButton.setMinSize(175,25);
        studentRegisterButton.setMaxSize(175,25);
        studentRegisterButton.setStyle(buttonCSS);
        studentRegisterButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean successfulRegister = registerHandler.registerStudent(username, password);
            if(successfulRegister) {
                primaryStage.setScene(loginScene());
            }
            else {
                if (!registerHandler.idIncrementer()) errorMassage.setText("Exceeding the available user space");
                else errorMassage.setText("Register failed, please try again.");
            }
        });

        Button teacherRegisterButton = new Button("Register as Teacher");
        teacherRegisterButton.setMinSize(175,25);
        teacherRegisterButton.setMaxSize(175,25);
        teacherRegisterButton.setStyle(buttonCSS);
        teacherRegisterButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean successfulRegister = registerHandler.registerTeacher(username, password);
            if(successfulRegister) {
                primaryStage.setScene(loginScene());
            }
            else {
                if (!registerHandler.idIncrementer()) errorMassage.setText("Exceeding the available user space");
                else errorMassage.setText("Register failed, please try again.");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setMinSize(175,25);
        cancelButton.setMaxSize(175,25);
        cancelButton.setStyle(buttonCSS);
        cancelButton.setOnAction(e -> primaryStage.setScene(loginScene()));

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, studentRegisterButton,
                teacherRegisterButton, cancelButton, errorMassage);

        return new Scene(layout,920,520);
    }

    public Scene studentHomeScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Student Home Screen");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        Label usernameDisplayer = new Label();
        usernameDisplayer.setText(displayedUsername);

        Button logoutButton = new Button("Log out");
        logoutButton.setStyle(buttonCSS);
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene()));

        layout.getChildren().addAll(usernameDisplayer, titleLabel, logoutButton);
        return new Scene(layout,920,520);
    }

    public Scene teacherScene(){
        Label topLabel = new Label(displayedUsername);
        topLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Log out");
        logoutButton.setMaxSize(175,25);
        logoutButton.setMinSize(175,25);
        logoutButton.setStyle(buttonCSS);
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene()));

        HBox topLayout = new HBox(10, topLabel, logoutButton);
        topLayout.setAlignment(Pos.TOP_LEFT);
        topLayout.setPadding(new Insets(10));

        Label centerLabel = new Label("My Courses");

        VBox courseContainer = new VBox(10);
        courseContainer.setPadding(new Insets(10));
        courseContainer.setAlignment(Pos.CENTER);

        ArrayList<Course> taughtCoursesList = courseHandler.showTeacherCourses(displayedUsername);
        if (taughtCoursesList != null){
            for (Course c : taughtCoursesList){
                Label taughtCourseLabel = new Label();
                taughtCourseLabel.setText("Class Name: "+c.getClassName()+'\n'+
                        "Class Teacher: "+c.getClassTeacher()+'\n'+
                        "Credit Number: "+c.getCreditNumber()+'\n'+
                        "Class Start Time: "+c.getClassStartTime()+'\n'+
                        "Class End Time: "+c.getClassEndTime());
                taughtCourseLabel.setMinWidth(300);
                taughtCourseLabel.setStyle("-fx-padding: 5; " +
                        "-fx-background-color: #f0f0f0; " +
                        "-fx-border-color: #dcdcdc;");
                courseContainer.getChildren().add(taughtCourseLabel);
            }
        }

        VBox centeredCourseContainer = new VBox(courseContainer);
        centeredCourseContainer.setAlignment(Pos.CENTER);   //wtf why

        ScrollPane scrollPane = new ScrollPane(centeredCourseContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setMinWidth(350);
        scrollPane.setMaxWidth(350);

        VBox centerLayout = new VBox(10, centerLabel, scrollPane);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPadding(new Insets(10));

        Button createCourseButton = new Button("Create Course");
        createCourseButton.setMinSize(175,25);
        createCourseButton.setMaxSize(175,25);
        createCourseButton.setStyle(buttonCSS);
        createCourseButton.setOnAction(e -> primaryStage.setScene(createCourseScene()));

        Button removeCourseButton = new Button("Remove Course");
        removeCourseButton.setMinSize(175,25);
        removeCourseButton.setMaxSize(175,25);
        removeCourseButton.setStyle(buttonCSS);

        HBox bottomLayout = new HBox(10, createCourseButton, removeCourseButton);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(10));

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);
        return new Scene(mainLayout, 920,520);
    }

    public Scene createCourseScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Create Course");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        TextField courseName = new TextField();
        courseName.setMaxSize(175,25);
        courseName.setMinSize(175,25);
        courseName.setPromptText("Course name...");

        TextField courseTeacher = new TextField();
        courseTeacher.setMaxSize(175,25);
        courseTeacher.setMinSize(175,25);
        courseTeacher.setPromptText("Course teacher...");

        TextField courseCreditNumber = new TextField();
        courseCreditNumber.setMaxSize(175,25);
        courseCreditNumber.setMinSize(175,25);
        courseCreditNumber.setPromptText("Course credit number...");

        TextField courseStartTime = new TextField();
        courseStartTime.setMaxSize(175,25);
        courseStartTime.setMinSize(175,25);
        courseStartTime.setPromptText("Course start hour...");

        TextField courseEndTime = new TextField();
        courseEndTime.setMaxSize(175,25);
        courseEndTime.setMinSize(175,25);
        courseEndTime.setPromptText("Course end hour...");

        Label errorMassage = new Label();
        errorMassage.setStyle(errorMassageCSS);

        Button createCourse = new Button("Create");
        createCourse.setMaxSize(175,25);
        createCourse.setMinSize(175,25);
        createCourse.setStyle(buttonCSS);
        createCourse.setOnAction(e -> {
            String name = courseName.getText();
            String teacher = courseTeacher.getText();
            String credit = courseCreditNumber.getText();
            String start = courseStartTime.getText();
            String end = courseEndTime.getText();
            boolean courseAdded = courseHandler.addCourse(name,teacher, Integer.parseInt(credit),start,end);
            if (courseAdded){
                primaryStage.setScene(teacherScene());
            }
            else{
                errorMassage.setText("Failed to create course.");
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(175,25);
        cancelButton.setMinSize(175,25);
        cancelButton.setStyle(buttonCSS);
        cancelButton.setOnAction(e -> {
            primaryStage.setScene((teacherScene()));
        });

        layout.getChildren().addAll(titleLabel, courseName, courseTeacher,
                courseCreditNumber, courseStartTime, courseEndTime, createCourse,
                cancelButton);
        return new Scene(layout,920,520);
    }


    public Scene teachSceneV1(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Teacher Home Screen");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        Label usernameDisplayer = new Label();
        usernameDisplayer.setText(displayedUsername);

        Button createCourseButton = new Button("Create Course");
        createCourseButton.setMinSize(175,25);
        createCourseButton.setMaxSize(175,25);
        createCourseButton.setStyle(buttonCSS);
        createCourseButton.setOnAction(e -> primaryStage.setScene(createCourseScene()));

        Button logoutButton = new Button("Log out");
        logoutButton.setMaxSize(175,25);
        logoutButton.setMinSize(175,25);
        logoutButton.setStyle(buttonCSS);
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene()));

        layout.getChildren().addAll(usernameDisplayer, titleLabel, createCourseButton, logoutButton);
        return new Scene(layout,920,520);
    }
}
