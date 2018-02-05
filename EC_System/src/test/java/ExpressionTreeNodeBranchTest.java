import org.junit.*;
import tree.ExpressionTreeFactory;
import tree.ExpressionTreeNodeBranch;
import tree.ExpressionTreeNodeBranchOperands;
import tree.ExpressionTreeNodeVariable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExpressionTreeNodeBranchTest {
  private ExpressionTreeNodeBranch nodeBranch;
  private ExpressionTreeNodeVariable nodeVariable;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    nodeBranch = new ExpressionTreeNodeBranch(ExpressionTreeNodeBranchOperands.getRandom(), ExpressionTreeFactory.getRandomExpressionTreeNode(1), ExpressionTreeFactory.getRandomExpressionTreeNode(2));
    nodeVariable =  new ExpressionTreeNodeVariable();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testEvaulate() {
    assertNotNull(nodeBranch.evaluate(2.0f));
  }

  @Test
  public void test() {
    nodeBranch.mutate();
    assertNotNull(nodeBranch);
    assertTrue(nodeBranch.getNodeCount() > 0);
  }

  @Test
  public void testGetClonedExpressionTree() {
    assertNotNull(nodeBranch.deepClone());
  }

  @Test
  public void testRandomNodeByHeight() {
    assertNotNull(nodeBranch.getRandomNodeByHeight(0, 1));
  }

  @Test
  public void testNodeByIndex() {
    assertNotNull(nodeBranch.getNodeByIndex(0, 0));
  }

  @Test
  public void testSetNodeByIndex() {
    assertNotNull(nodeBranch.setNodeByIndex(0, 0, ExpressionTreeFactory.getRandomExpressionTreeNode(2)));
  }

  @Test
  public void testGetNodeByIndex() {
    assertNotNull(nodeBranch.getNodeByIndex(0, 0));
  }
  @Test
  public void testGetIndexByNode() {
    assertNotNull(nodeBranch.getIndexByNode(ExpressionTreeFactory.getRandomExpressionTreeNode(2), 0));
  }

  @Test
  public void testGetIndexByNodeWhenIndexLessThanZero() {
    assertTrue(nodeBranch.getIndexByNode(ExpressionTreeFactory.getRandomExpressionTreeNode(2), 0) == -1);
  }
  @Test
  public void testGetIndexByNodeVariable() {
    nodeVariable.mutate();
    assertNotNull(nodeVariable.getIndexByNode(ExpressionTreeFactory.getRandomExpressionTreeNode(2), 0));
  }

  @Test
  public void testGetNodeByIndexExpressionTreeNodeVariable() {
    assertNotNull(nodeVariable.getNodeByIndex(0, 0));
  }

  @Test
  public void testRandomNodeByHeightExpressionTreeNodeVariable() {
    assertNotNull(nodeVariable.getRandomNodeByHeight(0, 1));
  }
}
