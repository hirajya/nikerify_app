package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class typeseller {
    private int ts_id;
    private int store_location_id;
    private String store_name;
    private String store_contact_number;
    private String store_link;
    private int report_id;
    private int user_id;
    private String receipt_picture;
    private String product_picture;

    // Constructors
    public typeseller(int ts_id, int store_location_id, String store_name, String store_contact_number, String store_link, int user_id) {
        this.ts_id = ts_id;
        this.store_location_id = store_location_id;
        this.store_name = store_name;
        this.store_contact_number = store_contact_number;
        this.store_link = store_link;
        this.user_id = user_id;
 
    }

    public typeseller(int store_location_id, String store_name, String store_contact_number, String store_link, int user_id) {
        this.store_location_id = store_location_id;
        this.store_name = store_name;
        this.store_contact_number = store_contact_number;
        this.store_link = store_link;
        this.user_id = user_id;

    }

    public typeseller() {
    }

    // Getters and setters
    public int getTs_id() {
        return ts_id;
    }

    public void setTs_id(int ts_id) {
        this.ts_id = ts_id;
    }

    public int getStore_location_id() {
        return store_location_id;
    }

    public void setStore_location_id(int store_location_id) {
        this.store_location_id = store_location_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_contact_number() {
        return store_contact_number;
    }

    public void setStore_contact_number(String store_contact_number) {
        this.store_contact_number = store_contact_number;
    }

    public String getStore_link() {
        return store_link;
    }

    public void setStore_link(String store_link) {
        this.store_link = store_link;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    

    public int saveTypeSeller() throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "INSERT INTO type_seller_table (store_location_id, store_name, store_contact_number, store_link, user_id) VALUES (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, store_location_id);
            pstmt.setString(2, store_name);
            pstmt.setString(3, store_contact_number);
            pstmt.setString(4, store_link);
            pstmt.setInt(5, user_id);
  

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Retrieve the generated ts_id
                    this.ts_id = generatedId; // Set the ts_id in the current instance
                }
            } else {
                throw new SQLException("Failed to insert type seller data, no rows affected.");
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

        return generatedId; // Return the generated ts_id
    }
}
