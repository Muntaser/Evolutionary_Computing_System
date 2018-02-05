import org.junit.*;
import tree.ExpressionTree;
import tree.ExpressionTreeFitnessComparator;
import tree.ExpressionTreeNodeLeaf;
import tree.ExpressionTreeNodeLeafIntegers;

import static org.junit.Assert.assertTrue;

public class ExpressionTreeFitnessComparatorTest {

    private ExpressionTree expTree1;
    private ExpressionTree expTree2;
    private ExpressionTree expTree3;
    private ExpressionTreeFitnessComparator comparator;

    @BeforeClass
      public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        expTree1 = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Two));
        expTree2 = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Two));
        expTree3 = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Four));
        comparator = new ExpressionTreeFitnessComparator();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCompareEquals() {
        assertTrue(comparator.compare(expTree1, expTree2) == 0);
    }

}
