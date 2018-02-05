import org.junit.*;
import tree.ExpressionTree;
import tree.ExpressionTreeFactory;
import tree.ExpressionTreeNodeLeaf;
import tree.ExpressionTreeNodeLeafIntegers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExpressionTreeTest {

  private ExpressionTree expTree;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    expTree = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Two));
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testEvaluate() {
    assertTrue(ExpressionTreeFactory.evaluate(expTree, 2) == 2.0);
  }

  @Test
  public void testGetClonedExpressionTree() {
    assertTrue(ExpressionTreeFactory.getClonedExpressionTree(expTree).getStringRepresentation().equals("2"));

  }
  @Test
  public void testMutate() {
    expTree.mutate();
    assertNotNull(expTree);
  }

  @Test
  public void testCrossover() {
    expTree.crossover(expTree);
    assertTrue(ExpressionTreeFactory.getClonedExpressionTree(expTree).getStringRepresentation().equals("2"));
  }
}