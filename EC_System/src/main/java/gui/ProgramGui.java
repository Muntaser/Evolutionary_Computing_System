package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import settings.Settings;
import tree.ExpressionTree;
import tree.ExpressionTreeFactory;
import tree.ExpressionTreeFitnessComparator;
import tree.ExpressionTreeFitnessTrainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class ProgramGui extends JFrame {

  // JFreeChart variables
  private final JDialog chartFrame = new JDialog();
  private final String series = "Fitness";
  private final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
  private final JFreeChart chart = ChartFactory.createLineChart(
      "Population Fitness",      // chart title
      "Population",              // domain axis label
      "Fitness",                 // range axis label
      dataset,                   // data
      PlotOrientation.VERTICAL,  // orientation
      false,                     // include legend
      true,                      // tooltips
      false                      // urls
  );
  private final ChartPanel chartPanel = new ChartPanel(chart);
  private JButton btnBrowseDataFile;
  private JButton btnClear;
  private JButton btnStart;
  private JLabel lblAcceptFitnessVal;
  private JLabel lblMaxTreeDepth;
  private JLabel lblMaxRunTime;
  private JLabel lblPopulationSize;
  private JLabel lblTerminatDataSet;
  // Variables declaration
  private JLabel lblTitle;
  private JSpinner spnAcceptFitnessVal;
  private JSpinner spnMaxRunTime;
  private JSpinner spnMaxTreeDepth;
  private JSpinner spnPopulationSize;
  private JTextField txtTerminatDataSet;
  private int optionReturn;
  private JDialog cancelPanel = new JDialog();
  private JButton cancelChart = new JButton("Cancel");

  private JPanel statusPanel = new JPanel();
  private JLabel status = new JLabel("", SwingConstants.CENTER);
  private JLabel solution = new JLabel("", SwingConstants.CENTER);

  private boolean running = false;

  public ProgramGui() {
    setTitle("Evolutionary Computing Project");
    initComponents();
  }

  public static void main(String args[]) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ProgramGui().setVisible(true);
      }
    });
  }

  private void initComponents() {

    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    CategoryAxis range = plot.getDomainAxis();

    range.setTickLabelsVisible(false);

    cancelPanel.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    cancelPanel.setResizable(false);
    cancelPanel.setUndecorated(true);
    cancelPanel.setModal(true);

    cancelPanel.setLayout(new BorderLayout());
    cancelChart.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent evt) {
        btnCancelChartMouseReleased(evt);
      }
    });

    cancelPanel.add(new JLabel("Executing... Please Wait."), BorderLayout.WEST);
    cancelPanel.add(cancelChart, BorderLayout.EAST);
    cancelPanel.pack();
    cancelPanel.setLocationRelativeTo(this);

    chartPanel.setPreferredSize(new Dimension(800, 600));

    chartFrame.setResizable(false);
    chartFrame.setModal(true);
    chartFrame.setLayout(new BorderLayout());

    statusPanel.setLayout(new BorderLayout());
    statusPanel.add(status, BorderLayout.WEST);
    statusPanel.add(solution, BorderLayout.EAST);

    chartFrame.add(chartPanel);
    chartFrame.add(statusPanel, BorderLayout.PAGE_END);

    chartFrame.pack();
    chartFrame.setLocationRelativeTo(null);

    SpinnerModel treeHeight = new SpinnerNumberModel(5, 3, 5, 1);
    SpinnerModel populationSize = new SpinnerNumberModel(10, 7, 20, 1);
    SpinnerModel fitnessVal = new SpinnerNumberModel(0, 0, 100, 1);
    SpinnerModel runTime = new SpinnerNumberModel(15, 1, 15, 1);

    lblPopulationSize = new JLabel();
    lblAcceptFitnessVal = new JLabel();
    lblMaxTreeDepth = new JLabel();
    lblTerminatDataSet = new JLabel();
    lblTitle = new JLabel();
    lblMaxRunTime = new JLabel();

    spnPopulationSize = new JSpinner(populationSize);
    spnAcceptFitnessVal = new JSpinner(fitnessVal);
    spnMaxTreeDepth = new JSpinner(treeHeight);
    spnMaxRunTime = new JSpinner(runTime);

    btnStart = new JButton();
    btnClear = new JButton();
    btnBrowseDataFile = new JButton();

    txtTerminatDataSet = new JTextField();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    lblPopulationSize.setText("Population Size");
    lblAcceptFitnessVal.setText("Target Fitness Value");
    lblMaxTreeDepth.setText("Maximum Tree Depth");
    lblTerminatDataSet.setText("Training Set File");
    lblMaxRunTime.setText("Run Time (in minutes)");

    spnPopulationSize.setName("valPopulationSize");

    spnAcceptFitnessVal.setName("valAcceptFitnessVal");
    spnMaxTreeDepth.setName("valMaxTreeDepth");

    btnStart.setText("Start");
    btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent evt) {
        btnStartMouseReleased(evt);
      }
    });

    btnClear.setText("Clear");
    btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent evt) {
        btnClearMouseReleased(evt);
      }
    });

    btnBrowseDataFile.setText("Browse");
    btnBrowseDataFile.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent evt) {
        btnBrowseDataFileMouseReleased(evt);
      }
    });

    lblTitle.setFont(new Font("Tahoma", 1, 18));
    lblTitle.setForeground(new Color(102, 0, 102));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setText("Evolutionary Computing Project");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblTerminatDataSet)
                                .addComponent(lblPopulationSize)
                                .addComponent(lblMaxTreeDepth)
                        )
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtTerminatDataSet, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(spnMaxTreeDepth, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnAcceptFitnessVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnMaxRunTime, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnClear)
                                                .addGap(73, 73, 73)
                                                .addComponent(btnStart))))
                                    .addComponent(spnPopulationSize, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE))))
                    .addComponent(lblAcceptFitnessVal)
                    .addComponent(lblMaxRunTime))
                .addGap(18, 18, 18)
                .addComponent(btnBrowseDataFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54))
    );

    layout.linkSize(SwingConstants.HORIZONTAL, new Component[]{spnAcceptFitnessVal, spnMaxRunTime, spnMaxTreeDepth, spnPopulationSize});

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTerminatDataSet)
                    .addComponent(txtTerminatDataSet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseDataFile))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPopulationSize)
                    .addComponent(spnPopulationSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxTreeDepth)
                    .addComponent(spnMaxTreeDepth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                ).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                ).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAcceptFitnessVal)
                    .addComponent(spnAcceptFitnessVal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxRunTime)
                    .addComponent(spnMaxRunTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnClear))
                .addGap(26, 26, 26))
    );

    pack();
    setLocationRelativeTo(null);
  }

  private void btnCancelChartMouseReleased(MouseEvent evt) {
    running = false;
  }

  private void btnBrowseDataFileMouseReleased(MouseEvent evt) {

    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int chooseFileResult = fileChooser.showOpenDialog(this);

    if (chooseFileResult == JFileChooser.CANCEL_OPTION) {
      return;
    }

    File dataSetFileName = fileChooser.getSelectedFile();

    if ((dataSetFileName == null) || (dataSetFileName.getName().equals(""))) {
      return;
    } else {
      try {
        txtTerminatDataSet.setText(dataSetFileName.getCanonicalPath());
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  private void btnStartMouseReleased(MouseEvent evt) {

    if (txtTerminatDataSet.getText().trim().equals("")) {
      JOptionPane.showMessageDialog(this, "Training Set File Not Set", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    } else if (!new File(txtTerminatDataSet.getText()).exists()) {
      JOptionPane.showMessageDialog(this, "Training Set File Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    Settings.setDataFilePath(txtTerminatDataSet.getText());
    Settings.setGenerationSize(((Integer) spnPopulationSize.getValue()).intValue());
    Settings.setTreeSize(((Integer) spnMaxTreeDepth.getValue()).intValue());
    Settings.setFitnessThreshold(((Integer) spnAcceptFitnessVal.getValue()).intValue());
    Settings.setExecutionLength(((Integer) spnMaxRunTime.getValue()).intValue());

    // load training set
    try {
      BufferedReader br = new BufferedReader(new FileReader(Settings.getDataFilePath()));
      try {
        String line;
        HashMap<Float, Float> fitnessTrainer = new HashMap<Float, Float>();

        while ((line = br.readLine()) != null) {
          String[] values = line.split(";");
          fitnessTrainer.put(Float.parseFloat(values[0]), Float.parseFloat(values[1]));
        }

        if (fitnessTrainer.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Empty Training Set", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        Settings.setFitnessTrainer(fitnessTrainer);

      } finally {
        br.close();
      }
    } catch (HeadlessException e) {
      JOptionPane.showMessageDialog(this, "Invalid Training Set", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(this, "Invalid Training Set", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Invalid Training Set", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid Training Set", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    Runnable runnable = new Runnable() {
      public void run() {

        long populationCounter = 1;
        long totalPopulationCounter = 1;
        boolean outOfTime = false;
        boolean canceled = false;


        final ArrayList<Float> fitnessCapture = new ArrayList<Float>();
        String solutionCapture;

        long timeToRun = Settings.getExecutionLength() * 60000; // minutes to ms
        final long startTime = System.currentTimeMillis();

        ExpressionTree[] population = ExpressionTreeFactory.getRandomExpressionTreePopulation();
        ExpressionTreeFitnessComparator comparator = new ExpressionTreeFitnessComparator();

        Arrays.sort(population, comparator);

        fitnessCapture.add(ExpressionTreeFitnessTrainer.getFitness(population[0]));
        solutionCapture = population[0].getStringRepresentation();

        while (ExpressionTreeFitnessTrainer.getFitness(population[0]) > Settings.getFitnessThreshold()) {

          if (System.currentTimeMillis() - startTime >= timeToRun) {
            outOfTime = true;
            break;
          }

          if (!running) {
            canceled = true;
            break;
          }

          if (populationCounter == 1000) {
            fitnessCapture.clear();
            population = ExpressionTreeFactory.getRandomExpressionTreePopulation();
            populationCounter = 1;
            totalPopulationCounter = totalPopulationCounter + 1;
          } else {
            population = ExpressionTreeFactory.getNextExpressionTreePopulation(population[0], population[1]);
            populationCounter = populationCounter + 1;
            totalPopulationCounter = totalPopulationCounter + 1;
          }


          Arrays.sort(population, comparator);

          fitnessCapture.add(ExpressionTreeFitnessTrainer.getFitness(population[0]));
          solutionCapture = population[0].getStringRepresentation();
        }


        if (outOfTime || canceled) {
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              running = false;
              cancelPanel.setVisible(false);
            }
          });
        } else {

          final long popCounter = populationCounter;
          final String popSolution = solutionCapture;

          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              for (int i = 0; i < fitnessCapture.size(); i++) {
                final float fitness = fitnessCapture.get(i);
                dataset.addValue(fitness, series, String.valueOf(i + 1));
              }
              status.setText("Completed in " + popCounter + " populations (" + ((System.currentTimeMillis() - startTime) / 1000f) + " seconds)");
              solution.setText(popSolution);
              chart.setNotify(true);
              chartFrame.setVisible(true);
              running = false;
              cancelPanel.setVisible(false);
            }
          });
        }
      }
    };

    dataset.clear();
    chart.setNotify(false);
    running = true;

    new Thread(runnable).start();

    cancelPanel.setLocationRelativeTo(this);
    cancelPanel.setVisible(true);
  }

  private void btnClearMouseReleased(MouseEvent evt) {
    spnMaxTreeDepth.setValue(new Integer(5));
    spnPopulationSize.setValue(new Integer(10));
    spnAcceptFitnessVal.setValue(new Integer(0));
    spnMaxRunTime.setValue(new Integer(15));
    txtTerminatDataSet.setText("");
  }


}