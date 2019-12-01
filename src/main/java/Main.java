import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // load fxml
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Binary search tree");
        showContent();
    }


    public void showContent() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("frontend/Gui2.fxml"));
            Parent personOverview = loader.load();
            // Set person overview into the center of root layout.
            primaryStage.setScene(new Scene(personOverview));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}