package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException; // Add import statement for IOException
import java.sql.SQLException;

public class homePageController {

    static String username = null;
    static String password = null;
    static int accUserId;

    @FXML
    Text acc_name_text;


    @FXML
    Button RFIDVerifyButton, submitReportButton, storeLocationButton, activityHistoryButton;

    @FXML
    public void initialize() throws SQLException {
        accUserId = user.getUserIdByEmailAndPassword(username, password);
        System.out.println(accUserId);
    }

    // public void intro_name(int accUserId) throws SQLException {
    //     String acc_name = user.getUserName(accUserId);
    //     acc_name_text.setText(acc_name);

    // }

    public void goToRFIDVerify(ActionEvent event) throws IOException {
        System.out.println("RFID Verify button clicked");
        changeScene(event, "/view/rfidverify.fxml");
    }

    public void goToSubmitReport(ActionEvent event) throws IOException {
        submitReportController.accUserId = accUserId;
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
