package backend;

/**
 * Astract class for a node
 * Takes any comparable as Value
 * @author wolke
 * @version 1.1, 19.08.2019
 *
 */

public abstract class AbstractNode<T extends Comparable<T>> {
    protected T value; // integer value
    private AbstractNode left = null;
    private AbstractNode right = null;
    AbstractNode(){}

    AbstractNode(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public  String toString(){
        return value.toString();
    }
    public AbstractNode getLeft() {
        return left;
    }

    public void setLeft(AbstractNode left) {
        this.left = left;
    }

    public AbstractNode getRight() {
        return right;
    }

    public void setRight(AbstractNode right) {
        this.right = right;
    }
}
