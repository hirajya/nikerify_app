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

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void adminLogin(ActionEvent event) throws IOException {
        System.out.println("Admin Login button clicked");
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }


    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
