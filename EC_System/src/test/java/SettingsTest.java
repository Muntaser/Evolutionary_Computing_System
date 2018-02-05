import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import settings.Settings;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class SettingsTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    TrainingSetGenerator trainingSetGenerator;
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    HashMap<Float, Float> fitnessTrainerMap = new HashMap<Float,Float>();
    fitnessTrainerMap.put(1.0f, 2.0f);
    Settings.setExecutionLength(5);
    Settings.setFitnessThreshold(0);
    Settings.setGenerationSize(100);
    Settings.setTreeSize(5);
    Settings.setDataFilePath("/test");
    Settings.setFitnessTrainer(fitnessTrainerMap);
    Settings.setDataDelim(";");
  }

  @Test
  public void testExecutionLength() {
    assertTrue(Settings.getExecutionLength() == 5);
    assertTrue(Settings.getFitnessThreshold() == 0);
    assertTrue(Settings.getGenerationSize() == 100);
    assertTrue(Settings.getTreeSize() == 5);
    assertTrue(Settings.getDataFilePath().equals("/test"));
    assertTrue(Settings.getFitnessTrainer().get(1.0f) == 2.0f);
    assertTrue(Settings.getDataDelim().equals(";"));

  }
}
