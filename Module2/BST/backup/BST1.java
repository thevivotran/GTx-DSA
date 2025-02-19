
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
            throw new IllegalArgumentException("Data can not be null!");
        }

        BSTNode<T> dummyNode = new BSTNode<>(null);
        root = removeHelper(root, data, dummyNode);

        if (dummyNode.getData() == null) {
            return null; // Return null instead of throwing exception
        }

        size--;
        return dummyNode.getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> root, T data, BSTNode<T> dummyNode) {
        if (root == null) {
            dummyNode.setData(null);
            return null;
        } else if (data.compareTo(root.getData()) < 0) {
            root.setLeft(removeHelper(root.getLeft(), data, dummyNode));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(removeHelper(root.getRight(), data, dummyNode));
        } else {
            BSTNode<T> left = root.getLeft();
            BSTNode<T> right = root.getRight();

            if (left == null && right == null) {
                dummyNode.setData(null);
                return null;
            } else if (left != null) {
                dummyNode.setData(left.getData());
                root.setData(left.getData());
                root.setLeft(removeHelper(root.getLeft(), left.getData(), new BSTNode<>(null)));
            } else if (right != null) {
                dummyNode.setData(right.getData());
                root.setData(right.getData());
                root.setRight(removeHelper(root.getRight(), right.getData(), new BSTNode<>(null)));
            } else {
                T successorData = findSuccessor(root);
                root.setData(successorData);
                // Remove the old successor from its original position
                root.setRight(removeHelper(root.getRight(), successorData, dummyNode));
            }
        }
        return root;
    }

    private T findSuccessor(BSTNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
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