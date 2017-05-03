package AVL_BST_Animation;

/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Date: 4/29/17
 * Tree Description: Tree Interface
 */

public interface Tree<E> extends Iterable<E> {
    /**
     * Return true if the element is in the tree
     *
     * @param e
     * @return
     */
    boolean search(E e);

    /**
     * Insert element into the binary tree Return true if the element is
     * inserted successfully
     *
     * @param e
     * @return
     */
    boolean insert(E e);

    /**
     * Delete the specified element from the tree Return true if the element is
     * deleted successfully
     *
     * @param e
     * @return
     */
    boolean delete(E e);

    /**
     * Inorder traversal from the root
     */
    void inorder();

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    int getSize();

    /**
     * Return true if the tree is empty
     *
     * @return
     */
    boolean isEmpty();

}
