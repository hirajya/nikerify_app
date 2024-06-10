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

public class storeLocationsController {

    @FXML
    Button backToHomeButton;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/homepage.fxml");
    }


    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
