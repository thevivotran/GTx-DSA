import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null!");
        }

        root = addHelper(root, data);
    }

    private BSTNode<T> addHelper(BSTNode<T> root, T data) {
        if (root == null) {
            size++;
            return new BSTNode<>(data);
        } else if (data.compareTo(root.getData()) < 0) {
            root.setLeft(addHelper(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(addHelper(root.getRight(), data));
        } else {
            return root;
        }
        return root;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        BSTNode<T> dummy1 = new BSTNode(-1);
        root = removeHelper(root, data, dummy1);

        return dummy1.getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy1) {
        if (node == null) {
            throw new NoSuchElementException("Data not found in the tree");
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy1));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy1));
        } else {
            dummy1.setData(node.getData());
            size--;
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                BSTNode<T> dummy2 = new BSTNode(-1);
                node.setRight(findSuccessor(node.getRight(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        return node;
    }

    private BSTNode<T> findSuccessor(BSTNode<T> node, BSTNode<T> dummy2) {
        if (node.getLeft() == null) {
            dummy2.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(findSuccessor(node.getLeft(), dummy2));
        }
        return node;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}