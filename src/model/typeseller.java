package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public typeseller(int ts_id, int store_location_id, String store_name, String store_contact_number, String store_link, int report_id, int user_id, String receipt_picture, String product_picture) {
        this.ts_id = ts_id;
        this.store_location_id = store_location_id;
        this.store_name = store_name;
        this.store_contact_number = store_contact_number;
        this.store_link = store_link;
        this.report_id = report_id;
        this.user_id = user_id;
        this.receipt_picture = receipt_picture;
        this.product_picture = product_picture;
    }

    public typeseller(int store_location_id, String store_name, String store_contact_number, String store_link, int report_id, int user_id, String receipt_picture, String product_picture) {
        this.store_location_id = store_location_id;
        this.store_name = store_name;
        this.store_contact_number = store_contact_number;
        this.store_link = store_link;
        this.report_id = report_id;
        this.user_id = user_id;
        this.receipt_picture = receipt_picture;
        this.product_picture = product_picture;
    }

    public typeseller() {
    }

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

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReceipt_picture() {
        return receipt_picture;
    }

    public void setReceipt_picture(String receipt_picture) {
        this.receipt_picture = receipt_picture;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }

    public void saveTypeSeller() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
            String sql = "INSERT INTO type_seller_table (store_location_id, store_name, store_contact_number, store_link, report_id, user_id, receipt_picture, product_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, store_location_id); // Assuming store_location_id is set before calling saveTypeSeller()
            pstmt.setString(2, store_name); // Assuming store_name is set before calling saveTypeSeller()
            pstmt.setString(3, store_contact_number); // Assuming store_contact_number is set before calling saveTypeSeller()
            pstmt.setString(4, store_link); // Assuming store_link is set before calling saveTypeSeller()
            pstmt.setInt(5, report_id); // Assuming report_id is set before calling saveTypeSeller()
            pstmt.setInt(6, user_id); // Assuming user_id is set before calling saveTypeSeller()
            pstmt.setString(7, receipt_picture); // Assuming receipt_picture is set before calling saveTypeSeller()
            pstmt.setString(8, product_picture); // Assuming product_picture is set before calling saveTypeSeller()

            pstmt.executeUpdate();

        } finally {
            // Close resources
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
