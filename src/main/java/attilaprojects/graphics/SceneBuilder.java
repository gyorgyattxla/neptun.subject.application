package attilaprojects.graphics;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class SceneBuilder {

    public Scene loginScene(){
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Neptun Subject Application");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setPadding(new Insets(10));

        String highlight =  "-fx-border-color: #D3D3D3;" +
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

        Button loginButton = new Button("Login");
        loginButton.setMinSize(175,25);
        loginButton.setMaxSize(175,25);
        loginButton.setStyle(buttonCSS);

        Button registerButton = new Button("Register");
        registerButton.setMinSize(175,25);
        registerButton.setMaxSize(175,25);
        registerButton.setStyle(buttonCSS);

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, registerButton);

        return new Scene(layout, 920,520);
    }
}
