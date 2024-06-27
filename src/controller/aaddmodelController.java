package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class aaddmodelController {

    public String model_id_val;
    public String model_name_val;
    public String model_color_val;
    public Blob picture_model;

    @FXML
    TextField model_id_tf, model_name_tf, color_model_tf;

    @FXML
    Button dashboardButton, reportsButton, addModelButton, backButton, give_photo_btn, saveButton;

    @FXML
    TextField search_tf;

    @FXML
    ImageView modelImageView; // This is the ImageView to display the image

    private Image selectedImage;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void captureDetailsNewModel() {
        model_id_val = model_id_tf.getText();
        model_name_val = model_name_tf.getText();
        model_color_val = color_model_tf.getText();
    }

    @FXML
    public void handleUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        java.io.File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            selectedImage = new Image(file.toURI().toString());
            modelImageView.setImage(selectedImage);
        }
    }

    public void saveModelDetails(ActionEvent event) {
        captureDetailsNewModel();

        InputStream imageStream = imageToInputStream(selectedImage);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password")) {
            String sql = "INSERT INTO InventoryModel (modelID, shoeName, picture) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model_id_val);
            pstmt.setString(2, model_name_val);
            pstmt.setBlob(3, imageStream);

            pstmt.executeUpdate();
            System.out.println("Model details saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private InputStream imageToInputStream(Image image) {
        if (image == null) {
            return null;
        }
        try {
            java.awt.image.BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    public void goToAddModel(ActionEvent event) throws IOException {
        System.out.println("Add Model button clicked");
        changeScene(event, "/view/ainventoryaddunit.fxml");
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
