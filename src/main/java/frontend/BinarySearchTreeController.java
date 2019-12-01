package frontend; /**
 * JavaFx Controller for Binary Search Tree
 *
 * @author Falko Tschernay
 * @author Joel Pitzler
 * @version 1.0, 10.11.2019
 */



import backend.BinarySearchTree;
import backend.BinarySearchTreeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BinarySearchTreeController implements Initializable {
    @FXML
    private TextField tf_insert_value;
    @FXML
    private TextField tf_delete_value;
    @FXML
    private TextField tf_search_value;
    @FXML
    private Label lb_searchAnswer;
    @FXML
    private Button b_rbtree;
    @FXML
    private Button b_go_insert;
    @FXML
    private Button b_go_delete;
    @FXML
    private Button b_go_search;
    @FXML
    private Button b_depth;
    @FXML
    private ImageView img_tree_display;
    @FXML
    private ChoiceBox cb_datatype_select;
    @FXML
    private VBox vb;

    private static String integer = "Integer";
    private static String string = "String";

    /*Creates an oblect of the BinarySearchTree class to provide functionality.*/
    private BinarySearchTree treeInteger = new BinarySearchTree();
    private BinarySearchTree treeString = new BinarySearchTree();
    private Image imgTmp;

    /*Reads out File input for the ImageView to display the treeInteger*/
    private Image setImg(File src) {
        try {
            imgTmp = new Image(new FileInputStream(src));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imgTmp;
    }


    /**
     * Initialize the functionality of the GUI
     *
     * @param url            to create Url objects.
     * @param resourceBundle to create a localization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    /**
     * Displays an Error message.
     *
     * @param message The error message to display.
     */
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Starts the functionality of the GUI.
     */
    private void refresh() {
        b_depth.setOnAction(e -> {
            if (cb_datatype_select.getValue() == integer) {
                lb_searchAnswer.setText("Depth of tree is:" + treeInteger.getDepth());
            } else {
                lb_searchAnswer.setText("Depth of tree is:" + treeString.getDepth());
            }
        });

        b_go_insert.setOnAction(e -> {
            if (cb_datatype_select.getValue() == integer) {
                try {
                    try {
                        treeInteger.addValue(Integer.parseInt(tf_insert_value.getText()));
                    } catch (IllegalArgumentException em) {
                        showErrorMessage(em.getMessage());
                    }
                } catch (BinarySearchTreeException ex) {
                    showErrorMessage(ex.getMessage());
                }
                try {
                    img_tree_display.setImage(setImg(treeInteger.toGraphiz()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    treeString.addValue(tf_insert_value.getText());
                } catch (BinarySearchTreeException ex) {
                    showErrorMessage(ex.getMessage());
                }
                try {
                    img_tree_display.setImage(setImg(treeString.toGraphiz()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


        });
        b_go_delete.setOnAction(e -> {
            if (cb_datatype_select.getValue() == integer) {
                try {
                    try {
                        treeInteger.delValue(Integer.parseInt(tf_delete_value.getText()));
                    } catch (IllegalArgumentException ed) {
                        showErrorMessage(ed.getMessage());
                    }
                } catch (BinarySearchTreeException ef) {
                    showErrorMessage(ef.getMessage());
                }
                try {
                    img_tree_display.setImage(setImg(treeInteger.toGraphiz()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    treeString.delValue(tf_delete_value.getText());
                } catch (BinarySearchTreeException ef) {
                    showErrorMessage(ef.getMessage());
                }
                try {
                    img_tree_display.setImage(setImg(treeString.toGraphiz()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        b_go_search.setOnAction(e -> {
            if (cb_datatype_select.getValue() == integer) {
                try {
                    if (treeInteger.hasValue(Integer.parseInt(tf_search_value.getText()))) {
                        lb_searchAnswer.setText("Tree has value:" + tf_search_value.getText());
                    } else {
                        lb_searchAnswer.setText("Tree has not value" + tf_search_value.getText());
                    }
                } catch (IllegalArgumentException ie) {
                    showErrorMessage(ie.getMessage());
                }
            } else {
                if (treeString.hasValue(tf_search_value.getText())) {
                    lb_searchAnswer.setText("Tree has value:" + tf_search_value.getText());
                } else {
                    lb_searchAnswer.setText("Tree has not value:" + tf_search_value.getText());
                }
            }
        });
        ObservableList<String> dataTypeList = FXCollections.observableArrayList(string, integer);
        cb_datatype_select.setItems(dataTypeList);
        cb_datatype_select.setValue(integer);
    }


}


