package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class location {
    private int store_location_id;
    private String street_number;
    private String block_number;
    private String barangay;
    private String city;

    public location(int store_location_id, String street_number, String block_number, String barangay, String city) {
        this.store_location_id = store_location_id;
        this.street_number = street_number;
        this.block_number = block_number;
        this.barangay = barangay;
        this.city = city;
    }

    public location(String street_number, String block_number, String barangay, String city) {
        this.street_number = street_number;
        this.block_number = block_number;
        this.barangay = barangay;
        this.city = city;
    }

    public location() {
    }

    public int getStore_location_id() {
        return store_location_id;
    }

    public void setStore_location_id(int store_location_id) {
        this.store_location_id = store_location_id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getBlock_number() {
        return block_number;
    }

    public void setBlock_number(String block_number) {
        this.block_number = block_number;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int saveLocation() throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");

            // SQL statement to insert location data and retrieve generated keys
            String sql = "INSERT INTO location (street_number, block_number, barangay, city) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, street_number);
            stmt.setString(2, block_number);
            stmt.setString(3, barangay);
            stmt.setString(4, city);

            // Execute the insert statement
            int affectedRows = stmt.executeUpdate();

            // Check if any rows were affected
            if (affectedRows > 0) {
                // Retrieve the generated keys
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Retrieve the generated store_location_id
                    this.store_location_id = generatedId; // Set the store_location_id in the current instance
                }
            } else {
                throw new SQLException("Failed to insert location data, no rows affected.");
            }
        } finally {
            // Close resources in reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return generatedId; // Return the generated store_location_id
    }
}
