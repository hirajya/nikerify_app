package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException; 

public class submitReportController {

    @FXML
    TextField verify_id_tf, shoe_model_tf;

    @FXML
    Button backToHomeButton, nextButton;

    @FXML
    DatePicker purchase_date_dp;

    @FXML
    Pane p1, p2, p3;

    @FXML
    RadioButton rb1, rb2, rb3;

    @FXML
    TextField rb1_store_location, rb2_store_location, rb2_store_name, rb3_store_location, rb3_store_name, rb3_store_contact_number;

    @FXML
    private ToggleGroup choices;

    String input_verify_id;
    String input_shoe_model;
    String selectedTypeSeller;
    String storeLocation;
    String storeName;
    String storeContactNumber;
    
    
    public void initialize() {
        // Add radio buttons to the toggle group
        choices = new ToggleGroup();
        rb1.setToggleGroup(choices);
        rb2.setToggleGroup(choices);
        rb3.setToggleGroup(choices);
        

        // Add listener to toggle group
        choices.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                handleRadioButtonChange(selectedRadioButton);
            }
        });

        // Initially hide all panes
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
    }

    public void typeSellerPicker() {
        input_verify_id = verify_id_tf.getText();
        input_shoe_model = shoe_model_tf.getText();
        if (rb1.isSelected()) {
            selectedTypeSeller = "Official Nike Store";
            storeLocation = rb1_store_location.getText(); 
            storeName = null;
            storeContactNumber = null;
        } else if (rb2.isSelected()) {
            selectedTypeSeller = "Physical Reseller";
            storeLocation = rb2_store_location.getText(); 
            storeName = rb2_store_name.getText(); 
            storeContactNumber = null;

        } else if (rb3.isSelected()) {
            selectedTypeSeller = "Online Merchant";
            storeLocation = rb3_store_location.getText(); 
            storeName = rb3_store_name.getText(); 
            storeContactNumber = rb3_store_contact_number.getText();
        } else {
            selectedTypeSeller = null;
            storeLocation = null;
            storeName = null;
            storeContactNumber = null;
        }
    }



    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToNext(ActionEvent event) throws IOException {

        supportDocumentsController.input_verify_id1 = input_verify_id;
        supportDocumentsController.input_shoe_model1 = input_shoe_model;
        supportDocumentsController.selectedTypeSeller1 = selectedTypeSeller;

        // supportDocumentsController.storeLocation1 = storeLocation;
        // supportDocumentsController.storeName1 = storeName;
        // supportDocumentsController.storeContactNumber1 = storeContactNumber;
        
       
        System.out.println("Next button clicked");
        changeScene(event, "/view/supportDocuments.fxml");
    }

    private void handleRadioButtonChange(RadioButton selectedRadioButton) {
        // Hide all panes initially
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);

        // Show the pane corresponding to the selected radio button
        if (selectedRadioButton == rb1) {
            p1.setVisible(true);
        } else if (selectedRadioButton == rb2) {
            p2.setVisible(true);
        } else if (selectedRadioButton == rb3) {
            p3.setVisible(true);
        }
    }


    public void changeScene(ActionEvent event, String fxml) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




}
