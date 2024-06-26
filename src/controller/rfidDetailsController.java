package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.inventory_units;
import model.verification;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class rfidDetailsController {

    @FXML
    Button backButtonRFID, submitReportBtn, submitReportBtn2;

    @FXML
    Pane passResult, failResult;

    static boolean isRFIDVerified = false;

    static String rfid_val;
    static String shoe_id1;

    public static int accUserId;


    public void initialize() throws SQLException {
        System.out.println(rfid_val);
        saveVerification();
        showValue();
    }

    public void showValue() {
        if (isRFIDVerified) {
            passResult.setVisible(true);
            failResult.setVisible(false);
        } else {
            passResult.setVisible(false);
            failResult.setVisible(true);
        }
    }

    public void saveVerification() throws SQLException {
        isRFIDVerified = inventory_units.checkRFIDExists(rfid_val);
        System.out.println(isRFIDVerified);
        if (isRFIDVerified) {
            shoe_id1 = inventory_units.getShoeIdByRFID(rfid_val);
            verification v = new verification(accUserId, LocalDate.now(), LocalTime.now(), shoe_id1, isRFIDVerified, rfid_val);
            v.saveVerification();
        } else {
            verification v = new verification(accUserId, LocalDate.now(), LocalTime.now(), null, isRFIDVerified, rfid_val);
            v.saveVerification();
        }
    }

    

    public void goToSubmitReport(ActionEvent event) throws Exception {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/submitReport.fxml");
    }

    public void backToRFIDVerify(ActionEvent event) throws Exception {
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
