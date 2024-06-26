package controller.utils_card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class activityCardController {

    @FXML
    private Label label1;

    // @FXML
    // Text verify_id, shoe_model, shoe_color, scan_date

    public void initialize() {
        // Initialization code if needed
    }

    public void setLabelText(String text) {
        label1.setText(text);
    }
}
