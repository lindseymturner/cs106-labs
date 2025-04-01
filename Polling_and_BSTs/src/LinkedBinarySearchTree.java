public class LinkedBinarySearchTree<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E data; // Root's element
    private LinkedBinarySearchTree<E> leftSubTree; // Reference to the left subtree
    private LinkedBinarySearchTree<E> rightSubTree; // Reference to the right subtree
    private int count; // Track tree's size

    public LinkedBinarySearchTree() {
        this.data = null;
        leftSubTree = null;
        rightSubTree = null;
    }

    /**
     * Constructs a tree with the left and right subtrees being trees themselves
     * @param data the data inputted into the node of the tree
     */
    public LinkedBinarySearchTree(E data) {
        this.data = data;
        leftSubTree = new LinkedBinarySearchTree<E>();
        rightSubTree = new LinkedBinarySearchTree<E>();
        count++;
    }

    /**
     * Inserts an element into the appropriate spot on the tree
     * @param e
     */
    @Override
    public void insert(E e) {
        // if this node is null, just replace it with the element
        if (data == null) {
            data = e;
            count ++;
        }
        // if the element is less than this root, and there is nothing in its left subtree, create a new left subtree and insert the element in the appropriate place within this subtree
        else if (e.compareTo(data) < 0) {
            if (leftSubTree == null) {
                leftSubTree = new LinkedBinarySearchTree<>();
            }
            leftSubTree.insert(e);
            count ++;
        }
        // if the element is greater than this root, and there is nothing in its right subtree, create a new right subtree and insert the element in the appropriate place within this subtree
        else if (e.compareTo(data) > 0) {
            if (rightSubTree == null) {
                rightSubTree = new LinkedBinarySearchTree<>();
            }
            rightSubTree.insert(e);
            count ++;
        }
        // if this element already present within the tree, update it with the new info
        else {
            data = e;
        }
    }

    public E getRootElement() { return data; }
    public boolean isEmpty() { return count == 0; }
    public int size() { return count - 1;}

//    public int size() {
//        if (this.isEmpty())
//            return 0; // base case
//        else
//            return 1 + leftSubTree.size() + rightSubTree.size(); // recursive case
//    }


    public String toStringInOrder() {
        return inOrderTraversal(this);
    }

    /**
     * Traverses through all elements of the tree in in-order
     * @param myTree
     * @return a string containing all the tree's data in order
     */
    public String inOrderTraversal(LinkedBinarySearchTree<E> myTree) {
        // if there is nothing in this tree, don't add anything to the string
        if (myTree == null || myTree.data == null) {
            return "";
        }
        // we visit the left subtree, then the root, then the right subtree. recursive. goes through whole tree
        return inOrderTraversal(myTree.leftSubTree) + " " + myTree.data + " " + inOrderTraversal(myTree.rightSubTree);
    }

    @Override
    public String toStringPreOrder() {
        return preOrderTraversal(this);
    }

    /**
     * Traverse through all elements of the tree in pre-order
     * @param myTree
     * @return a string containing all the tree's data in pre-order
     */
    public String preOrderTraversal(LinkedBinarySearchTree<E> myTree) {
        // if there is nothing in this tree, don't add anything to the string
        if (myTree == null || myTree.data == null) {
            return "";
        }
        // we visit the root, then the left subtree, then the right subtree. recursive. goes through whole tree.
        return myTree.data + " " + preOrderTraversal(myTree.leftSubTree) + " " + preOrderTraversal(myTree.rightSubTree);
    }

    @Override
    public String toStringPostOrder() {
        return postOrderTraversal(this);
    }

    /**
     * Traverses through all elements of the tree in post-order
     * @param myTree
     * @return a string containing all the tree's data in post-order
     */
    public String postOrderTraversal(LinkedBinarySearchTree<E> myTree) {
        // if there is nothing in this tree, don't add anything to the string
        if (myTree == null || myTree.data == null) {
            return "";
        }
        // we visit the left subtree, then the right subtree, then the root. recursive. goes through the whole tree.
        return postOrderTraversal(myTree.leftSubTree) + " " + postOrderTraversal(myTree.rightSubTree) + " " + myTree.data;
    }

    @Override
    public String toString() {
        return "Tree:\n" + "\tPre: " + toStringPreOrder() + "\n\tIn: " + toStringInOrder() + "\n\tPost: " + toStringPostOrder();
    }
}