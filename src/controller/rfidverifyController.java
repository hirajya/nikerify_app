package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class rfidverifyController {

    @FXML
    Button backToHomeButton, readAcceptRFID, readDenyRFID, TryAgainBnt, continueButton, RFIDpassedBTN, RFIDDeniedBTN;

    @FXML
    Text responseAnswerPass, textMiddleInstruc, responseAnswerFail;

    @FXML
    Rectangle recAccepted, recDenied, completeTransacRec;
    
    @FXML
    public void initialize() {
        responseAnswerPass.setVisible(false);
        responseAnswerFail.setVisible(false);
        recAccepted.setVisible(false);
        recDenied.setVisible(false);
        TryAgainBnt.setVisible(false);
        completeTransacRec.setVisible(false);
    }

    public void RFIDpassed(ActionEvent event) throws IOException {
        rfidDetailsController.isRFIDVerified = true;
    }

    public void RFIDDenied(ActionEvent event) throws IOException {
        rfidDetailsController.isRFIDVerified = false;
    }

    public void continueToDetails(ActionEvent event) throws IOException {
        System.out.println("Proceed to details clicked");
        changeScene(event, "/view/rfidAutheticDetails.fxml");
    }

    public void verifyRFID(ActionEvent event) {
        TryAgainBnt.setVisible(false);
        System.out.println("RFID verified");
        textMiddleInstruc.setText("RFID detected successfully! Tap 'Continue' to proceed");
        responseAnswerPass.setVisible(true);
        recAccepted.setVisible(true);
        recDenied.setVisible(false);
        responseAnswerFail.setVisible(false);
        completeTransacRec.setVisible(true);
        continueButton.setVisible(true);
    }

    public void denyRFID(ActionEvent event) {
        continueButton.setVisible(false);
        System.out.println("RFID denied");
        textMiddleInstruc.setText("RFID scan failed! Try Again.");        
        textMiddleInstruc.setStyle("-fx-fill: black;");
        recAccepted.setVisible(false);
        recDenied.setVisible(true);
        responseAnswerFail.setVisible(true);
        responseAnswerPass.setVisible(false);
        completeTransacRec.setVisible(true);
        TryAgainBnt.setVisible(true);
    }

    public void ReadRFIDagain(ActionEvent event) {
        continueButton.setVisible(false);
        TryAgainBnt.setVisible(false);
        System.out.println("Read RFID again button clicked");
        textMiddleInstruc.setText("Tap your phone near the tongue of the shoe to detect the RFID and continue.");
        recAccepted.setVisible(false);
        recDenied.setVisible(false);
        responseAnswerFail.setVisible(false);
        responseAnswerPass.setVisible(false);
        completeTransacRec.setVisible(false);
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
