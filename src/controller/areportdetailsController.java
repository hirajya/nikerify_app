package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.report;





public class areportdetailsController {

    @FXML
    Button inventoryButton, reportsButton, backButton, resolvedButton;

    @FXML
    private Label reportIDLabel;

    @FXML
    private Label reportIDLabel1;

    //@FXML
    //private Label userIdLabel;

    @FXML
    private Label verificationIdLabel;

    @FXML
    private Label shoemodelLabel;

    @FXML
    private Label purchasedateLabel;

    @FXML
    private Label typesellerLabel;

    @FXML
    private Label reportcommentLabel;

    //@FXML
    //private Label statusLabel;

    @FXML
    private Label reportdateLabel;

    @FXML
    private Label reportTimeLabel;
    
    @FXML
    TextField search_tf;

    private report currentReport;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void initData(report selectedReport) {
        currentReport = selectedReport;
        reportIDLabel.setText(String.valueOf(selectedReport.getReport_id()));
        verificationIdLabel.setText(String.valueOf(selectedReport.getVerification_id()));
        shoemodelLabel.setText(selectedReport.getInput_shoe_model());
        purchasedateLabel.setText(String.valueOf(selectedReport.getPurchase_date()));
        typesellerLabel.setText(String.valueOf(selectedReport.getType_seller()));
        reportcommentLabel.setText(selectedReport.getReport_comment());
        reportdateLabel.setText(String.valueOf(selectedReport.getReport_date()));
        reportTimeLabel.setText(String.valueOf(selectedReport.getReport_time()));
        reportIDLabel1.setText(String.valueOf(selectedReport.getReport_id()));
    }

    public void goToInventory(ActionEvent event) throws IOException {
        System.out.println("Inventory button clicked");
        changeScene(event, "/view/ainventory.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked");
        changeScene(event, "/view/areports.fxml");
    }

    public void backToDashboardOrReports(ActionEvent event) throws IOException {
        System.out.println("Back button clicked");
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }

    @FXML
    public void markAsResolved(ActionEvent event) throws IOException {
        if (currentReport != null) {
            System.out.println("Resolved Button clicked");
            updateReportStatus(currentReport.getReport_id(), "resolved");
            currentReport.setReport_status("resolved");
            // Optionally, update the UI to reflect the new status
        } else {
            System.out.println("No report selected");
        }
    }

    private void updateReportStatus(int reportId, String status) {
        String url = "jdbc:mysql://localhost:3306/nikerify_db";
        String user = "root";
        String password = "";

        String sql = "UPDATE report SET report_status = ? WHERE report_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, reportId);
            pstmt.executeUpdate();

            System.out.println("Report status updated to " + status);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
