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
import java.io.IOException;

public class rfidDetailsController {

    @FXML
    Button backButtonRFID;

    @FXML
    Pane passResult, failResult;

    static boolean isRFIDVerified = false;

    public void initialize() {
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

    public void backToRFIDVerify(ActionEvent event) throws Exception {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/rfidverify.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




}
