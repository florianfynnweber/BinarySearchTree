/**
 * Interface fuer Binary Search Tree Node
 *
 * @author wolke
 * @version 1.0, 19.08.2019
 */

public class Node<N extends Node, T extends Comparable<T>> implements Comparable {
    protected N left; // left child
    protected N right; // right child
    protected T value; // integer value

    Node() {
    }

    Node(T value) {
        this.value = value;
    }

    public N getLeft() {
        return left;
    }

    /**
     * Set the left Node
     *
     * @param left Node obj
     */
    public void setLeft(N left) {
        this.left = left;
    }

    public N getRight() {
        return right;
    }

    /**
     * Set the right Node
     *
     * @param right Node obj
     */
    public void setRight(N right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(Object o) {
        Node<Node, T> other = (Node<Node, T>) o;
        return getValue().compareTo(other.getValue());
    }
}
