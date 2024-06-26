package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.InventoryUnit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class amodeldetailsController {

    @FXML
    private TableView<InventoryUnit> unitTable;

    @FXML
    private TableColumn<InventoryUnit, String> colShoeID;

    @FXML
    private TableColumn<InventoryUnit, String> colVerificationSerialID;

    @FXML
    private TableColumn<InventoryUnit, String> colUnitColor;

    @FXML
    private TableColumn<InventoryUnit, Integer> colManufactureYear;

    @FXML
    private TableColumn<InventoryUnit, Integer> colScans;

    private ObservableList<InventoryUnit> unitList = FXCollections.observableArrayList();

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(amodeldetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    @FXML
    public void initialize() {
        colShoeID.setCellValueFactory(new PropertyValueFactory<>("shoeID"));
        colVerificationSerialID.setCellValueFactory(new PropertyValueFactory<>("verificationSerialID"));
        colUnitColor.setCellValueFactory(new PropertyValueFactory<>("unitColor"));
        colManufactureYear.setCellValueFactory(new PropertyValueFactory<>("manufacturingYear"));
        colScans.setCellValueFactory(new PropertyValueFactory<>("numberOfScans"));

        // Load units for the selected model (implement logic to fetch units)
        // For example, if you have a method to set selected model ID:
        // String selectedModelID = getSelectedModelID();
        String selectedModelID = "your_logic_to_get_selected_model_id";

        loadUnitData(selectedModelID);
    }

    private void loadUnitData(String modelID) {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM inventory_units WHERE model_ID = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, modelID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String shoeID = resultSet.getString("shoe_ID");
                String verificationSerialID = resultSet.getString("verification_serial_ID");
                String unitColor = resultSet.getString("unit_color");
                int manufacturingYear = resultSet.getInt("manufacturing_year");
                int numberOfScans = resultSet.getInt("number_of_scans");

                InventoryUnit unit = new InventoryUnit(shoeID, verificationSerialID, unitColor, manufacturingYear,
                        numberOfScans);
                unitList.add(unit);
            }

            unitTable.setItems(unitList);

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

    public void goToDashboard(ActionEvent event) throws IOException {
        System.out.println("Dashboard button clicked");
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked");
        changeScene(event, "/view/areports.fxml");
    }

    public void addunit(ActionEvent event) throws IOException {
        System.out.println("Add Unit button clicked");
        changeScene(event, "/view/ainventoryaddunit.fxml");
    }

    public void editmodel(ActionEvent event) throws IOException {
        System.out.println("Edit Model button clicked");
        changeScene(event, "/view/ainventoryeditmodel.fxml");
    }

    public void backToInventory(ActionEvent event) throws IOException {
        System.out.println("Back button clicked");
        changeScene(event, "/view/ainventory.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
