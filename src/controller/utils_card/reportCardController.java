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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reportCardController {

    @FXML
    private Label verify_id;

    @FXML
    private Button seeDetailsBnt;

    @FXML
    private ImageView image_product;

    @FXML
    public int report_id;

    public static int reportId_val;
    public static int userId_val;
    public static int verificationId_val;
    public static String shoeModel_val;
    public static String purchaseDate_val;
    public static int typeSeller_val;
    public static String reportComment_val;
    public static String reportStatus_val;
    public static String scanDate_val;
    public static String scanTime_val;
    
    public static int tsId_val;
    public static int storeLocationId_val;
    public static String storeName_val;
    public static String storeContactNumber_val;
    public static String storeLink_val;
    public static String typeSellerKind_val;

    public static String streetNumber_val;
    public static String blockNumber_val;
    public static String barangay_val;
    public static String city_val;


    @FXML
    Text shoe_model_txt, scan_date, scan_time, validity_text, ts_id_txt;

    public void initialize() throws SQLException {
        System.out.println("Report Card Controller Initialized");
        
    }

    public void setReportId(int report_id) {
        this.report_id = report_id;
    }

    // public void goToItemDetails(ActionEvent event) throws IOException {
    //     System.out.println("Proceed to see details");
    //     changeScene(event, "/view/reportDetailsOnline.fxml");
    // }

    public void goToNextSceneDetails(ActionEvent event) throws IOException, NumberFormatException, SQLException {
        fetchCombinedData(report_id);
        String valuets = getTypeSellerKind(Integer.valueOf(ts_id_txt.getText()));

        if (valuets.equals("Official Nike Store")) {
            changeScene(event, "/view/reportDetailsOfficial.fxml");
        } else if (valuets.equals("Physical Reseller")) {
            changeScene(event, "/view/reportDetailsPhysical.fxml");
        } else if (valuets.equals("Online Merchant")) {
            changeScene(event, "/view/reportDetailsOnline.fxml");
        }
    }

    

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setProductImage(Image image) {
        image_product.setImage(image);
    }

    public void setProductImage(String p_image) {
        image_product.setImage(new Image(getClass().getResourceAsStream(p_image)));
    }

    public void setVerify_Id(String v_id) {
        verify_id.setText(v_id);
    }

    public void setShoeModel(String s_model) {
        shoe_model_txt.setText(s_model);
    }

    public void setScanDate(String s_date) {
        scan_date.setText(s_date);
    }

    public void setScanTime(String s_time) {
        scan_time.setText(s_time);
    }

    public void setValidityText(String v_text) {
        validity_text.setText(v_text);
    }

    public void setTsId(int ts_id) {
        ts_id_txt.setText(String.valueOf(ts_id));
    }

    public int getTsId() {
        return Integer.parseInt(ts_id_txt.getText());
    }

    public static String getTypeSellerKind(int ts_id) throws SQLException {
        String typeSellerKind = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT type_seller_kind FROM type_seller_table WHERE ts_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ts_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                typeSellerKind = rs.getString("type_seller_kind");
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

        return typeSellerKind;
    }

    public static void fetchCombinedData(int reportIdFilter) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "SELECT r.report_id, r.user_id, r.verification_id, r.input_shoe_model, r.purchase_date, " +
                         "r.type_seller, r.report_comment, r.report_status, r.scan_date, r.scan_time, " +
                         "ts.ts_id, ts.store_location_id, ts.store_name, ts.store_contact_number, ts.store_link, ts.type_seller_kind, " +
                         "l.street_number, l.block_number, l.barangay, l.city " +
                         "FROM report r " +
                         "JOIN type_seller_table ts ON r.type_seller = ts.ts_id " +
                         "JOIN location l ON ts.store_location_id = l.store_location_id " +
                         "WHERE r.report_id = ?";  // Add WHERE clause to filter by report_id

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reportIdFilter);  // Set the report_id parameter

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // Retrieve data from the result set
                int reportId = rs.getInt("report_id");
                int userId = rs.getInt("user_id");
                int verificationId = rs.getInt("verification_id");
                String shoeModel = rs.getString("input_shoe_model");
                java.sql.Date purchaseDate = rs.getDate("purchase_date");
                int typeSeller = rs.getInt("type_seller");
                String reportComment = rs.getString("report_comment");
                String reportStatus = rs.getString("report_status");
                java.sql.Date reportDate = rs.getDate("scan_date");
                java.sql.Time reportTime = rs.getTime("scan_time");

                int tsId = rs.getInt("ts_id");
                int storeLocationId = rs.getInt("store_location_id");
                String storeName = rs.getString("store_name");
                String storeContactNumber = rs.getString("store_contact_number");
                String storeLink = rs.getString("store_link");
                String typeSellerKind = rs.getString("type_seller_kind");

                String streetNumber = rs.getString("street_number");
                String blockNumber = rs.getString("block_number");
                String barangay = rs.getString("barangay");
                String city = rs.getString("city");

                reportId_val = reportId;
                userId_val = userId;
                verificationId_val = verificationId;
                shoeModel_val = shoeModel;
                purchaseDate_val = purchaseDate.toString();
                typeSeller_val = typeSeller;
                reportComment_val = reportComment;
                reportStatus_val = reportStatus;
                scanDate_val = reportDate.toString();
                scanTime_val = reportTime.toString();

                tsId_val = tsId;
                storeLocationId_val = storeLocationId;
                storeName_val = storeName;
                storeContactNumber_val = storeContactNumber;
                storeLink_val = storeLink;
                typeSellerKind_val = typeSellerKind;

                streetNumber_val = streetNumber;
                blockNumber_val = blockNumber;
                barangay_val = barangay;
                city_val = city;

                // Print the retrieved data (or handle it as required)
                System.out.println("Report ID: " + reportId + ", User ID: " + userId + ", Verification ID: " + verificationId);
                System.out.println("Shoe Model: " + shoeModel + ", Purchase Date: " + purchaseDate + ", Type Seller: " + typeSeller);
                System.out.println("Report Comment: " + reportComment + ", Report Status: " + reportStatus + ", Report Date: " + reportDate + ", Report Time: " + reportTime);
                System.out.println("Type Seller ID: " + tsId + ", Store Location ID: " + storeLocationId + ", Store Name: " + storeName);
                System.out.println("Store Contact Number: " + storeContactNumber + ", Store Link: " + storeLink + ", Type Seller Kind: " + typeSellerKind);
                System.out.println("Street Number: " + streetNumber + ", Block Number: " + blockNumber + ", Barangay: " + barangay + ", City: " + city);
                System.out.println();
            }
        } finally {
            // Close resources
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
    

    public void loadDetail() {
        // Load the details of the report
    }

}

