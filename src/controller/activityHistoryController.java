package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException; 

public class activityHistoryController {

    @FXML
    Button backBtn, reportsButton, itemDetailBtn;
    
    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to Submit Report clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        changeScene(event, "/view/scanhistoryreports.fxml");
    }

    public void goToItemDetails(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        rfidDetailsController.isRFIDVerified = true;
        changeScene(event, "/view/rfidAutheticDetails.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
