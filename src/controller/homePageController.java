package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException; // Add import statement for IOException

public class homePageController {


    @FXML
    Button RFIDVerifyButton, submitReportButton, storeLocationButton, activityHistoryButton;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void goToRFIDVerify(ActionEvent event) throws IOException {
        System.out.println("RFID Verify button clicked");
        changeScene(event, "/view/rfidverify.fxml");
    }

    public void goToSubmitReport(ActionEvent event) throws IOException {
        System.out.println("Submit Report button clicked");
        changeScene(event, "/view/submitReport.fxml");
    }

    public void goToStoreLocation(ActionEvent event) throws IOException {
        System.out.println("Store Location button clicked");
        changeScene(event, "/view/storelocations.fxml");
    }

    public void goToActivityHistory(ActionEvent event) throws IOException {
        System.out.println("Activity History button clicked");
        changeScene(event, "/view/scanHistory.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    
    
}