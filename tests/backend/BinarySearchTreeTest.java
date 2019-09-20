package backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest{
    private BinarySearchTree tree;
    @BeforeEach
    void setup(){
        this.tree = new BinarySearchTree();
    }
    @Test
    void addValue() throws BinarySearchTreeException {
        tree.addValue(5);
        assertEquals(5, tree.root.getValue());
        tree.addValue(2);
        assertEquals(2, tree.root.getLeft().getValue());
        tree.addValue(6);
        assertEquals(6, tree.root.getRight().getValue());
    }

    @Test
    void delValue() {
    }

    @Test
    void hasValue() {
    }

    @Test
    void search() {
    }

    @Test
    void getDepth() {
    }

    @Test
    void traverse() throws BinarySearchTreeException {
        List<Integer> actual = Arrays.asList(1,2,3,4,5,5);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(2);
        tree.addValue(6);
        tree.addValue(7);
        assertThat(tree.traverse(Order.PREORDER).toArray(), arrayContaining(5,3,2,6,7));
    }
    @AfterEach
    void teardown(){
        this.tree = null;
    }
}