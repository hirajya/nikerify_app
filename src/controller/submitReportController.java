package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException; 

public class submitReportController {

    @FXML
    Button backToHomeButton, nextButton;

    @FXML
    Pane p1, p2, p3;

    @FXML
    RadioButton rb1, rb2, rb3;

    @FXML
    private ToggleGroup choices;
    
    
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

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToNext(ActionEvent event) throws IOException {
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
