import backend.BinarySearchTree;
import frontend.BinarySearchTreeController;
import frontend.RedBlackTreeController;
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
    private RedBlackTreeController rbcontroller = new RedBlackTreeController();
    private BinarySearchTreeController con = new BinarySearchTreeController();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Binary search tree");
        showContent();
    }


    public void showContent() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(con);
            //loader.setController(rbcontroller); // use to see a RedBlackTree
            loader.setLocation(Main.class.getResource("frontend/Gui2.fxml"));
            Parent personOverview = loader.load();
            primaryStage.setScene(new Scene(personOverview));
            primaryStage.getScene().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}