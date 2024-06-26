package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InventoryModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ainventoryController {

    @FXML
    private TableView<InventoryModel> inventoryTable;

    @FXML
    private TableColumn<InventoryModel, ImageView> colPicture;

    @FXML
    private TableColumn<InventoryModel, String> colModelID;

    @FXML
    private TableColumn<InventoryModel, String> colShoeName;

    @FXML
    private Button dashboardButton, reportsButton, addmodelButton;

    @FXML
    private TextField search_tf;

    private ObservableList<InventoryModel> inventoryList = FXCollections.observableArrayList();

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikerify_db", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ainventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void initialize() {
        colPicture.setCellValueFactory(new PropertyValueFactory<>("picture"));
        colModelID.setCellValueFactory(new PropertyValueFactory<>("modelID"));
        colShoeName.setCellValueFactory(new PropertyValueFactory<>("shoeName"));

        loadInventoryData();

        inventoryTable.setRowFactory(tv -> {
            TableRow<InventoryModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    InventoryModel rowData = row.getItem();
                    try {
                        goToDetails(rowData, event); // Pass the event parameter here
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private void loadInventoryData() {
        Connection con = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM inventory_model";
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String modelID = resultSet.getString("model_ID");
                byte[] imageBytes = resultSet.getBytes("shoe_img");
                String shoeName = resultSet.getString("shoe_name");

                // Convert byte[] to ImageView
                ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(imageBytes)));
                imageView.setFitHeight(80);
                imageView.setFitWidth(80);

                InventoryModel inventoryModel = new InventoryModel(imageView, modelID, shoeName);
                inventoryList.add(inventoryModel);
            }

            inventoryTable.setItems(inventoryList);

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

    private void goToDetails(InventoryModel inventoryModel, MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/amodeldetails.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        amodeldetailsController detailsController = loader.getController();

        // Optionally pass data to the details controller
        // detailsController.setData(...);

        // Create a new stage for the details view
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Close the current stage if needed
        if (event != null) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }

    @FXML
    public void goToDashboard(ActionEvent event) throws IOException {
        changeScene(event, "/view/adashboardscansandreports.fxml");
    }

    @FXML
    public void goToReports(ActionEvent event) throws IOException {
        changeScene(event, "/view/areports.fxml");
    }

    @FXML
    public void addModel(ActionEvent event) throws IOException {
        changeScene(event, "/view/ainventoryaddmodel.fxml");
    }

    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}