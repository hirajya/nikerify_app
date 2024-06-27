package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Table;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.report;

public class adashboardscansandreportsController {

    @FXML
    Button inventoryButton, reportsButton, reportspaneButton, scanneditemButton;

    @FXML
    Label NoofReports;

    @FXML
    Pane scanneditemsPane, reportsPane;

    @FXML
    TableView<report> reportsTable;

    @FXML
    TableColumn<report, Integer> report_id_column;

    @FXML
    TableColumn<report, Integer> user_id_column;

    @FXML
    TableColumn<report, Integer> verification_id_column;

    @FXML
    TableColumn<report, String> input_shoe_model_column;

    @FXML
    TableColumn<report, LocalDate> purchase_date_column;

    @FXML
    TableColumn<report, Integer> type_seller_column;

    @FXML
    TableColumn<report, String> report_comment_column;

    @FXML
    TableColumn<report, String> report_status_column;

    @FXML
    TableColumn<report, LocalDate> report_date_column;

    @FXML
    TableColumn<report, LocalTime> report_time_column;

    @FXML
    TextField search_tf;

    @FXML
    public void initialize() {
        // Initialization code if needed
        report_id_column.setCellValueFactory(new PropertyValueFactory<>("report_id"));
        user_id_column.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        verification_id_column.setCellValueFactory(new PropertyValueFactory<>("verification_id"));
        input_shoe_model_column.setCellValueFactory(new PropertyValueFactory<>("input_shoe_model"));
        purchase_date_column.setCellValueFactory(new PropertyValueFactory<>("purchase_date"));
        type_seller_column.setCellValueFactory(new PropertyValueFactory<>("type_seller"));
        report_comment_column.setCellValueFactory(new PropertyValueFactory<>("report_comment"));
        report_status_column.setCellValueFactory(new PropertyValueFactory<>("report_status"));
        report_date_column.setCellValueFactory(new PropertyValueFactory<>("report_date"));
        report_time_column.setCellValueFactory(new PropertyValueFactory<>("report_time"));

        try {
            loadDataFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reportsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { // Double-click to select
                report selectedReport = reportsTable.getSelectionModel().getSelectedItem();
                if (selectedReport != null) {
                    try {
                        goToReportDetail(event, selectedReport);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void goToInventory(ActionEvent event) throws IOException {
        System.out.println("Inventory button clicked");
        changeScene(event, "/view/ainventory.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Reports button clicked");
        changeScene(event, "/view/areports.fxml");
    }

    public void showReportsPane(ActionEvent event) {
        System.out.println("Reports pane button clicked");
        reportsPane.setVisible(true);
        scanneditemsPane.setVisible(false);
    }

    public void showscanneditemsPane(ActionEvent event) {
        System.out.println("Scanned Items pane button clicked");
        scanneditemsPane.setVisible(true);
        reportsPane.setVisible(false);
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void loadDataFromDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nikerify_db";
        String user = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT report_id, user_id, verification_id, input_shoe_model, purchase_date, type_seller, report_comment, report_status, scan_date, scan_time FROM report";
        ResultSet resultSet = statement.executeQuery(query);

        ObservableList<report> data = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int report_id = resultSet.getInt("report_id");
            int user_id = resultSet.getInt("user_id");
            int verification_id = resultSet.getInt("verification_id");
            String input_shoe_model = resultSet.getString("input_shoe_model");
            LocalDate purchase_date = resultSet.getDate("purchase_date").toLocalDate();
            int type_seller = resultSet.getInt("type_seller");
            String report_comment = resultSet.getString("report_comment");
            String report_status = resultSet.getString("report_status");
            LocalDate report_date = resultSet.getDate("scan_date").toLocalDate();
            LocalTime report_time = resultSet.getTime("scan_time").toLocalTime();
            
            data.add(new report(report_id, user_id, verification_id, input_shoe_model, purchase_date, type_seller, report_comment, report_status, report_date, report_time));
        }

        Platform.runLater(() -> {
            reportsTable.setItems(data);
            if (NoofReports != null) {
                NoofReports.setText(String.valueOf(data.size()));
            }
        });
        

        resultSet.close();
        statement.close();
        connection.close();
    }

    private void goToReportDetail(MouseEvent event, report selectedReport) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/areportdetails.fxml"));
        Parent root = loader.load();

        areportdetailsController controller = loader.getController();
        controller.initData(selectedReport);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
