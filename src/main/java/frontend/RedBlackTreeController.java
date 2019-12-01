package frontend;

import backend.BinarySearchTreeException;
import backend.Order;
import backend.RedBlackTree;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RedBlackTreeController implements Initializable {
    @FXML
    public Label resultSearch;
    @FXML
    public Button btnDepth;
    @FXML
    public TextField textFInAdd;
    @FXML
    public TextField textFInDelete;
    @FXML
    public TextField textFInSearch;
    @FXML
    public Label depth;
    RedBlackTree tree = new RedBlackTree();
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnSearch;
    @FXML
    public ImageView imageView;
    @FXML
    public ComboBox transverseBox;
    @FXML
    public Label transverseResult;

    private Image imgTmp;

    private Image setImg(File src) {
        try {
            imgTmp = new Image(new FileInputStream(src));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imgTmp;
    }

    private void errorMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transverseBox.setItems(FXCollections.observableList(Arrays.asList(Order.values())));
        transverseBox.getSelectionModel().select(Order.INORDER);
        transverseBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            List result = tree.traverse((Order) transverseBox.getValue());
            transverseResult.setText(result.toString());
            this.imageView.setImage(setImg(tree.toGraphiz()));
        });
        btnAdd.setOnAction(e -> {
            try {
                tree.addValue(Integer.parseInt(textFInAdd.getText()));
                System.out.println(tree.traverse(Order.INORDER));
                textFInAdd.setText("");
                this.imageView.setImage(setImg(tree.toGraphiz()));
            } catch (BinarySearchTreeException | NumberFormatException ex) {
                errorMessage(ex.getMessage());
            }
        });
        btnDelete.setOnAction(e -> {
            try {
                tree.delValue(Integer.parseInt(textFInDelete.getText()));
                textFInDelete.setText("");
                this.imageView.setImage(setImg(tree.toGraphiz()));
            } catch (BinarySearchTreeException | NumberFormatException ex) {
                errorMessage(ex.getMessage());
            }
        });
        btnSearch.setOnAction(e -> {
            try {
                if (tree.hasValue(Integer.parseInt(textFInSearch.getText()))) {
                    resultSearch.setText("200");
                    resultSearch.getStyleClass().add("text-success");
                } else {
                    resultSearch.setText("404");
                    resultSearch.getStyleClass().add("text-danger");
                }
                textFInSearch.setText("");
                this.imageView.setImage(setImg(tree.toGraphiz()));
            } catch (NumberFormatException ex) {
                errorMessage(ex.getMessage());
            } catch (Exception ex) {
                errorMessage(ex.getMessage());
            }

        });
        btnDepth.setOnAction(e -> {
            depth.setText(String.valueOf(tree.getDepth()));
        });
        btnDepth.setOnAction(e -> {
            depth.setText(String.valueOf(tree.getDepth()));
        });
        textFInSearch.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnSearch.fire();
            }
        });
        textFInAdd.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnAdd.fire();
            }
        });
        textFInDelete.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnDelete.fire();
            }
        });
        this.imageView.setImage(setImg(tree.toGraphiz()));
    }
}