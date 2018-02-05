import org.junit.*;
import settings.Settings;
import tree.ExpressionTree;
import tree.ExpressionTreeFitnessTrainer;
import tree.ExpressionTreeNodeLeaf;
import tree.ExpressionTreeNodeLeafIntegers;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ExpressionTreeFitnessTrainerTest {

    private ExpressionTree expTree;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        expTree = new ExpressionTree(new ExpressionTreeNodeLeaf(ExpressionTreeNodeLeafIntegers.Zero));
    }

    @After
    public void tearDown() throws Exception {
    }

       @Test
    public void testFitnessValue() {
           System.out.println(ExpressionTreeFitnessTrainer.getFitness(expTree));
    }

    @Test
    public void testFitness() {
        HashMap<Float, Float> fitnessTrainerMap = new HashMap<Float,Float>();
        fitnessTrainerMap.put(1.0f, 2.0f);
        Settings.setFitnessTrainer(fitnessTrainerMap);
        assertTrue(ExpressionTreeFitnessTrainer.getFitness(expTree) == 2.0);
    }


}
