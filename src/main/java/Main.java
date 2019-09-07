import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // load fxml
        Parent root = FXMLLoader.load(getClass().getResource("frontend/Gui.fxml"));
        // set tilte of the windwo
        primaryStage.setTitle("Binary search tree");
        // create new scene
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}