package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class loginPageController {

    @FXML
    Button loginProceedButton, backToWelcomeButton, toSignupButton;

    @FXML
    TextField email_tf, password_tf;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToWelcome(ActionEvent event) throws IOException {
        System.out.println("Back to welcome button clicked");
        changeScene(event, "/view/welcomepage.fxml");
    }

    public void goToLoginProceed(ActionEvent event) throws IOException {
        if (validateCredentials(email_tf.getText(), password_tf.getText())) {
            System.out.println("Login proceed button clicked");
            homePageController.username = email_tf.getText();
            homePageController.password = password_tf.getText();
            changeScene(event, "/view/homepage.fxml");
        }
    }

    public void goToSignup(ActionEvent event) throws IOException {
        System.out.println("Signup button clicked");
        changeScene(event, "/view/signupage1.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public boolean validateCredentials(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Replace with your XAMPP connection details
            String url = "jdbc:mysql://localhost:3306/nikerify_db";
            String username = "root";
            String dbPassword = "";

            // Establish connection
            connection = DriverManager.getConnection(url, username, dbPassword);

            // Prepare SQL statement
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Execute query
            resultSet = preparedStatement.executeQuery();

            // Check if user exists
            if (resultSet.next()) {
                return true; // User exists and password matches
            } else {
                showAlert("Login Failed", "Email or password is incorrect or account doesn't exist.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while accessing the database.");
            return false;
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
