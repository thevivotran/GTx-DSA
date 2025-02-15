import java.util.ArrayList;
import java.util.List;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        List<T> traversal = new ArrayList<>();

        if (root == null) {
            return traversal; // Return an empty list if the root is null
        }

        if (root.getData() != null) {
            traversal.add(root.getData()); // Add current node data
            traversal.addAll(preorder(root.getLeft())); // Traverse left subtree
            traversal.addAll(preorder(root.getRight())); // Traverse right subtree
        }

        return traversal;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        List<T> traversal = new ArrayList<>();

        if (root == null) {
            return traversal; // Return an empty list if the root is null
        }

        if (root.getData() != null) {
            traversal.addAll(inorder(root.getLeft())); // Traverse left subtree
            traversal.add(root.getData()); // Add current node data
            traversal.addAll(inorder(root.getRight())); // Traverse right subtree
        }

        return traversal;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> traversal = new ArrayList<>();

        if (root == null) {
            return traversal; // Return an empty list if the root is null
        }

        if (root.getData() != null) {
            traversal.addAll(postorder(root.getLeft())); // Traverse left subtree
            traversal.addAll(postorder(root.getRight())); // Traverse right subtree
            traversal.add(root.getData()); // Add current node data
        }

        return traversal;
    }
}