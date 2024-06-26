package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inventory_units {

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
