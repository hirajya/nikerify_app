package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class reportHistoryController {

    @FXML
    private ScrollPane scrollPaneReport;

    @FXML
    private Button backBtn, goToHistoryScanBtn;

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
        System.out.println("Back to Homepage clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToReportsDetail(ActionEvent event) throws IOException {
        System.out.println("View details of report clicked");
        changeScene(event, "/view/reportDetailsOnline.fxml");
    }

    public void goToHistoryScan(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        rfidDetailsController.isRFIDVerified = true;
        changeScene(event, "/view/scanhistory.fxml");
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
        List<report> reports = getReportsFromDatabase();

        for (report rep : reports) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/utils/scanHistoryCard.fxml"));
            Parent reportCard = loader.load();

            // Set data for each report card
            controller.utils_card.reportCardController controller = loader.getController();
            controller.setVerify_Id(String.valueOf(rep.getVerification_id()));
            controller.setShoeModel(rep.getInput_shoe_model());
            controller.setScanDate(rep.getReport_date().toString());
            controller.setValidityText("ito nlng muna");
            controller.setScanTime(rep.getReport_time().toString());
            controller.ts_id = rep.getType_seller();

            // Convert byte array to Image and set to ImageView
            if (rep.getProduct_photo() != null) {
                Image productImage = new Image(new ByteArrayInputStream(rep.getProduct_photo()));
                controller.setProductImage(productImage);
            } else {
                controller.setProductImage("/resources/images/shoe.png"); // Default image
            }

            content.getChildren().add(reportCard);
        }

        scrollPaneReport.setContent(content);
    }

    private List<report> getReportsFromDatabase() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT * FROM report WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accUserId);
            rs = pstmt.executeQuery();

            List<report> reports = new ArrayList<>();
            while (rs.next()) {
                report rep = new report(
                    rs.getInt("report_id"),
                    rs.getInt("user_id"),
                    rs.getInt("verification_id"),
                    rs.getString("input_shoe_model"),
                    rs.getDate("purchase_date").toLocalDate(),
                    rs.getInt("type_seller"),
                    rs.getString("report_comment"),
                    rs.getString("report_status"),
                    rs.getDate("scan_date").toLocalDate(),
                    rs.getTime("scan_time").toLocalTime(),
                    rs.getBytes("product_photo"), // Fetch the product photo
                    rs.getBytes("receipt_photo")  // Fetch the receipt photo
                );
                reports.add(rep);
            }
            return reports;

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
    }


}
