package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class inventory_units {
    private String shoe_ID;
    private String model_ID;
    private String verification_serial_ID;
    private String unit_color;
    private String manufacturing_location;
    private Year manufacturing_year;
    private String batch_number;
    private Date last_scan_date;

    public inventory_units(String shoe_ID, String model_ID, String verification_serial_ID, String unit_color, String manufacturing_location, Year manufacturing_year, String batch_number, Date last_scan_date) {
        this.shoe_ID = shoe_ID;
        this.model_ID = model_ID;
        this.verification_serial_ID = verification_serial_ID;
        this.unit_color = unit_color;
        this.manufacturing_location = manufacturing_location;
        this.manufacturing_year = manufacturing_year;
        this.batch_number = batch_number;
        this.last_scan_date = last_scan_date;
    }

    public inventory_units(String model_ID, String verification_serial_ID, String unit_color, String manufacturing_location, Year manufacturing_year, String batch_number, Date last_scan_date) {
        this.model_ID = model_ID;
        this.verification_serial_ID = verification_serial_ID;
        this.unit_color = unit_color;
        this.manufacturing_location = manufacturing_location;
        this.manufacturing_year = manufacturing_year;
        this.batch_number = batch_number;
        this.last_scan_date = last_scan_date;
    }

    public String getShoe_ID() {
        return shoe_ID;
    }

    public void setShoe_ID(String shoe_ID) {
        this.shoe_ID = shoe_ID;
    }

    public String getModel_ID() {
        return model_ID;
    }

    public void setModel_ID(String model_ID) {
        this.model_ID = model_ID;
    }

    public String getVerification_serial_ID() {
        return verification_serial_ID;
    }

    public void setVerification_serial_ID(String verification_serial_ID) {
        this.verification_serial_ID = verification_serial_ID;
    }

    public String getUnit_color() {
        return unit_color;
    }

    public void setUnit_color(String unit_color) {
        this.unit_color = unit_color;
    }

    public String getManufacturing_location() {
        return manufacturing_location;
    }

    public void setManufacturing_location(String manufacturing_location) {
        this.manufacturing_location = manufacturing_location;
    }

    public Year getManufacturing_year() {
        return manufacturing_year;
    }

    public void setManufacturing_year(Year manufacturing_year
    ) {
        this.manufacturing_year = manufacturing_year;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public Date getLast_scan_date() {
        return last_scan_date;
    }

    public void setLast_scan_date(Date last_scan_date) {
        this.last_scan_date = last_scan_date;
    }


    public static boolean checkRFIDExists(String rfid_input_value) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");

            String sql = "SELECT COUNT(*) AS count FROM inventory_units WHERE verification_serial_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid_input_value);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
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

        return false; // Return false if no matching RFID found
    }

    public static String getShoeIdByRFID(String rfid_input_value) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");

            String sql = "SELECT shoe_ID FROM inventory_units WHERE verification_serial_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rfid_input_value);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("shoe_ID");
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

        return null; // Return null if no matching RFID found
    }
    
}
