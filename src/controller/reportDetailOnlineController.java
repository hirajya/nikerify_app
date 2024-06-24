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

import java.io.IOException;

public class reportDetailOnlineController {

    @FXML
    Button backToHistoryReportsBtn;

    @FXML
    Text status, report_id, verification_request_id, authenticity_result, report_date, shoe_model, purchase_date, WDYproduct, store_location, store_name, store_contact_number;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void backToRepHist(ActionEvent event) throws IOException {
        System.out.println("Back to reports history clicked");
        changeScene(event, "/view/scanhistoryreports.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setStatus(String status_input) {
        status.setText(status_input);
    }

    public void setReportId(String report_id_input) {
        report_id.setText(report_id_input);
    }

    public void setVerificationRequestId(String verification_request_id_input) {
        verification_request_id.setText(verification_request_id_input);
    }

    public void setAuthenticityResult(String authenticity_result_input) {
        authenticity_result.setText(authenticity_result_input);
    }

    public void setReportDate(String report_date_input) {
        report_date.setText(report_date_input);
    }

    public void setShoeModel(String shoe_model_input) {
        shoe_model.setText(shoe_model_input);
    }

    public void setPurchaseDate(String purchase_date_input) {
        purchase_date.setText(purchase_date_input);
    }

    public void setWDYproduct(String WDYproduct_input) {
        WDYproduct.setText(WDYproduct_input);
    }

    public void setStoreLocation(String store_location_input) {
        store_location.setText(store_location_input);
    }

    public void setStoreName(String store_name_input) {
        store_name.setText(store_name_input);
    }

    public void setStoreContactNumber(String store_contact_number_input) {
        store_contact_number.setText(store_contact_number_input);
    }
}
