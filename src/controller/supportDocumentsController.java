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

public class supportDocumentsController {

    static String input_verify_id1 = null;
    static String input_shoe_model1 = null;
    static String selectedTypeSeller1 = null;
    static String storeLocation1 = null;
    static String storeName1 = null;
    static String storeContactNumber1 = null;

    @FXML
    Button backBtn, nextButton;
    
    @FXML
    public void initialize() {
        System.out.println(input_verify_id1);
        System.out.println(input_shoe_model1);
        System.out.println(selectedTypeSeller1);
        System.out.println(storeLocation1);
        System.out.println(storeName1);
        System.out.println(storeContactNumber1);
    }

    public void backToSubmit(ActionEvent event) throws IOException {
        System.out.println("Back to Submit Report clicked");
        changeScene(event, "/view/submitReport.fxml");
    }

    public void goToNext(ActionEvent event) throws IOException {
        System.out.println("Next button clicked");
        changeScene(event, "/view/userInfoReport.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    


}
