package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class signupController1 {

    @FXML
    Button signupProceedButton, backToWelcomeButton, toLoginButton;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToWelcome(ActionEvent event) throws IOException {
        System.out.println("Back to welcome button clicked");
        changeScene(event, "/view/welcomepage.fxml");
    }

    public void goToSignupProceed(ActionEvent event) throws IOException {
        System.out.println("Signup proceed button clicked");
        changeScene(event, "/view/signuppage2.fxml");
    }

    public void goToLogin(ActionEvent event) throws IOException {
        System.out.println("Login button clicked");
        changeScene(event, "/view/loginpage.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
