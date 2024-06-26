package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException; 
import javafx.scene.control.ScrollPane;

public class activityHistoryController {

    @FXML
    private ScrollPane scrollPaneReport;

    @FXML
    private Button backBtn, reportsButton;

    static int accUserId;

    @FXML
    public void initialize() {
        try {
            loadReportCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToHome(ActionEvent event) throws IOException {
        System.out.println("Back to Submit Report clicked");
        changeScene(event, "/view/homepage.fxml");
    }

    public void goToReports(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        changeScene(event, "/view/scanhistoryreports.fxml");
    }

    public void goToItemDetails(ActionEvent event) throws IOException {
        System.out.println("Proceed submit");
        rfidDetailsController.isRFIDVerified = true;
        changeScene(event, "/view/rfidAutheticDetails.fxml");
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
        content.setSpacing(10);  // Set spacing between each activity card

        for (int i = 0; i < 10; i++) {  // Example: Load 10 activity cards
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/utils/scanActivityCard.fxml"));
            Parent activityCard = loader.load();

            // Ensure that scanActivityCard.fxml is associated with activityCardController
            controller.utils_card.activityCardController controller = loader.getController();
            controller.setLabelText("Authentic Check " + (i + 1));

            content.getChildren().add(activityCard);
        }

        scrollPaneReport.setContent(content);
    }
}
