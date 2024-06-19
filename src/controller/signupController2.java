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
import model.user;

public class signupController2 {

    static String first_n; // n = name
    static String middle_n;
    static String last_n;
    static String phone_number;
    static String email;
    static String password;
    
    @FXML
    TextField email_tf, password_tf, passwordConfirm_tf;

    @FXML
    Button signup3ProceedButton, backToSignup1Button, toLoginButton;

    @FXML
    public void initialize() {
        System.out.println(first_n);
        System.out.println(middle_n);
        System.out.println(last_n);
        System.out.println(phone_number);
        email = null;
        password = null;
    }

    public void backToSignup1(ActionEvent event) throws IOException {
        System.out.println("Back to signup1 button clicked");
        changeScene(event, "/view/signupage1.fxml");
    }

    public void goToSignup3Proceed(ActionEvent event) throws IOException {
        if (checkGetValue()) {
            addAccount();
            System.out.println("Signup3 proceed button clicked");
            changeScene(event, "/view/signuppage3.fxml");
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

    public boolean checkIfSamePass() {
        if (!password_tf.getText().equals(passwordConfirm_tf.getText())) {
            showAlert(AlertType.ERROR, "Password Error", "Passwords do not match!");
            return false;
        }
        return true;
    }

    public boolean checkGetValue() {
        if (email_tf.getText().isEmpty() || password_tf.getText().isEmpty() || passwordConfirm_tf.getText().isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "All fields must be filled out!");
            return false;
        }

        if (checkIfSamePass()) {
            password = password_tf.getText();
            email = email_tf.getText();
            return true;
        }
        
        return false;
    }

    public void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addAccount() {
        user newUser = new user(first_n, middle_n, last_n, phone_number, email, password);
        try {
            newUser.addUser();
            showAlert(AlertType.INFORMATION, "Success", "Account created successfully!");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Database Error", "Failed to create account. Please try again.");
            e.printStackTrace();
        }
    }
}
