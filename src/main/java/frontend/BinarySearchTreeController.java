package frontend;

import backend.BinarySearchTree;
import backend.BinarySearchTreeException;
import backend.Order;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class BinarySearchTreeController implements Initializable {

    BinarySearchTree tree = new BinarySearchTree();
    @FXML
    public TextField textFIn;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnSearch;
    @FXML
    public Label failureView;
    @FXML
    public ImageView imageView;
    @FXML
    public ComboBox transverseBox;
    @FXML
    public Label transverseResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transverseBox.setItems(FXCollections.observableList(Arrays.asList(Order.values())));
        transverseBox.getSelectionModel().select(Order.INORDER);

        transverseBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            List result = tree.traverse((Order) transverseBox.getValue());
            transverseResult.setText(result.toString());
        });
        btnAdd.setOnAction(e ->{
            try {
                System.out.println("hi");
                tree.addValue(textFIn.getText());
                System.out.println(tree.traverse(Order.INORDER));
            } catch (BinarySearchTreeException ex) {
                ex.printStackTrace();
            }
        });
        btnDelete.setOnAction(e ->{
            try {
                System.out.println("hi2");
                tree.delValue(textFIn.getText());
            } catch (BinarySearchTreeException ex) {
                ex.printStackTrace();
            }
        });
        btnSearch.setOnAction(e -> {
            System.out.println("hi3");
            tree.hasValue(textFIn.getText());

        });

    }
}