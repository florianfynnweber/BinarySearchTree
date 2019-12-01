package backend;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class BinarySearchTree implements InterfaceBinarySearchTree {
    BNode<Integer> root;
    ArrayList ordered;
    private StringBuilder treeDot = new StringBuilder();
    private MutableGraph mGraph;
    private int count;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Add a Node to the tree
     *
     * @param value The Node obj that should be add
     */
    public void addValue(Comparable value) throws BinarySearchTreeException {
        root = insertRec(root, value);
    }

    public BNode insertRec(BNode tmp, Comparable value) {
        if (tmp == null) {
            tmp = new BNode(value);
            return tmp;
        } else {
            /* Otherwise, recur down the tree */
            if ((int) value < (int) tmp.getValue())
                tmp.setLeft(insertRec(tmp.getLeft(), value));
            else if ((int) value > (int) tmp.getValue())
                tmp.setRight(insertRec(tmp.getRight(), value));
            /* return the (unchanged) node pointer */
            return tmp;
        }
    }



    /**
     * Delete a Node to the tree
     *
     * @param value The Node obj that should be delete
     */
    public void delValue(Comparable value) throws BinarySearchTreeException {
        root = delete(root, (int) value);

    }

    public BNode delete(BNode tmp, int key) {

        if (tmp == null) return tmp;

        /* Otherwise, recur down the tree */
        if (key < (int) tmp.getValue())
            tmp.setLeft(delete(tmp.getLeft(), key));
        else if (key > (int) tmp.getValue())
            tmp.setRight(delete(tmp.getRight(), key));

            // if key is same as tmp's key, then This is the node
            // to be deleted
        else {
            // node with only one child or no child
            if (tmp.getLeft() == null)
                return tmp.getRight();
            else if (tmp.getRight() == null)
                return tmp.getLeft();

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            tmp.setValue(minValue(tmp.getRight()));

            // Delete the inorder successor
            tmp.setRight(delete(tmp.getRight(), (int) tmp.getValue()));
        }

        return tmp;
    }

    public int minValue(AbstractNode tmp) {
        int minv = (int) tmp.getValue();
        while (tmp.getLeft() != null) {
            minv = (int) tmp.getLeft().getValue();
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
        return search(root, (int) value) != null;
    }


    /**
     * Search recursive in tree
     *
     * @param key integer
     * @param tmp current root
     */
    public AbstractNode search(AbstractNode tmp, int key) {
        // root is null or key is present at root
        if (tmp == null || (int) tmp.getValue() == key)
            return tmp;

        // val is greater than root's key
        if ((int) tmp.getValue() > key)
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
        return depth(root);
    }

    public int depth(AbstractNode root){
        if (root==null){
            return 0;
        }else {
            int lheight = depth(root.getLeft());
            int rheight = depth(root.getRight());
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }

    /**
     * Order the tree sort with one order method
     *
     * @param o Which oder type should be used (Enum)
     * @return ordered List of tree
     */
    public List traverse(Order o) {
        ordered = new ArrayList();
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
                getLevelorder(root);
                return ordered;
        }
        return null;
    }
    public void getLevelorder(AbstractNode node){
        int h = depth(node);
        int i;
        for (i=1; i<=h; i++)
            atGivenDepth(node, i);
    }

    private void atGivenDepth(AbstractNode node, int i) {
        if (node == null)
            return;
        if (i == 1){
            ordered.add(node.getValue());
        }else if (i>1) {
            atGivenDepth(node.getLeft(), i-1);
            atGivenDepth(node.getRight(), i-1);
        }
    }

    public void getInorder(AbstractNode node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        ordered.add(node.getValue());
        getInorder(node.getRight());

    }

    public void getPostorder(AbstractNode node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        getInorder(node.getRight());
        ordered.add(node.getValue());
    }

    public void getPreorder(AbstractNode node) {
        if (node == null)
            return;
        ordered.add(node.getValue());
        getPreorder(node.getLeft());
        getPreorder(node.getRight());
    }

    public File toGraphiz() {
        this.treeDot.setLength(0);
        this.treeDot.append("digraph BST {");
        this.graphvizTree(this.root);
        this.treeDot.append("}");
        this.count = 0;
        try {
            this.mGraph = Parser.read(treeDot.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return Graphviz.fromGraph(this.mGraph).render(Format.PNG).toFile(new File("./src/main/resources/images/tempGraph.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void graphvizTree(BNode root) {
        if (root != null){
            this.treeDot.append(root.getValue()).append("[label=").append(root.getValue()).append("];");
            if (root.getLeft() == null) {
                this.treeDot.append(root.getValue()).append("-> null").append(this.count).append("; null").append(this.count).append(" [shape=point];");
                this.count++;
            } else {
                this.treeDot.append(root.getValue()).append("-> ").append(root.getLeft().getValue()).append("; ");
                graphvizTree(root.getLeft());
            }

            if (root.getRight() == null) {
                this.treeDot.append(root.getValue()).append("-> null").append(this.count).append("; null").append(this.count).append(" [shape=point];");
                this.count++;
            } else {
                this.treeDot.append(root.getValue()).append("-> ").append(root.getRight().getValue()).append("; ");
                graphvizTree(root.getRight());
            }
        }
    }

    public static void main(String[] args) throws BinarySearchTreeException, IOException {
        BinarySearchTree tree = new BinarySearchTree();
        //tree.root = new BNode<Integer>(10);
        //tree.addValue(5);
        //tree.addValue(20);
        //tree.addValue(25);
        System.out.println(tree.traverse(Order.INORDER));
        System.out.println(tree.hasValue(5));
        System.out.println(tree.hasValue(1000000));
        //tree.addValue(3);
        System.out.println(tree.traverse(Order.POSTORDER));
        //tree.addValue(1);
        //tree.delValue(23);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(2);
        tree.addValue(6);
        tree.addValue(7);
        System.out.println(tree.root.getRight().getValue());
        System.out.println(tree.traverse(Order.INORDER));
        System.out.println(tree.traverse(Order.POSTORDER));
        System.out.println(tree.traverse(Order.PREORDER));
        System.out.println(tree.traverse(Order.LEVELORDER));
        System.out.println(tree.getDepth());
        tree.toGraphiz();
    }


}