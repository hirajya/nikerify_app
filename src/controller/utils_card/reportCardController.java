package controller.utils_card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class reportCardController {

    @FXML
    private Label verify_id;

    @FXML
    Text shoe_model_txt, scan_date, scan_time, validity_text;

    // @FXML
    // ImageView validity_symbol;

    public void initialize() {
        // Initialization code if needed
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

    // public void setValiditySymbol(String v_symbol) {
    //     validity_symbol.setImage(new Image(getClass().getResourceAsStream(v_symbol)));
    // }

}
