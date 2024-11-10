package attilaprojects.graphics;


import attilaprojects.logic.login.LoginHandler;
import attilaprojects.logic.register.RegisterHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SceneBuilder {

    private Stage primaryStage;
    private String displayedUsername;

    public SceneBuilder(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    RegisterHandler registerHandler = new RegisterHandler();
    LoginHandler loginHandler = new LoginHandler();

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
                primaryStage.setScene(teacherHomeScene());
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

    public Scene teacherHomeScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Teacher Home Screen");
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
}
