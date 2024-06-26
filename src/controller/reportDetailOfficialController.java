package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.verification;

public class reportDetailOfficialController {
    @FXML
    Button backToHistoryReportsBtn;

    public static String status_val1;
    public static int report_id1;
    public static int verification_id1;
    public static String authenticity_result1;
    public static String report_date1;

    public static String shoe_model1;
    public static String purchase_date1;
    public static String typeSeller_kind1;

    public static String store_location_full;
    public static Blob product_photo1;
    public static Blob receipt_photo1;

    @FXML
    ImageView receipt_pht, product_pht;

    @FXML
    Text status_txt, report_id_txt, verification_id_txt, authenticity_result_txt, report_date_txt, shoe_model_txt, purchase_date_txt, typeSeller_kind_txt, store_location_txt;

    @FXML
    public void initialize() {
        System.out.println(status_val1);

        
        status_txt.setText(status_val1);
        report_id_txt.setText(String.valueOf(report_id1));
        verification_id_txt.setText(String.valueOf(verification_id1));
        authenticity_result_txt.setText(authenticity_result1);
        report_date_txt.setText(report_date1);
        shoe_model_txt.setText(shoe_model1);
        purchase_date_txt.setText(purchase_date1);
        typeSeller_kind_txt.setText(typeSeller_kind1);
        store_location_txt.setText(store_location_full);
        initializeImages();


    }

    public void initializeImages() {
        // Set the ImageView to display the image
        receipt_pht.setImage(getImageViewFromBlob(receipt_photo1).getImage());
        product_pht.setImage(getImageViewFromBlob(product_photo1).getImage());
    }

    public static ImageView getImageViewFromBlob(Blob blob) {
        try {
            // Convert Blob to byte array
            byte[] blobBytes = blob.getBytes(1, (int) blob.length());
            InputStream inputStream = new ByteArrayInputStream(blobBytes);

            // Create Image object from byte array
            Image image = new Image(inputStream);

            // Create ImageView and set Image
            ImageView imageView = new ImageView(image);
            
            // Optionally, set properties on the ImageView
            imageView.setFitWidth(300);
            imageView.setFitHeight(95);
            imageView.setPreserveRatio(true);

            return imageView;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or handle the exception as appropriate
        }
    }


    


    public void backToRepHist(ActionEvent event) throws IOException {
        System.out.println("Back to reports history clicked");
        changeScene(event, "/view/scanhistoryreports.fxml");
    }


    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
