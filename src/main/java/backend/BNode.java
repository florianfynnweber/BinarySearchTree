package backend;

/**
 * Binary Tree Node with left and right child
 *
 * @author wolke
 * @version 1.1, 19.08.2019
 *
 */


public class BNode<T extends Comparable<T>> extends AbstractNode implements Comparable{
    private BNode left = null;
    private BNode right = null;
    private T value = null;

    BNode(){ super();}

    BNode(T value){
            this.value = value;
        }

    public BNode getLeft() {
            return left;
        }

    public void setLeft(BNode left) {
            this.left = left;
        }

    public BNode getRight() {
        return right;
    }

    public void setRight(BNode right) {
        this.right = right;
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
        BNode<T> other = (BNode<T>) o;
        return this.getValue().compareTo(other.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
