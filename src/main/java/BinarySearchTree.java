import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

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
       insertRec(root, value);
    }
    public Node insertRec(Node root, Comparable value){
        if (root == null) {
            root = new Node(value);
            return root;
        }
        /* Otherwise, recur down the tree */
        if ((int)value < (int)root.getValue())
            root.setLeft(insertRec(root.left, (int)value));
        else if ((int) value > (int)root.getValue())
            root.right = insertRec(root.right, (int) value);
        /* return the (unchanged) node pointer */
        return root;
    }


    /**
     * Delete a Node to the tree
     *
     * @param value The Node obj that should be delete
     */
    public void delValue(Comparable value) throws BinarySearchTreeException {
        root = delete(root, (int)value);

    }

    public Node delete(Node tmp, int key){

        if (tmp == null)  return tmp;

        /* Otherwise, recur down the tree */
        if (key < (int)tmp.getValue())
            tmp.setLeft(delete(tmp.getLeft(), key));
        else if (key > (int)tmp.getValue())
            tmp.setRight(delete(tmp.getRight(), key));

            // if key is same as tmp's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (tmp.getLeft() == null)
                return tmp.getRight();
            else if (tmp.getRight() == null)
                return tmp.getLeft();

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            tmp.setValue(minValue(tmp.getRight()));

            // Delete the inorder successor
            tmp.setRight(delete(tmp.right, (int)tmp.getValue()));
        }

        return tmp;
    }
    public int minValue(Node tmp)
    {
        int minv = (int)tmp.getValue();
        while (tmp.getLeft() != null)
        {
            minv = (int)tmp.left.getValue();
            tmp = tmp.getLeft();
        }
        return minv;
    }
    /**
     * Search for a node
     *
     * @param value The Node obj that should be searched
     */
    public boolean hasValue(Comparable value) {
        // check if value exist in search
        if (search(root, (int)value) != null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Search recursive in tree
     * @param key integer
     * @param tmp current root*/
    public Node search(Node tmp, int key)
    {
        // root is null or key is present at root
        if (tmp==null || (int)tmp.getValue()==key)
            return tmp;

        // val is greater than root's key
        if ((int)tmp.getValue() > key)
            return search(tmp.getLeft(), key);

        // val is less than root's key
        return search(tmp.getRight(), key);
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

    public static void main(String[] args) throws BinarySearchTreeException, IOException {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node<Node, Integer>(10);
        tree.addValue(5);
        tree.addValue(20);
        tree.addValue(25);
        System.out.println(tree.traverse(Order.INORDER));
        System.out.println(tree.hasValue(5));
        tree.addValue(3);
        //tree.traverse(Order.POSTORDER);
        tree.addValue(1);
        tree.delValue(23);
        System.out.println(tree.traverse(Order.INORDER));
        tree.to_graphiz();
    }

    public void to_graphiz() throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(RankDir.LEFT_TO_RIGHT)
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link((node("c")).with(Style.DASHED))
                );
        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));


    }
}