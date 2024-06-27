package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminloginController {

    @FXML
    Button aloginButton;

    @FXML
    TextField aemail_tf, apassword_tf;

    private final String predefinedEmail = "nikerifyadmin@gmail.com";
    private final String predefinedPassword = "admin123";

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void adminLogin(ActionEvent event) throws IOException {
        System.out.println("Admin Login button clicked");

        if (checkCredentials(aemail_tf.getText(), apassword_tf.getText())) {
            changeScene(event, "/view/adashboardscansandreports.fxml");
        } else {
            System.out.println("Invalid email or password");
        }
    }

    private boolean checkCredentials(String email, String password) {
        return predefinedEmail.equals(email) && predefinedPassword.equals(password);
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
