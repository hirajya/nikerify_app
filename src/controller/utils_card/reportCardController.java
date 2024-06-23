package controller.utils_card;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class reportCardController {

    @FXML
    private Label verify_id;

    @FXML
    private Button seeDetailsBnt;

    @FXML
    private ImageView image_product;

    @FXML
    Text shoe_model_txt, scan_date, scan_time, validity_text;

    public void initialize() {
        // Initialization code if needed
    }

    public void goToItemDetails(ActionEvent event) throws IOException {
        System.out.println("Proceed to see details");
        changeScene(event, "/view/reportDetailsOnline.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setProductImage(Image image) {
        image_product.setImage(image);
    }

    public void setProductImage(String p_image) {
        image_product.setImage(new Image(getClass().getResourceAsStream(p_image)));
    }

    public void setVerify_Id(String v_id) {
        verify_id.setText(v_id);
    }

    public void setShoeModel(String s_model) {
        shoe_model_txt.setText(s_model);
    }

    public void setScanDate(String s_date) {
        scan_date.setText(s_date);
    }

    public void setScanTime(String s_time) {
        scan_time.setText(s_time);
    }

    public void setValidityText(String v_text) {
        validity_text.setText(v_text);
    }
}
