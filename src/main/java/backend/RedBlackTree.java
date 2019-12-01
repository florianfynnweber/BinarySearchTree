package backend;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class RedBlackTree implements InterfaceBinarySearchTree {
    /**
     * root node of tree
     */
    RBNode<Integer> root;
    /**
     * include traverse results
     */
    ArrayList ordered;
    /**
     * helpers for dot
     */
    private StringBuilder treeDot = new StringBuilder();
    private MutableGraph mGraph;
    private int count;

    /**
     * Constructor
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Add a Node to the tree
     *
     * @param value The Node obj that should be add
     */
    public void addValue(Comparable value) throws BinarySearchTreeException {
        root = insertRec(root, value, null);
    }


    /**
     * Should find the uncle, doesnt work now
     *
     * @param node   a node obj
     * @param parent the parent of node
     * @return uncle of node
     */
    public RBNode findUncle(RBNode node, RBNode parent) {
        if (parent != null) {
            if (parent.getRight() == null && parent.getLeft() == null) {
                node.setUncle(null);
            } else if (parent.getRight() != null) {
                node.setUncle(parent.getRight());
            } else if (parent.getLeft() != null) {
                node.setUncle(parent.getLeft());
            }
        }
        return node;
    }

    /**
     * Search for the right position in the tree
     *
     * @param tmp    current node
     * @param value  value that should be added
     * @param parent parent node of tmp
     * @return added node
     */
    public RBNode insertRec(RBNode tmp, Comparable value, RBNode parent) {
        if (tmp == null) {
            tmp = new RBNode(value, parent);
            tmp = findUncle(tmp, parent);
            insert_case1(tmp);
            return tmp;
        } else {
            /* Otherwise, recur down the tree */
            if ((int) value < (int) tmp.getValue())
                tmp.setLeft(insertRec(tmp.getLeft(), value, tmp));
            else if ((int) value > (int) tmp.getValue())
                tmp.setRight(insertRec(tmp.getRight(), value, tmp));
            /* return the (unchanged) node pointer */
            return tmp;
        }
    }

    /**
     * First case adding a node to RedBlackTree
     *
     * @param node a node obj
     */
    public void insert_case1(RBNode node) {
        if (node.getParent() == null) {
            node.setColor(BLACK);
        } else {
            insert_case2(node);
        }
    }

    /**
     * Second case adding a node to RedBlackTree
     *
     * @param node a node obj
     */
    public void insert_case2(RBNode node) {
        if (node.getParent().getColor() == BLACK) {
        } else {
            insert_case3(node);
        }
    }

    /**
     * Third case adding a node to RedBlackTree
     *
     * @param node a node obj
     */
    public void insert_case3(RBNode node) {
        if ((node.getUncle() != null) && (node.getUncle().getColor() == RED)) {
            node.getParent().setColor(BLACK);
            node.getUncle().setColor(BLACK);
            node.getParent().getParent().setColor(RED);
            insert_case1(node.getParent().getParent());
        } else {
            insert_case4(node.getParent().getParent());
        }
    }

    /**
     * Fourth case adding a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void insert_case4(RBNode node) {
        if ((node == node.getParent().getRight()) && (node.getParent() == node.getParent().getParent().getLeft())) {
            // rotate with parent
            this.rotate_left(node.getParent());
            //System.out.println("routate left");
            node = node.getLeft();
        } else if ((node == node.getParent().getLeft()) && (node.getParent() == node.getParent().getParent().getRight())) {
            //rotate with parent
            this.rotate_right(node.getParent());
            // System.out.println("rotate right");
            node = node.getRight();
        }
        insert_Case5(node);
    }

    /**
     * Fifth case adding a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void insert_Case5(RBNode node) {
        if ((node == node.getParent().getLeft()) && (node.getParent() == node.getParent().getParent().getLeft())) {
            // rotate with grandparent
            this.rotate_right(node.getParent().getParent());
            // System.out.println("rotate right");
        } else if ((node == node.getParent().getRight()) && (node.getParent() == node.getParent().getParent().getRight())) {
            // rotate with grandparent
            this.rotate_left(node.getParent().getParent());
            // System.out.println("rotate left");
            node.getParent().setColor(BLACK);
            node.getParent().getParent().setColor(RED);
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

    /**
     * Search recursive in tree for the node that should deleted
     *
     * @param tmp current node
     * @param key value of node that should be deleted
     * @return reordered tree
     */
    public RBNode delete(RBNode tmp, int key) {

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

    /**
     * @param tmp a node
     * @return lowest value of node obj
     */
    public int minValue(AbstractNode tmp) {
        int minv = (int) tmp.getValue();
        while (tmp.getLeft() != null) {
            minv = (int) tmp.getLeft().getValue();
            tmp = tmp.getLeft();
        }
        return minv;
    }

    /**
     * First case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case1(RBNode node) {
        if (node.getParent() != null) {
            del_case2(node);
        } else {
            delete(node, (int) node.getValue());
        }
    }

    /**
     * Second case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case2(RBNode node) {
        if (node.getSibling().getColor() == RED) {
            if (node == node.getParent().getLeft()) {
                rotate_left(node.getParent());
            } else {
                rotate_right((node.getParent()));
                node.getParent().setColor(RED);
                node.getSibling().setColor(BLACK);
            }
        }
        del_case3(node);
    }

    /**
     * Third case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case3(RBNode node) {
        if ((node.getParent().getColor() == BLACK) && (node.getSibling().getColor() == BLACK) && (node.getSibling().getLeft().getColor() == BLACK) && (node.getSibling().getRight().getColor() == BLACK)) {
            node.getSibling().setColor(RED);
            del_case1(node.getParent());
        } else {
            del_case4(node);
        }
    }

    /**
     * Fourth case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case4(RBNode node) {
        if ((node.getParent().getColor() == RED) && (node.getSibling().getLeft().getColor() == BLACK) && (node.getSibling().getRight().getColor() == BLACK)) {
            node.getSibling().setColor(RED);
            node.getParent().setColor(BLACK);
        } else {
            del_case5(node);
        }
    }

    /**
     * Fifth case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case5(RBNode node) {
        if (node.getSibling().getColor() == RED) {
            if ((node == node.getParent().getLeft()) && (node.getSibling().getRight().getColor() == BLACK) && (node.getSibling().getLeft().getColor() == RED)) {
                rotate_right(node.getSibling());
            } else if ((node == node.getParent().getRight()) && (node.getSibling().getLeft().getColor() == BLACK) && (node.getSibling().getRight().getColor() == RED)) {
                rotate_left(node.getSibling());
                node.getSibling().setColor(RED);
                node.getSibling().getRight().setColor(BLACK);
            }
        }
        del_case6(node);
    }

    /**
     * Sixth case deleting a node to RedBlackTree
     *
     * @param node a node obj
     */
    private void del_case6(RBNode node) {
        node.getSibling().setColor(node.getParent().getColor());
        if (node == node.getParent().getLeft()) {
            node.getSibling().getRight().setColor(BLACK);
            rotate_left(node.getParent());
        } else {
            node.getSibling().getLeft().setColor(BLACK);
            rotate_right(node.getParent());
        }
    }

    /**
     * Rotate left around the given node
     *
     * @param node a node obj
     */
    private void rotate_left(RBNode node) {
        if (node.getParent() != null) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(node.getRight());
            } else {
                node.getParent().setRight(node.getRight());
            }
            node.getRight().setParent(node.getParent());
            node.setParent(node.getRight());
            if (node.getRight().getLeft() != null) {
                node.getRight().getLeft().setParent(node);
            }
            node.setRight(node.getRight().getLeft());
            node.getParent().setLeft(node);
        } else {
            RBNode right = root.getRight();
            root.setRight(right.getLeft());
            right.getLeft().setParent(root);
            root.setParent(right);
            right.setLeft(root);
            right.setParent(null);
            root = right;
        }
    }

    /**
     * Rotate right around the given node
     *
     * @param node a node obj
     */
    private void rotate_right(RBNode node) {
        if (node.getParent() != null) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(node.getLeft());
            } else {
                node.getParent().setRight(node.getLeft());
            }
            node.getLeft().setParent(node.getParent());
            node.setParent(node.getLeft());
            if (node.getLeft().getRight() != null) {
                node.getLeft().getRight().setParent(node);
            }
            node.setLeft(node.getLeft().getRight());
            node.getParent().setRight(node);
        } else {
            RBNode left = root.getLeft();
            root.setLeft(root.getLeft().getRight());
            left.getRight().setParent(root);
            root.setParent(left);
            left.setRight(root);
            left.setParent(null);
            root = left;
        }
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

    public int depth(AbstractNode root) {
        if (root == null) {
            return 0;
        } else {
            int lheight = depth(root.getLeft());
            int rheight = depth(root.getRight());
            if (lheight > rheight)
                return (lheight + 1);
            else return (rheight + 1);
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

    /**
     * sorted tree by levelorder
     *
     * @param node a node obj (mostly root)
     */
    public void getLevelorder(AbstractNode node) {
        int h = depth(node);
        int i;
        for (i = 1; i <= h; i++)
            atGivenDepth(node, i);
    }

    /**
     * Helper for levelorder
     *
     * @param node a node obj
     * @param i    level number
     */
    private void atGivenDepth(AbstractNode node, int i) {
        if (node == null)
            return;
        if (i == 1) {
            ordered.add(node.getValue());
        } else if (i > 1) {
            atGivenDepth(node.getLeft(), i - 1);
            atGivenDepth(node.getRight(), i - 1);
        }
    }

    /**
     * sorted tree by inorder
     *
     * @param node a node obj (mostly root)
     */
    public void getInorder(AbstractNode node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        ordered.add(node.getValue());
        getInorder(node.getRight());

    }

    /**
     * sorted tree by postorder
     *
     * @param node a node obj (mostly root)
     */
    public void getPostorder(AbstractNode node) {
        if (node == null)
            return;
        getInorder(node.getLeft());
        getInorder(node.getRight());
        ordered.add(node.getValue());
    }

    /**
     * sorted tree by preorder
     *
     * @param node a node obj (mostly root)
     */
    public void getPreorder(AbstractNode node) {
        if (node == null)
            return;
        ordered.add(node.getValue());
        getPreorder(node.getLeft());
        getPreorder(node.getRight());
    }

    /**
     * Returns the tree as a image
     *
     * @return Gives back a image file as png
     */
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

    /**
     * Translates the tree into dot notation
     *
     * @param root a node obj
     */
    private void graphvizTree(RBNode root) {
        if (root != null) {
            this.treeDot.append(root.getValue()).append("[label=").append(root.getValue()).append(",color=" + ((root.getColor() == RED) ? "red" : "black") + ",style=filled,fontcolor=white];");
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

    public static void main(String[] args) throws BinarySearchTreeException {
        RedBlackTree tree = new RedBlackTree();
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
        tree.addValue(6);
        tree.addValue(1);
        System.out.println(tree.traverse(Order.PREORDER));
        //tree.rotate_right(tree.root);
        System.out.println(tree.traverse(Order.LEVELORDER));
    }
}
