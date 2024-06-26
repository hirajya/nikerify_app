package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.verification;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class activityHistoryController {

    @FXML
    private ScrollPane scrollPaneReport;

    @FXML
    private Button backBtn, reportsButton;

    static int accUserId;

    @FXML
    public void initialize() {
        try {
            loadReportCards();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to Submit Report clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        changeScene(event, "/view/scanhistoryreports.fxml");
    }

    public void goToItemDetails(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        rfidDetailsController.isRFIDVerified = true;
        changeScene(event, "/view/rfidAutheticDetails.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void loadReportCards() throws IOException, SQLException {
        VBox content = new VBox();
        content.setSpacing(10);

        // Fetch the list of reports from the database
        List<verification> verifications = getReportsFromDatabase();

        for (verification veri : verifications) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/utils/scanActivityCard.fxml"));
            Parent reportCard = loader.load();

            // Set data for each report card
            controller.utils_card.activityCardController controller = loader.getController();
            controller.setLabelText("RFID Authentic");
            controller.setScan_date_txt(veri.getVerification_date().toString());
            controller.setScan_time_txt(veri.getVerification_time().toString());
            controller.setShoe_model_txt(getShoeModel2(getShoeModel(veri.getShoe_id())));
            controller.setValidity_txt("RFID Authentic");
            controller.setVerify_id_txt(String.valueOf(veri.getVerification_id()));
            controller.setSerialNum_txt(veri.getSerial_number());
            

            content.getChildren().add(reportCard);
        }

        scrollPaneReport.setContent(content);
    }

    public String getShoeModel(String shoeId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String shoeModel = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT model_ID FROM inventory_units WHERE shoe_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, shoeId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                shoeModel = rs.getString("model_ID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return shoeModel;
    }

    public String getShoeModel2(String model_id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String shoeModel = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT shoe_name FROM inventory_model WHERE model_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                shoeModel = rs.getString("shoe_name");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return shoeModel;
    }



    private List<verification> getReportsFromDatabase() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<verification> verifications = new ArrayList<>();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT * FROM verification WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accUserId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                verification verification = new verification(
                    rs.getInt("verification_id"),
                    rs.getInt("user_id"),
                    rs.getDate("verification_time").toLocalDate(),
                    rs.getTime("verification_time").toLocalTime(),
                    rs.getString("shoe_id"),
                    rs.getBoolean("authenticity_result"),
                    rs.getString("serial_number")
                );
                verifications.add(verification);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return verifications;
    }
}
