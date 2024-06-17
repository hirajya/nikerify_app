package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.report;
import model.typeseller;
import model.location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class supportDocumentsController {

    static String input_verify_id1 = null;
    static String input_shoe_model1 = null;
    static String selectedTypeSeller1 = null;
    static String storeName1 = null;
    static String storeContactNumber1 = null;
    static LocalDate purchaseDate1 = null;

    static String location_street_number1 = null;
    static String location_block_number1 = null;
    static String location_barangay1 = null;
    static String location_city1 = null;

    static int accUserId1;

    private File productPhotoFile;
    private File receiptPhotoFile;

    @FXML
    Button backBtn, nextButton, product_photo_btn, receipt_photo_btn;

    @FXML
    public void initialize() {
        System.out.println(input_verify_id1);
        System.out.println(input_shoe_model1);
        System.out.println(selectedTypeSeller1);
        System.out.println(storeName1);
        System.out.println(storeContactNumber1);
        System.out.println(purchaseDate1);
        System.out.println(location_street_number1);
        System.out.println(location_block_number1);
        System.out.println(location_barangay1);
        System.out.println(location_city1);
    }

    public void backToSubmit(ActionEvent event) throws IOException {
        System.out.println("Back to Submit Report clicked");
        changeScene(event, "/view/submitReport.fxml");
    }

    public void goToNext(ActionEvent event) throws IOException {
        System.out.println("Next button clicked");

        // Save location data first
        int storeLocationId = saveLocation();

        // Save report data and photos to the database
        saveDataAndPhotos(storeLocationId);

        // Navigate to userInfoReport.fxml
        changeScene(event, "/view/userInfoReport.fxml");
    }

    public int saveLocation() {
        try {
            // Create a new location object with the provided details
            location loc = new location(location_street_number1, location_block_number1, location_barangay1, location_city1);

            // Save location data to database and get the generated store_location_id
            int storeLocationId = loc.saveLocation();

            System.out.println("Location saved successfully with ID: " + storeLocationId);
            return storeLocationId;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save location: " + e.getMessage());
            return -1; // or handle error as needed
        }
    }

    public void saveDataAndPhotos(int storeLocationId) {
        try {
            // Save product and receipt photos to database
            if (productPhotoFile != null) {
                savePhotoToDatabase(input_verify_id1, productPhotoFile, "product_photo");
            }
            if (receiptPhotoFile != null) {
                savePhotoToDatabase(input_verify_id1, receiptPhotoFile, "receipt_photo");
            }

            // Save report data, including type_seller with storeLocationId
            saveData(storeLocationId);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data and photos: " + e.getMessage());
        }
    }

    public void saveData(int storeLocationId) {
        try {
            // Insert report data into report table
            int reportId = insertReport(storeLocationId);
            // Insert type_seller data into typeseller table
            insertTypeSeller(reportId, storeLocationId);

            System.out.println("Data saved successfully");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private int insertReport(int storeLocationId) throws SQLException, IOException {
        // Assuming input_shoe_model1 and purchaseDate1 are non-null (validate before calling this method)
        report report = new report();
        report.setVerification_id(Integer.parseInt(input_verify_id1));
        report.setInput_shoe_model(input_shoe_model1);
        report.setPurchase_date(purchaseDate1.toString());
        report.setType_seller(selectedTypeSeller1); // Here, selectedTypeSeller1 should be the ts_id

        // Save report data to database and get the generated report_id
        return report.saveReport();
    }

    private void insertTypeSeller(int reportId, int storeLocationId) throws SQLException, IOException {
        // Assuming storeName1, storeContactNumber1, etc., are non-null (validate before calling this method)
        typeseller typeSeller = new typeseller();
        typeSeller.setReport_id(reportId);
        typeSeller.setUser_id(accUserId1);
        typeSeller.setStore_name(storeName1);
        typeSeller.setStore_contact_number(storeContactNumber1);
        // Set the store_location_id from the saved location data
        typeSeller.setStore_location_id(storeLocationId);
    
        // Save type_seller data to database
        typeSeller.saveTypeSeller();
    }

    private void savePhotoToDatabase(String verification_id, File photoFile, String photoType) throws SQLException, IOException {
        try (FileInputStream fis = new FileInputStream(photoFile)) {
            byte[] imageData = new byte[(int) photoFile.length()];
            fis.read(imageData);

            // Save the photo to the database
            report report = new report();
            report.savePhotoToDatabase(verification_id, imageData, photoType);

            System.out.println(photoType + " uploaded successfully!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    public void uploadProductPhoto(ActionEvent event) {
        productPhotoFile = uploadPhoto("product_photo");
    }

    @FXML
    public void uploadReceiptPhoto(ActionEvent event) {
        receiptPhotoFile = uploadPhoto("receipt_photo");
    }

    private File uploadPhoto(String photoType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
    
        if (selectedFile != null) {
            // Generate a unique file name for saving locally
            String uniqueFileName = UUID.randomUUID().toString() + "_" + selectedFile.getName();
            File directory = new File("temp");
            if (!directory.exists()) {
                directory.mkdirs(); // Ensure temp directory exists
            }
            File destinationFile = new File(directory, uniqueFileName); // Save in a temp directory
    
            try (FileInputStream fis = new FileInputStream(selectedFile);
                 FileOutputStream fos = new FileOutputStream(destinationFile)) {
    
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
    
                System.out.println(photoType + " saved locally: " + destinationFile.getAbsolutePath());
                return destinationFile;
    
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to upload " + photoType + ".");
            }
        }
        return null;
    }
    

    private void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
