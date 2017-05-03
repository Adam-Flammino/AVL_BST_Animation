package AVL_BST_Animation;

/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Date: 5/1/17
 * Validation Description: Checks for empty TextField
 */
import javafx.scene.control.TextField;

public class Validation {
    /**
     * Checks if a TextField is empty
     *
     * @param t
     * @return true if empty
     */
    public boolean emptyTextField(TextField t) {
        return t.getText().trim().equals("");
    }
}
