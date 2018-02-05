import org.junit.*;
import org.junit.rules.ExpectedException;
import settings.Settings;
import tree.ExpressionTree;
import tree.ExpressionTreeFactory;
import tree.ExpressionTreeNodeLeaf;
import tree.ExpressionTreeNodeLeafIntegers;

import static org.junit.Assert.*;

public class ExpressionTreeFactoryTest {

    private ExpressionTree expTree;
    private ExpressionTree expTree1;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        expTree = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Two));
        expTree1 = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Two));

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
    public void testRandomExpressionTreePopulation() {
//        exception.expect(java.lang.Exception.class);
//        exception.expectMessage("populationSize must be at least seven.");
//        Settings.setGenerationSize(2);
//        ExpressionTreeFactory.getRandomExpressionTreePopulation();
        Settings.setGenerationSize(7);
        ExpressionTree[] expTreeArray = ExpressionTreeFactory.getRandomExpressionTreePopulation();
        assertNotNull(expTreeArray[0].getStringRepresentation());
    }
    @Test
    public void testGetNextExpressionTreePopulation(){
        ExpressionTree[] expTreeArray = ExpressionTreeFactory.getNextExpressionTreePopulation(expTree, expTree1);
        assertTrue(expTreeArray[0].getStringRepresentation().equals("2"));
    }
    @Test
    public void testCrossOver(){
        ExpressionTreeFactory.crossover(expTree, expTree1);
        assertTrue(ExpressionTreeFactory.evaluate(expTree, 2) == 2.0);
    }
    @Test
    public void testMutate(){
        ExpressionTreeFactory.mutate(expTree);
        assertNotNull(expTree);
    }



}
