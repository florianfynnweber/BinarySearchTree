package backend;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree tree;

    private void addNodesToTree(int i_) throws BinarySearchTreeException {
        for (int i=0; i<i_;i++){
            tree.addValue(new Random().nextInt(10));
        }
    }
    @BeforeEach
    void setup() {
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
    void delValue() throws BinarySearchTreeException {
        this.addNodesToTree(6);
        tree.addValue(800);
        assertTrue(tree.hasValue(800));
        tree.delValue(800);
        assertFalse(tree.hasValue(800));}

    @Test
    void hasValue() throws BinarySearchTreeException {
        this.addNodesToTree(6);
        tree.addValue(800);
        assertTrue(tree.hasValue(800));
        assertFalse(tree.hasValue(700));

        List li = tree.traverse(Order.PREORDER);
        li.forEach((tmp)->{
            assertTrue(tree.hasValue((Comparable) tmp));
        });    }

    @Test
    void getDepth() {
    }

    @Test
    void traverse() throws BinarySearchTreeException {
        List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5, 5);
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(2);
        tree.addValue(6);
        tree.addValue(7);
        assertThat(tree.traverse(Order.PREORDER).toArray(), arrayContaining(5, 3, 2, 6, 7));
        assertThat(tree.traverse(Order.POSTORDER).toArray(), arrayContaining(2, 3, 6, 7, 5));
        assertThat(tree.traverse(Order.INORDER).toArray(), arrayContaining(2, 3, 5, 6, 7));
    }

    @AfterEach
    void teardown() {
        this.tree = null;
    }
}
