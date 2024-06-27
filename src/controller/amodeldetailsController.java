package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.InventoryModel;
import model.InventoryUnit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.Node;

public class amodeldetailsController {

    @FXML
    private TableView<InventoryUnit> unitTable;

    @FXML
    private TableView<InventoryModel> inventoryTable;

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

    @FXML
    private ImageView modelImg;

    @FXML
    private Label modelTitlePage;

    @FXML
    private Label shoeNamelbl;

    @FXML
    private Label shoeCountLbl;

    @FXML
    private Label scannedCountLbl;

    private ObservableList<InventoryUnit> unitList = FXCollections.observableArrayList();

    private String selectedModelID;
    private String selectedShoeName;

    @FXML
    public void initialize() {
        colShoeID.setCellValueFactory(new PropertyValueFactory<>("shoeID"));
        colVerificationSerialID.setCellValueFactory(new PropertyValueFactory<>("verificationSerialID"));
        colUnitColor.setCellValueFactory(new PropertyValueFactory<>("unitColor"));
        colManufactureYear.setCellValueFactory(new PropertyValueFactory<>("manufacturingYear"));
        colScans.setCellValueFactory(new PropertyValueFactory<>("numberOfScans"));

        unitTable.setRowFactory(tv -> {
            TableRow<InventoryUnit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    InventoryUnit rowData = row.getItem();
                    try {
                        goToDetails(rowData, event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    private void goToDetails(InventoryUnit inventoryUnit, MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ainventoryunitdetails.fxml"));
        Parent root = loader.load();

        // InventoryModel inventoryModel;
        // detailsController = loader.getController();
        // .initData(inventoryModel.getModelID(), inventoryUnit.getShoeID());

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        if (event != null) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }

    public void initData(String modelID, String shoeName) {
        this.selectedModelID = modelID;
        this.selectedShoeName = shoeName;
        updateDetails();
    }

    public void updateDetails() {
        if (selectedModelID != null && selectedShoeName != null) {
            modelTitlePage.setText("Details for Model ID: " + selectedModelID);
            shoeNamelbl.setText(selectedShoeName);
            loadImage(selectedModelID); // Load image for the model ID

            loadUnitData(selectedModelID);
            updateShoeCount(selectedModelID);
            updateScannedCount(selectedModelID);
        }
    }

    private void loadUnitData(String modelID) {
        unitList.clear();

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

    private void updateShoeCount(String modelID) {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT COUNT(*) FROM inventory_units WHERE model_ID = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, modelID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Total Shoes: " + count); // Debug statement
                // Update the label on the JavaFX Application Thread
                Platform.runLater(() -> shoeCountLbl.setText(String.valueOf(count)));
            } else {
                System.out.println("No results found for shoe count."); // Debug statement
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

    private void updateScannedCount(String modelID) {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT SUM(number_of_scans) FROM inventory_units WHERE model_ID = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, modelID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Total Scans: " + count); // Debug statement
                // Update the label on the JavaFX Application Thread
                Platform.runLater(() -> scannedCountLbl.setText(String.valueOf(count)));
            } else {
                System.out.println("No results found for scanned count."); // Debug statement
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

    private void loadImage(String modelID) {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT shoe_img FROM inventory_model WHERE model_ID = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, modelID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                byte[] imageBytes = resultSet.getBytes("shoe_img");
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                modelImg.setImage(image);
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