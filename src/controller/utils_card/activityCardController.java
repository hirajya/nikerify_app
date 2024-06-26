package controller.utils_card;

import java.io.File;
import java.io.IOException;

import javax.swing.Action;

import controller.rfidDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class activityCardController {

    @FXML
    private Label label1;

    @FXML
    Text validity_txt, shoe_model_txt, scan_time_txt, scan_date_txt,verify_id_txt, serialNum_txt;

    @FXML
    ImageView authenPic;
    
    private Image checkImage;
    private Image crossImage;
    // @FXML
    // Text verify_id, shoe_model, shoe_color, scan_date

    public void initialize() {
        checkImage = new Image(new File("assets\\activityhistory\\iconamoon_check-fill.png").toURI().toString());
        crossImage = new Image(new File("assets\\activityhistory\\iconamoon_check-filll.png").toURI().toString());
        
    }

    public void seeDetails(ActionEvent event) throws IOException {
        System.out.println("See details button clicked");
        rfidDetailsController.forViewing = true;
        rfidDetailsController.rfid_val = serialNum_txt.getText();
        changeScene(event, "/view/rfidAutheticDetails.fxml");
    }

    public void setLabelText(String text) {
        label1.setText(text);
    }

    public void setSerialNum_txt(String text) {
        serialNum_txt.setText(text);
    }

    public void setScan_date_txt(String text) {
        scan_date_txt.setText(text);
    }

    public void setValidity_txt(String text) {
        validity_txt.setText(text);
        // Set text color based on validity
        if ("RFID Authentic".equals(text)) {
            validity_txt.setStyle("-fx-fill: green;");
            authenPic.setImage(checkImage);
        } else {
            validity_txt.setStyle("-fx-fill: red;");
            authenPic.setImage(crossImage);
        }
    }

    public void setShoe_model_txt(String text) {
        shoe_model_txt.setText(text);
    }

    public void setScan_time_txt(String text) {
        scan_time_txt.setText(text);
    }

    public void setValidity_id_txt(String text) {
        validity_txt.setText(text);
    }

    public void setVerify_id(String text) {
        verify_id_txt.setText(text);
    }

    public void setShoe_model(String text) {
        shoe_model_txt.setText(text);
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
