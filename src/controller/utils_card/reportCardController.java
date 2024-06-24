package controller.utils_card;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.location;
import model.report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.*;

public class reportCardController {

    @FXML
    private Label verify_id;

    @FXML
    private Button seeDetailsBtn;

    @FXML
    private ImageView image_product;

    @FXML
    private Text shoe_model_txt, scan_date, scan_time, validity_text;

    private Stage primaryStage;

	public int ts_id;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        // Initialization code if needed
    }

    @FXML
    public void goToItemDetails(ActionEvent event) {
        System.out.println("Proceed to see details");
        // Example: Set dynamic ts_id based on some logic
        int ts_id = determineTsIdBasedOnLogic();
        try {
            changeScene(event, "/view/reportDetailsOnline.fxml", ts_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeScene(ActionEvent event, String fxml, int ts_id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        if ("/view/reportDetailsOnline.fxml".equals(fxml)) {
            reportDetailOnlineController controller = loader.getController();
            try {
                report rep = fetchReportDetails(ts_id);
                if (rep != null) {
                    controller.setStatus(rep.getReport_status());
                    controller.setReportId(String.valueOf(rep.getReport_id()));
                    controller.setVerificationRequestId(String.valueOf(rep.getVerification_id()));
                    controller.setAuthenticityResult("Authentic"); // Example, this should be fetched from your logic
                    controller.setReportDate(rep.getReport_date().toString());
                    controller.setShoeModel(rep.getInput_shoe_model());
                    controller.setPurchaseDate(rep.getPurchase_date().toString());
                    controller.setWDYproduct("Nike Air Max"); // Example, this should be fetched from your logic
                    controller.setStoreLocation(getStoreLocation(rep.getType_seller()));
                    controller.setStoreName(getStoreName(rep.getType_seller()));
                    controller.setStoreContactNumber(getStoreContactNumber(rep.getType_seller()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int determineTsIdBasedOnLogic() {
        // Example logic to determine ts_id dynamically
        // Replace with your actual logic to determine ts_id
        return 1; // Example value, replace with actual determined ts_id
    }

    private report fetchReportDetails(int ts_id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        report rep = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT * FROM report WHERE type_seller = ?"; // Modify this query to match your schema
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ts_id); // Use appropriate parameter

            rs = pstmt.executeQuery();

            if (rs.next()) {
                rep = new report(
                    rs.getInt("report_id"),
                    rs.getInt("user_id"),
                    rs.getInt("verification_id"),
                    rs.getString("input_shoe_model"),
                    rs.getDate("purchase_date").toLocalDate(),
                    rs.getInt("type_seller"),
                    rs.getString("report_comment"),
                    rs.getString("report_status"),
                    rs.getDate("scan_date").toLocalDate(),
                    rs.getTime("scan_time").toLocalTime()
                    // rs.getBytes("product_photo"),
                    // rs.getBytes("receipt_photo")
                );
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

        return rep;
    }

    private String getStoreLocation(int typeSellerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String storeLocation = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT l.street_number, l.block_number, l.barangay, l.city " +
                         "FROM location l " +
                         "JOIN type_seller_table ts ON l.store_location_id = ts.store_location_id " +
                         "WHERE ts.ts_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeSellerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                storeLocation = rs.getString("street_number") + ", " +
                                rs.getString("block_number") + ", " +
                                rs.getString("barangay") + ", " +
                                rs.getString("city");
            }
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return storeLocation;
    }

    private String getStoreName(int typeSellerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String storeName = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT store_name FROM type_seller_table WHERE ts_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeSellerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                storeName = rs.getString("store_name");
            }
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return storeName;
    }

    private String getStoreContactNumber(int typeSellerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String storeContactNumber = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT store_contact_number FROM type_seller_table WHERE ts_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeSellerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                storeContactNumber = rs.getString("store_contact_number");
            }
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return storeContactNumber;
    }

    private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example method to handle different scenarios or pane button clicks
    @FXML
    public void handleDynamicScenario(ActionEvent event) {
        int ts_id = determineTsIdBasedOnLogic();
        try {
            changeScene(event, "/view/reportDetailsOnline.fxml", ts_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVerify_Id(String valueOf) {
        verify_id.setText(valueOf);
    }

    public void setShoeModel(String valueOf) {
        shoe_model_txt.setText(valueOf);
    }

    public void setScanDate(String valueOf) {
        scan_date.setText(valueOf);
    }

    public void setValidityText(String valueOf) {
        validity_text.setText(valueOf);
    }

    public void setScanTime(String valueOf) {
        scan_time.setText(valueOf);
    }

    public void setProductImage(Image productImage) {
        image_product.setImage(productImage);
    }

    public void setProductImage(String path) {
        image_product.setImage(new Image(path));
    }


}
