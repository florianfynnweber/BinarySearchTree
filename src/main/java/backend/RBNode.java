package backend; /**
 * Red Black Binary Tree Node with left and right child
 * and color
 *
 * @author wolke
 * @version 1.1, 19.08.2019
 */

import java.awt.Color;


public class RBNode<T extends Comparable<T>> extends AbstractNode implements Comparable {
    private RBNode left = null;
    private RBNode right = null;
    private RBNode parent = null;
    private T value = null;
    private Color color = Color.RED;

    RBNode() {
        super();
    }

    RBNode(T value) {
        this.value = value;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = (T) value;
    }

    @Override
    public int compareTo(Object o) {
        RBNode<T> other = (RBNode<T>) o;
        return this.getValue().compareTo(other.getValue());
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }
}
