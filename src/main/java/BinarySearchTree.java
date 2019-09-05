import java.util.List;

public class BinarySearchTree implements InterfaceBinarySearchTree {
    Node<Node, Integer> root;
    BinarySearchTree(){
        root = null;
    }
    /**
     * Add a Node to the tree
     * @param value The Node obj that should be add*/
    public void addValue(Comparable value) throws BinarySearchTreeException {
        if (value==null){
            throw new BinarySearchTreeException("value == null");
        }else{

        }
    }
    /**
     * Delete a Node to the tree
     * @param value The Node obj that should be delete*/
    public void delValue(Comparable value) throws BinarySearchTreeException {
        if (value==null){
            throw new BinarySearchTreeException("value == null");
        }else{

        }
    }
    /**
     * Search for a node
     * @param value The Node obj that should be searched*/
    public boolean hasValue(Comparable value) {
        return false;
    }
    /**
     * Return depth of tree
     * @return depth of tree */
    public Integer getDepth() {
        return null;
    }
    /**
     * Order the tree sort with one order method
     * @param o Which oder type should be used (Enum)
     * @return ordered List of tree*/
    public List traverse(Order o) {

        switch (o){
            case INORDER:
                printInorder(root);
                return null;
            case PREORDER:
                printPreorder(root);
                return null;
            case POSTORDER:
                printPostorder(root);
                return null;
            case LEVELORDER:
                return null;
        }
        return null;
    }
    public void printInorder(Node node){
        if (node == null)
            return;
        printInorder(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInorder(node.getRight());
    }

    public void printPostorder(Node node)
    {
        if (node == null)
            return;
        printInorder(node.getLeft());
        printInorder(node.getRight());
        System.out.print(node.getValue() + " ");
    }
    public void printPreorder(Node node)
    {
        if (node == null)
            return;
        System.out.print(node.getValue() + " ");
        printPreorder(node.getLeft());
        printPreorder(node.getRight());
    }

    public static void main(String[] args) throws BinarySearchTreeException {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node<Node, Integer>(10);
        tree.root.setLeft(new Node<Node, Integer>(5));
        tree.root.setRight(new Node<Node, Integer>(20));
        tree.root.getRight().setRight(new Node<Node, Integer>(25));
        System.out.println(tree.root.getLeft().getValue());
        tree.traverse(Order.PREORDER);
        tree.traverse(Order.INORDER);
        tree.traverse(Order.POSTORDER);
    }
}
