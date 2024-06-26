package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.inventory_models;
import model.inventory_units;
import model.user;
import model.verification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;

public class rfidDetailsController {

    @FXML
    Button backButtonRFID, submitReportBtn, submitReportBtn2;

    @FXML
    Pane passResult, failResult;

    static boolean isRFIDVerified = false;

    public static String rfid_val;
    static String shoe_id1;
    static String model_id1;

    public static int accUserId;

    public static boolean forViewing = false;
    
    @FXML
    Text verify_id_txt, batch_number_txt, manufacturing_location_txt, scan_date_txt, manufacturing_year_txt, unit_color_txt, full_name_txt, model_shoe_txt;

    public void initialize() throws SQLException {
        System.out.println(rfid_val);

        if (!forViewing) {
            saveVerification();
        }

        // Common fetch operations
        fetchData();
        fetchUserName();
        fetchShoeModel();
        showValue();

    }

    public void showValue() throws SQLException {
        isRFIDVerified = inventory_units.checkRFIDExists(rfid_val);
        if (isRFIDVerified) {
            passResult.setVisible(true);
            failResult.setVisible(false);
        } else {
            passResult.setVisible(false);
            failResult.setVisible(true);
        }
    }

    public void saveVerification() throws SQLException {
        isRFIDVerified = inventory_units.checkRFIDExists(rfid_val);
        System.out.println(isRFIDVerified);
        verification v;

        if (isRFIDVerified) {
            shoe_id1 = inventory_units.getShoeIdByRFID(rfid_val);
            v = new verification(accUserId, LocalDate.now(), LocalTime.now(), shoe_id1, isRFIDVerified, rfid_val);
        } else {
            v = new verification(accUserId, LocalDate.now(), LocalTime.now(), null, isRFIDVerified, rfid_val);
        }

        int verificationId = v.saveVerification();
        verify_id_txt.setText(String.valueOf(verificationId));
    }

    public void goToSubmitReport(ActionEvent event) throws Exception {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/submitReport.fxml");
    }

    public void backToRFIDVerify(ActionEvent event) throws Exception {
        System.out.println("Back to home button clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void fetchData() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");

            String sql = "SELECT batch_number, model_ID, manufacturing_location, manufacturing_year, unit_color, last_scan_date FROM inventory_units WHERE verification_serial_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid_val);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String batchNumber = rs.getString("batch_number");
                String manufacturingLocation = rs.getString("manufacturing_location");
                Year manufacturingYear = Year.of(rs.getInt("manufacturing_year"));
                String unit_color = rs.getString("unit_color");
                String scan_date = rs.getString("last_scan_date");
                String model_id = rs.getString("model_ID");

                batch_number_txt.setText(batchNumber);
                manufacturing_location_txt.setText(manufacturingLocation);
                manufacturing_year_txt.setText(manufacturingYear.toString());
                unit_color_txt.setText(unit_color);
                scan_date_txt.setText(scan_date);
                model_id1 = model_id;
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
    }

    public void fetchUserName() throws SQLException {
        String fullName = user.getFullNameById(accUserId);
        full_name_txt.setText(fullName);
    }

    public void fetchShoeModel() throws SQLException {
        String shoe_model_value1 = inventory_models.getShoeModelByModelId(model_id1);
        model_shoe_txt.setText(shoe_model_value1);
    }
}
