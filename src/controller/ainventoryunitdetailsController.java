package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InventoryUnit;
import javafx.scene.image.ImageView;

public class ainventoryunitdetailsController {

    @FXML
    Button dashboardButton, reportsButton, editButton, deactivateButton, backButton;

    @FXML
    TextField search_tf;

    @FXML
    private ImageView unitImage;

    @FXML
    private Label unitModelID, unitShoeID, unitSerialID, unitColor, unitManufactureLocation, unitManufactureYear,
            unitBNumber, unitScans, unitLastScan;

    @FXML
    private TableView<InventoryUnit> unitTable;

    String serialID;
    String modelID;
    String location;
    int batch;
    String color;
    int manufactureYear;
    int scans;
    String lastScanDate;

    public void initData(String modelID, String shoeID) {
        loadUnitDetails(modelID, shoeID);
        unitModelID.setText(modelID);
        unitShoeID.setText(shoeID);
        unitSerialID.setText(serialID);
        unitScans.setText(String.valueOf(scans));
        unitColor.setText(color);
        unitManufactureYear.setText(String.valueOf(manufactureYear));
        unitManufactureLocation.setText(location);
        unitBNumber.setText(String.valueOf(batch));
        unitLastScan.setText(lastScanDate);
    }

    private void loadUnitDetails(String modelID, String shoeID) {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM inventory_units WHERE model_ID = ? AND shoe_ID = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, modelID);
            statement.setString(2, shoeID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                serialID = resultSet.getString("verification_serial_ID");
                color = resultSet.getString("unit_color");
                manufactureYear = resultSet.getInt("manufacturing_year");
                scans = resultSet.getInt("number_of_scans");
                location = resultSet.getString("manufacturing_location");
                batch = resultSet.getInt("batch_number");
                lastScanDate = resultSet.getString("last_scan_date");

                unitModelID.setText(modelID);
                unitShoeID.setText(shoeID);
                unitSerialID.setText(serialID);
                unitScans.setText(String.valueOf(scans));
                unitColor.setText(color);
                unitManufactureYear.setText(String.valueOf(manufactureYear));
                unitManufactureLocation.setText(location);
                unitBNumber.setText(String.valueOf(batch));
                unitLastScan.setText(lastScanDate);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    @FXML
    public void initialize() {

    }

    public void goToDashboard(ActionEvent event) throws IOException {
        System.out.println("Dashboard button clicked");
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked");
        changeScene(event, "/view/areports.fxml");
    }

    public void goToEditUnit(ActionEvent event) throws IOException {
        System.out.println("Update Information button clicked");
        changeScene(event, "/view/ainventoryeditunit.fxml");
    }

    public void backToModelInformation(ActionEvent event) throws IOException {
        System.out.println("Back button clicked");
        changeScene(event, "/view/ainventory.fxml");
    }

    public void goToDeactivateRFID(ActionEvent event) throws IOException {
        System.out.println("deactivate");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}