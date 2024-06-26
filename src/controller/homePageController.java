package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.sql.SQLException;

public class homePageController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView blackBg;

    @FXML
    private Pane locationPane;

    static String username = null;
    static String password = null;
    static int accUserId;

    @FXML
    private Text acc_name_text;

    @FXML
    private Button RFIDVerifyButton, submitReportButton, storeLocationButton, activityHistoryButton, hideLocationBtn, signoutBtn;

    @FXML
    public void initialize() throws SQLException {
        accUserId = user.getUserIdByEmailAndPassword(username, password);
        rfidDetailsController.accUserId = accUserId;
        activityHistoryController.accUserId = accUserId;
        reportHistoryController.accUserId = accUserId;
        System.out.println(accUserId);
        intro_name(accUserId);
    }

    public void intro_name(int accUserId_input) throws SQLException {
        String acc_name = user.getNameById(accUserId_input);
        acc_name_text.setText(acc_name + "!");
    }

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
        changeScene(event, "/view/scanhistory.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void hideLocationPane() {
        removeBlurEffect();
        TranslateTransition transition = new TranslateTransition(Duration.millis(800), locationPane);
        transition.setToY(0);
        transition.play();
    }

    public void showLocationPane() {
        applyBlurEffect();
        TranslateTransition transition = new TranslateTransition(Duration.millis(800), locationPane);
        transition.setToY(-250);
        transition.play();
    }

    public void applyBlurEffect() {
        blackBg.setVisible(true);
    }

    public void removeBlurEffect() {
        blackBg.setVisible(false);
    }

    public void signout(ActionEvent event) throws IOException {
        System.out.println("Signout button clicked");
        changeScene(event, "/view/loginpage.fxml");
    }
}
