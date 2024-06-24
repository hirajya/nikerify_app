package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class reportDetailPhysicalController {
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
    public static String store_name1;

    @FXML
    Text status_txt, report_id_txt, verification_id_txt, authenticity_result_txt, report_date_txt, shoe_model_txt, purchase_date_txt, typeSeller_kind_txt, store_location_txt, store_name_txt;

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
        store_name_txt.setText(store_name1);
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
