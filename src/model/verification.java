package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class verification {
    private int verification_id;
    private int user_id;
    private LocalDate verification_date;
    private LocalTime verification_time;
    private String shoe_id;
    private boolean authenticity_result;
    private String serial_number;

    public verification(int verification_id, int user_id, LocalDate verification_date, LocalTime verification_time, String shoe_id, boolean authenticity_result, String serial_number) {
        this.verification_id = verification_id;
        this.user_id = user_id;
        this.verification_date = verification_date;
        this.verification_time = verification_time;
        this.shoe_id = shoe_id;
        this.authenticity_result = authenticity_result;
        this.serial_number = serial_number;
    }

    public verification(int user_id, LocalDate verification_date, LocalTime verification_time, String shoe_id, boolean authenticity_result, String serial_number) {
        this.user_id = user_id;
        this.verification_date = verification_date;
        this.verification_time = verification_time;
        this.shoe_id = shoe_id;
        this.authenticity_result = authenticity_result;
        this.serial_number = serial_number;
    }

    public verification() {
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getVerification_date() {
        return verification_date;
    }

    public void setVerification_date(LocalDate verification_date) {
        this.verification_date = verification_date;
    }

    public LocalTime getVerification_time() {
        return verification_time;
    }

    public void setVerification_time(LocalTime verification_time) {
        this.verification_time = verification_time;
    }

    public String getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(String shoe_id) {
        this.shoe_id = shoe_id;
    }

    public boolean isAuthenticity_result() {
        return authenticity_result;
    }

    public void setAuthenticity_result(boolean authenticity_result) {
        this.authenticity_result = authenticity_result;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }



    public int saveVerification() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");

            String sql = "INSERT INTO verification (user_id, verification_date, verification_time, shoe_id, authenticity_result, serial_number) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, user_id);
            pstmt.setDate(2, java.sql.Date.valueOf(verification_date));
            pstmt.setTime(3, java.sql.Time.valueOf(verification_time));
            pstmt.setString(4, shoe_id);
            pstmt.setBoolean(5, authenticity_result);
            pstmt.setString(6, serial_number);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Retrieve the generated verification_id
                    this.verification_id = generatedId; // Set the verification_id in the current instance
                }
            } else {
                throw new SQLException("Failed to insert verification record, no rows affected.");
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

        return generatedId; // Return the generated verification_id
    }

    
}
