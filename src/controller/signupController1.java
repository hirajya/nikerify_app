package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signupController1 {

    String first_n; // n = name
    String middle_n;
    String last_n;
    String phone_number;

    @FXML
    Button signupProceedButton, backToWelcomeButton, toLoginButton;

    @FXML
    TextField first_name_tf, middle_name_tf, last_name_tf, phone_number_tf;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToWelcome(ActionEvent event) throws IOException {
        System.out.println("Back to welcome button clicked");
        changeScene(event, "/view/welcomepage.fxml");
    }

    public void goToSignupProceed(ActionEvent event) throws IOException {
        if (areFieldsEmpty()) {
            showAlert("Error", "All fields must be filled in before proceeding.");
        } else {
            getValueAllInfo();
            System.out.println("Signup proceed button clicked");
            changeScene(event, "/view/signuppage2.fxml");
        }
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

    public void getValueAllInfo() {
        first_n = first_name_tf.getText();
        middle_n = middle_name_tf.getText();
        last_n = last_name_tf.getText();
        phone_number = phone_number_tf.getText();

        signupController2.first_n = first_n;
        signupController2.middle_n = middle_n;
        signupController2.last_n = last_n;
        signupController2.phone_number = phone_number;
    }

    public boolean areFieldsEmpty() {
        return first_name_tf.getText().isEmpty() || middle_name_tf.getText().isEmpty() || 
               last_name_tf.getText().isEmpty() || phone_number_tf.getText().isEmpty();
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
