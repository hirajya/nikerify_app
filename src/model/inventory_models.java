package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inventory_models {

    public static String getShoeModelByModelId(String model_id) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nikerify_db"; // Replace with your database name
        String username = "root"; // Replace with your database username
        String dbPassword = ""; // Replace with your database password

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String shoe_name_value = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(url, username, dbPassword);

            // SQL query to retrieve name based on user_id
            String sql = "SELECT shoe_name FROM inventory_model WHERE model_ID = ?";

            // Prepare the statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model_id);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if any rows were returned
            if (rs.next()) {
                shoe_name_value = rs.getString("shoe_name");
            } else {
                System.out.println("Userzz.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving user name from the database.", e);
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

        return shoe_name_value;
    }
    
}