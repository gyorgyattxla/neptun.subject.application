package attilaprojects.graphics;


import attilaprojects.MainApp;
import attilaprojects.logic.register.RegisterHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SceneBuilder {

    private Stage primaryStage;

    public SceneBuilder(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    RegisterHandler registerHandler = new RegisterHandler();

    public Scene loginScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Neptun Subject Application");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        String highlight =  "-fx-border-color: green;" +
                            "-fx-border-width: 2px;" +
                            "-fx-focus-color: transparent;" +
                            "-fx-faint-focus-color: transparent";

        String buttonCSS =  "-fx-border-width: 2px;" +
                            "-fx-background-radius: 5px;" +
                            "-fx-background-color: #D3D3D3";

        TextField usernameField = new TextField();
        usernameField.setMaxWidth(175);
        usernameField.setPromptText("Username... ");
        usernameField.setStyle(highlight);

        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(175);
        passwordField.setPromptText("Password... ");
        passwordField.setStyle(highlight);

        Button loginButton = new Button("Log in");
        loginButton.setMinSize(175,25);
        loginButton.setMaxSize(175,25);
        loginButton.setStyle(buttonCSS);

        Button registerButton = new Button("Register");
        registerButton.setMinSize(175,25);
        registerButton.setMaxSize(175,25);
        registerButton.setStyle(buttonCSS);
        registerButton.setOnAction(e -> primaryStage.setScene(registerScene()));

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, registerButton);

        return new Scene(layout, 920,520);
    }

    public Scene registerScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Neptun Subject Application");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        String highlight =  "-fx-border-color: green;" +
                "-fx-border-width: 2px;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent";

        String buttonCSS =  "-fx-border-width: 2px;" +
                "-fx-background-radius: 5px;" +
                "-fx-background-color: #D3D3D3";

        String errorMassageCSS = "-fx-text-fill: red";

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

        Button registerButton = new Button("Register");
        registerButton.setMinSize(175,25);
        registerButton.setMaxSize(175,25);
        registerButton.setStyle(buttonCSS);
        registerButton.setOnAction(e -> {
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

        Button cancelButton = new Button("Cancel");
        cancelButton.setMinSize(175,25);
        cancelButton.setMaxSize(175,25);
        cancelButton.setStyle(buttonCSS);
        cancelButton.setOnAction(e -> primaryStage.setScene(loginScene()));




        layout.getChildren().addAll(titleLabel, usernameField, passwordField,
                registerButton, cancelButton, errorMassage);

        return new Scene(layout,920,520);
    }
}
