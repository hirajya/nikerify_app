package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException; 

public class reportHistoryController {

    @FXML
    ScrollPane scrollPaneReport;

    @FXML
    Button backBtn, goToHistoryScanBtn;
    
    @FXML
    public void initialize() {
        try {
            loadReportCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to Homepage clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToReportsDetail(ActionEvent event) throws IOException {
        System.out.println("View details of report clicked");
        changeScene(event, "/view/reportDetailsOnline.fxml");
    }

    public void goToHistoryScan(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        rfidDetailsController.isRFIDVerified = true;
        changeScene(event, "/view/scanhistory.fxml");
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void loadReportCards() throws IOException {
        VBox content = new VBox();
        content.setSpacing(10);

        for (int i = 0; i < 10; i++) {  // Example: Load 10 report cards
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/utils/scanHistoryCard.fxml"));
            Parent reportCard = loader.load();

            // Optionally, set data for each report card
            controller.utils_card.reportCardController controller = loader.getController();
            controller.setLabelText("Report Card " + (i + 1));

            content.getChildren().add(reportCard);
        }

        scrollPaneReport.setContent(content);
    }
}
