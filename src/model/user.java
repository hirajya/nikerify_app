package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    private int user_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String contact_number;
    private String email;
    private String password;

    public user(int user_id, String first_name, String middle_name, String last_name, String contact_number, String email, String password) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.email = email;
        this.password = password;
    }

    public user(String first_name, String middle_name, String last_name, String contact_number, String email, String password) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.email = email;
        this.password = password;
    }

    public user(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public user() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static String getNameById(int userId) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nikerify_db"; // Replace with your database name
        String username = "root"; // Replace with your database username
        String dbPassword = ""; // Replace with your database password

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String name = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(url, username, dbPassword);

            // SQL query to retrieve name based on user_id
            String sql = "SELECT first_name, middle_name, last_name FROM user WHERE user_id = ?";

            // Prepare the statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if any rows were returned
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                name = firstName;
            } else {
                System.out.println("User not found with provided user ID.");
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

        return name;
    }

    public static int getUserIdByEmailAndPassword(String email, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nikerify_db"; // Replace with your database name
        String username = "root"; // Replace with your database username
        String dbPassword = ""; // Replace with your database password

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int userId = -1; // Default value if user is not found

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(url, username, dbPassword);

            // SQL query to retrieve user_id based on email and password
            String sql = "SELECT user_id FROM user WHERE email = ? AND password = ?";

            // Prepare the statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if any rows were returned
            if (rs.next()) {
                userId = rs.getInt("user_id");
            } else {
                System.out.println("User not found with provided credentials.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving user id from the database.", e);
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

        return userId;
    }

    public void addUser() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nikerify_db"; // Replace with your database name
        String username = "root"; // Replace with your database username
        String password = ""; // Replace with your database password

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(url, username, password);

            // SQL insert query
            String sql = "INSERT INTO user (first_name, middle_name, last_name, contact_number, email, password) VALUES (?, ?, ?, ?, ?, ?)";

            // Prepare the statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, this.first_name);
            pstmt.setString(2, this.middle_name);
            pstmt.setString(3, this.last_name);
            pstmt.setString(4, this.contact_number);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.password);

            // Execute the insert operation
            pstmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error adding user to the database.", e);
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
