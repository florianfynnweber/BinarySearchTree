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

    BNode(){ super();};

    BNode(T value){
            value = value;
        };

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
    public int compareTo(Object o) {
        BNode<T> other = (BNode<T>) o;
        return getValue().compareTo(other.getValue());
    }
}
