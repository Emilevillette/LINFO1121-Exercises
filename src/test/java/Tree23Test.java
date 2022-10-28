
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class Tree23Test {
    @Test
    public void testSimple() {

        Tree23 t = new Tree23();
        for (int i = 0; i < 10; i++) {
            t.root = Tree23.put(i, (double) i, t.root);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals((double) t.search(i), (double) i, 0.0001);
        }

        assertEquals(t.search(11), null);
    }

    @Test
    public void isBalanced() {
        Tree23 t = new Tree23();
        for (int i = 0; i < 100; i++) {
            t.root = Tree23.put(i, (double) i, t.root);
        }
        assertEquals(t.isBalanced(), true);
    }

    @Test
    public void isBst() {
        Tree23 t = new Tree23();
        for (int i = 0; i < 1000; i++) {
            t.root = Tree23.put(i, (double) i, t.root);
        }
        assertEquals(t.isBst(), true);
    }
}
