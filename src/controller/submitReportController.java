package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class submitReportController {

    @FXML
    TextField verify_id_tf, shoe_model_tf, street_number_tf, block_number_tf, barangay_tf, city_tf;

    @FXML
    Pane locationPane;

    @FXML
    Button backToHomeButton, nextButton, hideLocationBtn;

    @FXML
    DatePicker purchase_date_dp;

    @FXML
    Pane p1, p2, p3;

    @FXML
    RadioButton rb1, rb2, rb3;

    @FXML
    private ToggleGroup choices;

    @FXML
    TextField rb1_store_location, rb2_store_location, rb2_store_name, rb3_store_location, rb3_store_name, rb3_store_contact_number;

    String input_verify_id;
    String input_shoe_model;
    String selectedTypeSeller;
    String storeLocation;
    String storeName;
    String storeContactNumber;
    LocalDate purchaseDate;

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
        try {
            input_verify_id = verify_id_tf.getText();
            input_shoe_model = shoe_model_tf.getText();
            purchaseDate = purchase_date_dp.getValue(); // Get the date value

            // Validate date format
            if (purchaseDate == null) {
                throw new DateTimeParseException("Null date value", "", 0);
            }

            // Check if any radio button is selected
            if (!rb1.isSelected() && !rb2.isSelected() && !rb3.isSelected()) {
                throw new IllegalArgumentException("Please select a seller type.");
            }

            // Validate based on selected radio button
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
            }

            // Validate all fields are filled
            if (input_verify_id.isEmpty() || input_shoe_model.isEmpty() || selectedTypeSeller == null || storeLocation.isEmpty() ||
                    (rb2.isSelected() && storeName.isEmpty()) ||
                    (rb3.isSelected() && (storeName.isEmpty() || storeContactNumber.isEmpty()))) {
                throw new IllegalArgumentException("Please fill all required fields.");
            }

        } catch (DateTimeParseException e) {
            showAlert("Invalid Date", "Please enter a valid date.");
        } catch (IllegalArgumentException e) {
            // showAlert("Incomplete Information", e.getMessage());
        }
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToNext(ActionEvent event) throws IOException {
        typeSellerPicker(); // Ensure this method is called to pick up the values

        // Proceed to the next scene only if all required fields are validated
        if (input_verify_id != null && input_shoe_model != null && selectedTypeSeller != null && storeLocation != null) {
            supportDocumentsController.input_verify_id1 = input_verify_id;
            supportDocumentsController.input_shoe_model1 = input_shoe_model;
            supportDocumentsController.selectedTypeSeller1 = selectedTypeSeller;
            supportDocumentsController.purchaseDate1 = purchaseDate; // Pass the purchase date
            supportDocumentsController.storeName1 = storeName;
            supportDocumentsController.storeContactNumber1 = storeContactNumber;

            System.out.println("Next button clicked");
            changeScene(event, "/view/supportDocuments.fxml");
        }
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

    public void showLocationPane() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(800), locationPane);
        transition.setToY(-250);
        transition.play();
    }

    public void hideLocationPane() {
        concatenateLocation();
        TranslateTransition transition = new TranslateTransition(Duration.millis(800), locationPane);
        transition.setToY(0);
        transition.play();
    }

    public void concatenateLocation() {
        String streetNumber = street_number_tf.getText();
        String blockNumber = block_number_tf.getText();
        String barangay = barangay_tf.getText();
        String city = city_tf.getText();

        supportDocumentsController.location_street_number1 = streetNumber;
        supportDocumentsController.location_block_number1 = blockNumber;
        supportDocumentsController.location_barangay1 = barangay;
        supportDocumentsController.location_city1 = city;
        
        String concatenated = streetNumber + ", " + blockNumber + ", " + barangay + ", " + city;
        rb1_store_location.setText(concatenated);
        rb2_store_location.setText(concatenated);
        rb3_store_location.setText(concatenated);
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
