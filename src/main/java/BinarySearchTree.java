import java.util.List;

public class BinarySearchTree implements InterfaceBinarySearchTree {
    Node<Node<Node, Integer>, Integer> root;
    BinarySearchTree(){
        root = null;
    }
    /**
     * Add a Node to the tree
     * @param value The Node obj that should be add*/
    public void addValue(Comparable value) throws BinarySearchTreeException {

    }
    /**
     * Delete a Node to the tree
     * @param value The Node obj that should be delete*/
    public void delValue(Comparable value) throws BinarySearchTreeException {

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
                return null;
            case PREORDER:
                return null;
            case POSTORDER:
                return null;
            case LEVELORDER:
                return null;
        }
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node<Node<Node, Integer>, Integer>(10);
        tree.root.left = new Node<Node, Integer>(5);
        tree.root.right = new Node<Node, Integer>(20);
    }
}
