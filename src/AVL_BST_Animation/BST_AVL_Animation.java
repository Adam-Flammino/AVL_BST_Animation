package AVL_BST_Animation;

/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Assignment Name: BST_AVL_Animation
 * @Date: 4/29/17
 * @Description: Modified Stephen Hickey's binary tree animation:
 * Added clear, exit, balance buttons/functions
 * Added AVL tree method, display
 * Added exception handling, input validation
 * Removed unused methods
 * Tweaked display
 */

//Imports

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.Optional;

//Begin Class BST_AVL_Animation
public class BST_AVL_Animation extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> BSTtree = new BST<>(); // Create a BST tree
        AVL<Integer> AVLTree = new AVL<>(); // Create an AVL tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(BSTtree); // Create a View
        AVLView view1 = new AVLView(AVLTree);
        pane.setLeft(view);
        pane.setRight(view1);
        view.setPrefWidth(300);
        view1.setPrefWidth(250);
        pane.setPrefWidth(250);
        BorderPane.setMargin(view, new Insets(10, 20, 10, 20));
        BorderPane.setMargin(view1, new Insets(10, 20, 10, 20));

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btBalance = new Button("Balance");
        Button btClear = new Button("Clear");
        Button btExit = new Button("Exit");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btBalance, btClear, btExit);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));

        Validation v = new Validation();
        HashSet<Integer> treeVal = new HashSet<>();

        btInsert.setOnAction(e -> {
            if (v.emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (BSTtree.search(key)) { // key is in the tree already
                        view.displayBSTTree();
                        view.setStatus(key + " is already in the tree");
                    } else {
                        BSTtree.insert(key); // Insert a new key
                        view.displayBSTTree();
                        view.setStatus(key + " is inserted in the tree");
                        treeVal.add(key); // Adds value to HashSet for building AVL tree
                    }
                    tfKey.setText("");
                    tfKey.requestFocus();
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });

        btDelete.setOnAction(e -> {
            if (v.emptyTextField(tfKey)) {
                invalidKey(tfKey, "No key entered!");
            } else {
                try {
                    int key = Integer.parseInt(tfKey.getText());
                    if (!BSTtree.search(key)) { // key is not in the tree
                        view.displayBSTTree();
                        view.setStatus(key + " is not in the tree");
                    } else {
                        BSTtree.delete(key); // Delete a key
                        view.displayBSTTree();
                        view.setStatus(key + " is deleted from the tree");
                        treeVal.remove(key); // Removes key from HashSet for when tree is rebalanced
                        if (!AVLTree.isEmpty()) { // Removes key from AVL tree if it is currently displayed
                            if (AVLTree.getSize() == 1) { // Prevents NullPointerException when removing last node
                                AVLTree.clear();
                            } else {
                                AVLTree.delete(key);
                            }
                            view1.displayAVLTree();
                        }
                        AVLTree.delete(key); // Removes key from AVL tree
                    }
                    tfKey.setText("");
                } catch (NumberFormatException ex) {
                    invalidKey(tfKey, "Key must be an integer!");
                }
            }
        });

        btBalance.setOnAction(e -> {
            for (Integer i : treeVal) {
                AVLTree.insert(i); // Builds AVL tree
            }
            view1.displayAVLTree();
            if (treeVal.isEmpty()) {
                view1.setStatus("Tree is empty"); // Keeps empty message if balance is hit with no nodes to balance
            } else {
                view1.setStatus("The AVL Tree");
            }
        });

        btClear.setOnAction(e -> {
            tfKey.clear();
            BSTtree.clear();
            AVLTree.clear();
            treeVal.clear();
            view.displayBSTTree();
            view1.displayAVLTree();
            view.setStatus("BST Tree deleted");
            view1.setStatus("AVL Tree deleted");
        });

        btExit.setOnAction(e -> {
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 650, 350);
        primaryStage.setTitle("BST/AVL Animation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Warns user if an invalid key is entered
     *
     * @param key
     * @param alertHeader
     */
    private void invalidKey(TextField key, String alertHeader) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
