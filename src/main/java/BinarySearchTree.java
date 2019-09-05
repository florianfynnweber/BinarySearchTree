import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

public class BinarySearchTree implements InterfaceBinarySearchTree {
    Node<Node, Integer> root;
    ArrayList ordered;

    BinarySearchTree() {
        root = null;
    }

    /**
     * Add a Node to the tree
     *
     * @param value The Node obj that should be add
     */
    public void addValue(Comparable value) throws BinarySearchTreeException {
        Node<Node, Integer> newNode = new Node<Node, Integer>((Integer) value);
        if (newNode.getValue() < root.getValue()) {
            root.getLeft();
        } else if (newNode.getValue() > root.getValue()) {
            root.getRight();
        } else {
            throw new BinarySearchTreeException("sth went wrong");
        }
    }


    /**
     * Delete a Node to the tree
     *
     * @param value The Node obj that should be delete
     */
    public void delValue(Comparable value) throws BinarySearchTreeException {
        if (value == null) {
            return;
        }

    }

    /**
     * Search for a node
     *
     * @param value The Node obj that should be searched
     */
    public boolean hasValue(Comparable value) {
        if (search(root, (int)value) != null){
            return true;
        }else{
            return false;
        }
    }

    // A utility function to search a given key in BST
    public Node search(Node root, int key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || (int)root.getValue()==key)
            return root;

        // val is greater than root's key
        if ((int)root.getValue() > key)
            return search(root.getLeft(), key);

        // val is less than root's key
        return search(root.getRight(), key);
    }


    /**
     * Return depth of tree
     *
     * @return depth of tree
     */
    public Integer getDepth() {
        return null;
    }

    /**
     * Order the tree sort with one order method
     *
     * @param o Which oder type should be used (Enum)
     * @return ordered List of tree
     */
    public List traverse(Order o) {
        ordered  = new ArrayList();
        switch (o) {
            case INORDER:
                getInorder(root);
                return ordered;
            case PREORDER:
                getPreorder(root);
                return ordered;
            case POSTORDER:
                getPostorder(root);
                return ordered;
            case LEVELORDER:
                return null;
        }
        return null;
    }

    public void getInorder(Node node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        ordered.add(node.getValue());
        getInorder(node.getRight());

    }

    public void getPostorder(Node node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        getInorder(node.getRight());
        ordered.add(node.getValue());
    }

    public void getPreorder(Node node) {
        if (node == null)
            return;
        ordered.add(node.getValue());
        getPreorder(node.getLeft());
        getPreorder(node.getRight());
    }

    public static void main(String[] args) throws BinarySearchTreeException {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node<Node, Integer>(10);
        tree.root.setLeft(new Node<Node, Integer>(5));
        tree.root.setRight(new Node<Node, Integer>(20));
        tree.root.getRight().setRight(new Node<Node, Integer>(25));
        //tree.traverse(Order.PREORDER);
        System.out.println(tree.traverse(Order.INORDER));
        System.out.println(tree.hasValue(5));
        //tree.traverse(Order.POSTORDER);
        tree.addValue(1);
    }
}


//*
// import java.util.List;
//import java.util.jar.JarOutputStream;
//
//public class BinarySearchTree implements InterfaceBinarySearchTree {
//    BNode<Integer> root;
//    List orderd;
//    BinarySearchTree(){
//        root = null;
//    }
//    /**
//     * Add a Node to the tree
//     * @param value The Node obj that should be add*/
//    public void addValue(Comparable value) throws BinarySearchTreeException {
//        if (value==null) {
//            return;
//        }
//
//    }
//    /**
//     * Delete a Node to the tree
//     * @param value The Node obj that should be delete*/
//    public void delValue(Comparable value) throws BinarySearchTreeException {
//        if (value==null){
//            return;
//        }
//
//    }
//    /**
//     * Search for a node
//     * @param value The Node obj that should be searched*/
//    public boolean hasValue(Comparable value) {
//        return false;
//    }
//    /**
//     * Return depth of tree
//     * @return depth of tree */
//    public Integer getDepth() {
//        return null;
//    }
//    /**
//     * Order the tree sort with one order method
//     * @param o Which oder type should be used (Enum)
//     * @return ordered List of tree*/
//    public List traverse(Order o) {
//
//        switch (o){
//            case INORDER:
//                printInorder(root);
//                return null;
//            case PREORDER:
//                printPreorder(root);
//                return null;
//            case POSTORDER:
//                printPostorder(root);
//                return null;
//            case LEVELORDER:
//                return null;
//        }
//        return null;
//    }
//    public void printInorder(BNode node){
//        if (node == null)
//            return;
//        printInorder(node.getLeft());
//        System.out.print(node.getValue() + " ");
//        printInorder(node.getRight());
//    }
//
//    public void printPostorder(BNode node)
//    {
//        if (node == null)
//            return;
//        printInorder(node.getLeft());
//        printInorder(node.getRight());
//        System.out.print(node.getValue() + " ");
//    }
//    public void printPreorder(BNode node)
//    {
//        if (node == null)
//            return;
//        System.out.print(node.getValue() + " ");
//        printPreorder(node.getLeft());
//        printPreorder(node.getRight());
//    }
//
//    public static void main(String[] args) throws BinarySearchTreeException {
//        BinarySearchTree tree = new BinarySearchTree();
//        tree.root = new BNode<Integer>(10);
//        System.out.println(tree.root.getValue());
//        tree.root.setLeft(new BNode<Integer>(5));
//        tree.root.setRight(new BNode<Integer>(20));
//        tree.root.getRight().setRight(new BNode<Integer>(25));
//        System.out.println(tree.root.getLeft().getValue());
//        //tree.traverse(Order.PREORDER);
//        tree.traverse(Order.INORDER);
//        //tree.traverse(Order.POSTORDER);
//    }
//}*/