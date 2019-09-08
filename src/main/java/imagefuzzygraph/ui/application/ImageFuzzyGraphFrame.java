package imagefuzzygraph.ui.application;

import imagefuzzygraph.algorithm.BuildGraph;
import imagefuzzygraph.algorithm.FuzzyGraphMatching;
import imagefuzzygraph.data.AggregationOperator;
import imagefuzzygraph.data.AggregationOperators;
import imagefuzzygraph.data.ListOfMatches;
import imagefuzzygraph.data.Region;
import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Edge;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.graphdb.GraphDatabase;
import imagefuzzygraph.graphdb.GraphExamples;
import imagefuzzygraph.ui.elements.MatchingAlgorithmPreferencesDialog;
import imagefuzzygraph.ui.elements.GraphPlotter;
import imagefuzzygraph.ui.elements.ImageListInternalFrame;
import imagefuzzygraph.ui.elements.TextSearchDialog;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Class representing the main JFrame of the application.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class ImageFuzzyGraphFrame extends javax.swing.JFrame {

    private final GraphDatabase sourceGraphDatabase;
    private final GraphDatabase queryGraphDatabase;
    private Graph queryGraph;
    private ArrayList<Tuple<Integer, Double>> inclusionDegrees;
    private final Comparator<Tuple<Integer, Double>> tupleComparator = Comparator.comparing(Tuple::getSecond);
    public static final int GP_SIZE = 300;
    private int lastGraphPlotted;

    /**
     * Create main window.
     */
    public ImageFuzzyGraphFrame() {
        initComponents();
        this.sourceGraphDatabase = new GraphDatabase();
        this.queryGraphDatabase = new GraphDatabase();
        this.queryGraph = null;
        this.inclusionDegrees = new ArrayList<>();
        this.lastGraphPlotted = 0;
        this.changeSourceDBButtonsVisibility(false);
        this.changeQueryDBButtonsVisibility(false);
        this.viewMatchesButton.setEnabled(false);
        this.drawSortedMatchesButton.setEnabled(false);
        this.explainMatchesButton.setEnabled(false);
        this.textSearchButton.setEnabled(false);
    }

    /**
     * Get current selected image frame (JInternalFrame).
     *
     * @return Get current selected image frame (JInternalFrame).
     */
    private JInternalFrame getSelectedImageFrame() {
        return desktop.getSelectedFrame();
    }

    /**
     * Get the textSearchGraph of the current selected image frame (ImageInternalFrame).
     *
     * @return Get the textSearchGraph of the current selected image frame
 (ImageInternalFrame).
     */
    private Graph getSelectedGraph() {
        Graph graph = null;
        JInternalFrame vi = this.getSelectedImageFrame();
        if (vi != null) {
            graph = new Graph(((GraphPlotter) vi.getContentPane().getComponent(0)).getGraph());
        }

        if (graph == null) {
            JOptionPane.showInternalMessageDialog(desktop, "A graph must be selected", "Graph", JOptionPane.ERROR_MESSAGE);
        }

        return graph;
    }
    
    /**
     * Method to change the visibility of the Source DataBase section buttons. 
     * 
     * @param visibility new visibility of those buttons.
     */
    private void changeSourceDBButtonsVisibility(boolean visibility){
        this.plotRandomGraphSourceDBButton.setEnabled(visibility);
        this.plotSourceDBButton.setEnabled(visibility);
        this.matchingAlgorithmPreferencesButton.setEnabled(visibility);
        this.matchingButton.setEnabled(visibility);
        this.textSearchPreferencesButton.setEnabled(visibility);
    }
    
    /**
     * Method to change the visibility of the Query DataBase section buttons. 
     * 
     * @param visibility new visibility of those buttons.
     */
    private void changeQueryDBButtonsVisibility(boolean visibility){
        this.plotRandomGraphQueryDBButton.setEnabled(visibility);
        this.plotQueryDBButton.setEnabled(visibility);
        this.matchingAlgorithmPreferencesButton.setEnabled(visibility);
        this.matchingButton.setEnabled(visibility);
        this.plotNextGraphQueryDBButton.setEnabled(visibility);
    }
    
    /**
     * Method to plot a textSearchGraph in an JInternalFrame.
     * 
     * @param graph              textSearchGraph to be plotted.
     * @param internalFrameTitle title of the JInternalFrame.
     */
    private void plotGraphInInternalFrame(Graph graph, String internalFrameTitle) {
        JInternalFrame internalFrame = new JInternalFrame(internalFrameTitle, true, true, true);
        internalFrame.setSize(ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE);
        internalFrame.setBackground(Color.WHITE);
        GraphPlotter gp = new GraphPlotter();
        gp.plotGraph(graph, internalFrame.getWidth(), internalFrame.getHeight());
        internalFrame.add(gp);
        this.desktop.add(internalFrame);
        internalFrame.setVisible(true);
    }
    
    /**
     * Create a textSearchGraph database.
     * 
     * @param sourceOrQuery string representing if we want to build the source or the query database.
     */
    private void createDatabase(String sourceOrQuery) {
        try {
            if (sourceOrQuery.equals("source")) {
                this.sourceGraphDatabase.buildDatabase(sourceOrQuery);
                this.changeSourceDBButtonsVisibility(true);
            } else if (sourceOrQuery.equals("query")) {
                this.queryGraphDatabase.buildDatabase(sourceOrQuery);
                this.changeQueryDBButtonsVisibility(true);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            Logger.getLogger(ImageFuzzyGraphFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Load a textSearchGraph database.
     * 
     * @param sourceOrQuery string representing if we want to build the source or the query database.
     */
    private void loadDatabase(String sourceOrQuery) {
        JFileChooser dlg = new JFileChooser(new File(System.getProperty("user.dir")));
        dlg.setMultiSelectionEnabled(true);
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            java.awt.Cursor cursor = this.getCursor();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            File file = dlg.getSelectedFile();
            try {
                if (sourceOrQuery.equals("source")) {
                    this.sourceGraphDatabase.readDatabase(file.getAbsolutePath());
                    this.changeSourceDBButtonsVisibility(true);
                } else if (sourceOrQuery.equals("query")) {
                    this.queryGraphDatabase.readDatabase(file.getAbsolutePath());
                    this.changeQueryDBButtonsVisibility(true);
                }
                
            } catch (IOException ex) {
                System.err.println(ex);
            }
            setCursor(cursor);
        }
    }
    
    /**
     * Plot a random textSearchGraph from a textSearchGraph database.
     * 
     * @param sourceOrQuery string representing if we want to build the source or the query database.
     */
    private void plotRandomGraph(String sourceOrQuery){
        if (sourceOrQuery.equals("source")) {
            if (this.sourceGraphDatabase.size() > 0) {
                int selectedGraphIdx = new Random().nextInt(this.sourceGraphDatabase.size());
                Graph selectedGraph = this.sourceGraphDatabase.get(selectedGraphIdx);
                this.plotGraphInInternalFrame(selectedGraph, selectedGraph.getId());
            }
        } else if (sourceOrQuery.equals("query")) {
            if (this.queryGraphDatabase.size() > 0) {
                int selectedGraphIdx = new Random().nextInt(this.queryGraphDatabase.size());
                Graph selectedGraph = this.queryGraphDatabase.get(selectedGraphIdx);
                this.plotGraphInInternalFrame(selectedGraph, selectedGraph.getId());
            }
        }
    }
    
    /**
     * Plot all the graphs from a textSearchGraph database.
     * 
     * @param sourceOrQuery string representing if we want to build the source or the query database.
     */
    private void plotDatabase(String sourceOrQuery) {
        ImageListInternalFrame listFrame = new ImageListInternalFrame();
        GraphPlotter gp = new GraphPlotter();
  
        if (sourceOrQuery.equals("source")) {
            listFrame.setTitle("Source database graphs.");
            for (Graph graph : this.sourceGraphDatabase) {
                listFrame.add(gp.plotGraph(graph, ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE), graph.getId());
            }
        } else if (sourceOrQuery.equals("query")) {
            listFrame.setTitle("Query database graphs.");
            for (Graph graph : this.queryGraphDatabase) {
                listFrame.add(gp.plotGraph(graph, ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE), graph.getId());
            }
        }
        
        this.desktop.add(listFrame);
        listFrame.setVisible(true);
        Dimension listFrameSize = listFrame.getSize();
        listFrame.setSize(listFrameSize.height * 3, listFrameSize.height);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        toolsPanel = new javax.swing.JPanel();
        sourceDBToolBar = new javax.swing.JToolBar();
        generateRandomGraphSourceDBButton = new javax.swing.JButton();
        createSourceDBButton = new javax.swing.JButton();
        openSourceDBButton = new javax.swing.JButton();
        plotRandomGraphSourceDBButton = new javax.swing.JButton();
        plotSourceDBButton = new javax.swing.JButton();
        queryDBToolBar = new javax.swing.JToolBar();
        createQueryDBButton = new javax.swing.JButton();
        openQueryDBButton = new javax.swing.JButton();
        plotRandomGraphQueryDBButton = new javax.swing.JButton();
        plotNextGraphQueryDBButton = new javax.swing.JButton();
        plotQueryDBButton = new javax.swing.JButton();
        matchingAlgorithmToolBar = new javax.swing.JToolBar();
        matchingAlgorithmPreferencesButton = new javax.swing.JButton();
        matchingButton = new javax.swing.JButton();
        explainMatchesButton = new javax.swing.JButton();
        drawSortedMatchesButton = new javax.swing.JButton();
        viewMatchesButton = new javax.swing.JButton();
        matchingAlgorithmToolBar1 = new javax.swing.JToolBar();
        textSearchPreferencesButton = new javax.swing.JButton();
        textSearchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ImageFuzzyGraph");
        setName("PrincipalWindow"); // NOI18N

        desktop.setBackground(java.awt.Color.lightGray);
        desktop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1118, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        toolsPanel.setAlignmentX(0.0F);
        toolsPanel.setAlignmentY(0.0F);
        toolsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        sourceDBToolBar.setRollover(true);

        generateRandomGraphSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/random-database.png"))); // NOI18N
        generateRandomGraphSourceDBButton.setToolTipText("Plot random graph from source database");
        generateRandomGraphSourceDBButton.setFocusable(false);
        generateRandomGraphSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generateRandomGraphSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generateRandomGraphSourceDBButton.addActionListener(this::generateRandomGraphSourceDBButtonActionPerformed);
        sourceDBToolBar.add(generateRandomGraphSourceDBButton);

        createSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create-database.png"))); // NOI18N
        createSourceDBButton.setToolTipText("Generate source database");
        createSourceDBButton.setFocusable(false);
        createSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createSourceDBButton.addActionListener(this::createSourceDBButtonActionPerformed);
        sourceDBToolBar.add(createSourceDBButton);

        openSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/load-database.png"))); // NOI18N
        openSourceDBButton.setToolTipText("Open a source database");
        openSourceDBButton.setFocusable(false);
        openSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openSourceDBButton.addActionListener(this::openSourceDBButtonActionPerformed);
        sourceDBToolBar.add(openSourceDBButton);

        plotRandomGraphSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/random.png"))); // NOI18N
        plotRandomGraphSourceDBButton.setToolTipText("Plot random graph from source database");
        plotRandomGraphSourceDBButton.setFocusable(false);
        plotRandomGraphSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotRandomGraphSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotRandomGraphSourceDBButton.addActionListener(this::plotRandomGraphSourceDBButtonActionPerformed);
        sourceDBToolBar.add(plotRandomGraphSourceDBButton);

        plotSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw.png"))); // NOI18N
        plotSourceDBButton.setToolTipText("Plot all the graphs in the source database");
        plotSourceDBButton.setFocusable(false);
        plotSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotSourceDBButton.addActionListener(this::plotSourceDBButtonActionPerformed);
        sourceDBToolBar.add(plotSourceDBButton);

        toolsPanel.add(sourceDBToolBar);

        queryDBToolBar.setRollover(true);

        createQueryDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create-database.png"))); // NOI18N
        createQueryDBButton.setToolTipText("Generate source database");
        createQueryDBButton.setFocusable(false);
        createQueryDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createQueryDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createQueryDBButton.addActionListener(this::createQueryDBButtonActionPerformed);
        queryDBToolBar.add(createQueryDBButton);

        openQueryDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/load-database.png"))); // NOI18N
        openQueryDBButton.setToolTipText("Open a source database");
        openQueryDBButton.setFocusable(false);
        openQueryDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openQueryDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openQueryDBButton.addActionListener(this::openQueryDBButtonActionPerformed);
        queryDBToolBar.add(openQueryDBButton);

        plotRandomGraphQueryDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/random.png"))); // NOI18N
        plotRandomGraphQueryDBButton.setToolTipText("Plot random graph from source database");
        plotRandomGraphQueryDBButton.setFocusable(false);
        plotRandomGraphQueryDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotRandomGraphQueryDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotRandomGraphQueryDBButton.addActionListener(this::plotRandomGraphQueryDBButtonActionPerformed);
        queryDBToolBar.add(plotRandomGraphQueryDBButton);

        plotNextGraphQueryDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        plotNextGraphQueryDBButton.setToolTipText("Plot random graph from source database");
        plotNextGraphQueryDBButton.setFocusable(false);
        plotNextGraphQueryDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotNextGraphQueryDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotNextGraphQueryDBButton.addActionListener(this::plotNextGraphQueryDBButtonActionPerformed);
        queryDBToolBar.add(plotNextGraphQueryDBButton);

        plotQueryDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw.png"))); // NOI18N
        plotQueryDBButton.setToolTipText("Plot all the graphs in the source database");
        plotQueryDBButton.setFocusable(false);
        plotQueryDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotQueryDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotQueryDBButton.addActionListener(this::plotQueryDBButtonActionPerformed);
        queryDBToolBar.add(plotQueryDBButton);

        toolsPanel.add(queryDBToolBar);

        matchingAlgorithmToolBar.setRollover(true);

        matchingAlgorithmPreferencesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        matchingAlgorithmPreferencesButton.setToolTipText("Set matching algorithm preferences");
        matchingAlgorithmPreferencesButton.setFocusable(false);
        matchingAlgorithmPreferencesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingAlgorithmPreferencesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingAlgorithmPreferencesButton.addActionListener(this::matchingAlgorithmPreferencesButtonActionPerformed);
        matchingAlgorithmToolBar.add(matchingAlgorithmPreferencesButton);

        matchingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compare.png"))); // NOI18N
        matchingButton.setToolTipText("Perform the matching algorithm");
        matchingButton.setFocusable(false);
        matchingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingButton.addActionListener(this::matchingButtonActionPerformed);
        matchingAlgorithmToolBar.add(matchingButton);

        explainMatchesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/question.png"))); // NOI18N
        explainMatchesButton.setToolTipText("Perform the matching algorithm");
        explainMatchesButton.setFocusable(false);
        explainMatchesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        explainMatchesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        explainMatchesButton.addActionListener(this::explainMatchesButtonActionPerformed);
        matchingAlgorithmToolBar.add(explainMatchesButton);

        drawSortedMatchesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw-sorted-matches.png"))); // NOI18N
        drawSortedMatchesButton.setToolTipText("Plot the graphs in the database sorted by the inclussion degree with the query graph.");
        drawSortedMatchesButton.setFocusable(false);
        drawSortedMatchesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        drawSortedMatchesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        drawSortedMatchesButton.addActionListener(this::drawSortedMatchesButtonActionPerformed);
        matchingAlgorithmToolBar.add(drawSortedMatchesButton);

        viewMatchesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view-matches.png"))); // NOI18N
        viewMatchesButton.setToolTipText("Draw the matching between the queryGraph and the best matching one");
        viewMatchesButton.setFocusable(false);
        viewMatchesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewMatchesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewMatchesButton.addActionListener(this::viewMatchesButtonActionPerformed);
        matchingAlgorithmToolBar.add(viewMatchesButton);

        matchingAlgorithmToolBar1.setRollover(true);

        textSearchPreferencesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/text-search-preferences.png"))); // NOI18N
        textSearchPreferencesButton.setToolTipText("");
        textSearchPreferencesButton.setFocusable(false);
        textSearchPreferencesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textSearchPreferencesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        textSearchPreferencesButton.addActionListener(this::textSearchPreferencesButtonActionPerformed);
        matchingAlgorithmToolBar1.add(textSearchPreferencesButton);

        textSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/text-search.png"))); // NOI18N
        textSearchButton.setToolTipText("");
        textSearchButton.setFocusable(false);
        textSearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textSearchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        textSearchButton.addActionListener(this::textSearchButtonActionPerformed);
        matchingAlgorithmToolBar1.add(textSearchButton);

        matchingAlgorithmToolBar.add(matchingAlgorithmToolBar1);

        toolsPanel.add(matchingAlgorithmToolBar);

        getContentPane().add(toolsPanel, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void plotRandomGraphSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotRandomGraphSourceDBButtonActionPerformed
        this.plotRandomGraph("source");
    }//GEN-LAST:event_plotRandomGraphSourceDBButtonActionPerformed

    private void openSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSourceDBButtonActionPerformed
        this.loadDatabase("source");
    }//GEN-LAST:event_openSourceDBButtonActionPerformed

    /**
     * Query database to compare textSearchGraph with all the graphs in the database.
     * 
     * @param graph textSearchGraph to be compared with all the graphs in the database.
     */
    private void queryDatabase(Graph graph) {
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        AggregationOperator aggregationOperator;
        if (MatchingAlgorithmPreferences.getAggregationOperator().equals("atLeast")) {
            aggregationOperator = AggregationOperators.atLeast(MatchingAlgorithmPreferences.getAlpha(), MatchingAlgorithmPreferences.getBeta());
        } else {
            aggregationOperator = AggregationOperators.all();
        }
            
        this.inclusionDegrees = new ArrayList<>();
        for (int i = 0; i < this.sourceGraphDatabase.size(); i++) {
            double inclusionDegree = fuzzyGraphMatching.computeInclusion(this.sourceGraphDatabase.get(i), graph, aggregationOperator);
            inclusionDegrees.add(new Tuple<>(i, inclusionDegree));
        }

        this.inclusionDegrees.sort(Collections.reverseOrder(this.tupleComparator));
    }
    
    private void matchingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingButtonActionPerformed
        this.queryGraph = this.getSelectedGraph();
        if (this.queryGraph != null) {
            this.queryDatabase(queryGraph);
            
            Graph graph = this.sourceGraphDatabase.get(this.inclusionDegrees.get(0).getFirst());
            this.plotGraphInInternalFrame(graph, graph.getId() + ": " + this.inclusionDegrees.get(0).getSecond());
            this.viewMatchesButton.setEnabled(true);
            this.drawSortedMatchesButton.setEnabled(true);
            this.explainMatchesButton.setEnabled(true);
        }
    }//GEN-LAST:event_matchingButtonActionPerformed

    private void createSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSourceDBButtonActionPerformed
        this.createDatabase("source");
    }//GEN-LAST:event_createSourceDBButtonActionPerformed

    private void matchingAlgorithmPreferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingAlgorithmPreferencesButtonActionPerformed
        MatchingAlgorithmPreferencesDialog mapd = new MatchingAlgorithmPreferencesDialog(this);
        mapd.setTitle("Matching Algorithms Preferences");
        mapd.showDialog();
    }//GEN-LAST:event_matchingAlgorithmPreferencesButtonActionPerformed

    private void plotSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotSourceDBButtonActionPerformed
        this.plotDatabase("source");
    }//GEN-LAST:event_plotSourceDBButtonActionPerformed

    private void viewMatchesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMatchesButtonActionPerformed
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        if (this.queryGraph != null && !this.inclusionDegrees.isEmpty()) {
            int bestGraphIdx = this.inclusionDegrees.get(0).getFirst();
            Tuple<ListOfMatches, ListOfMatches> matches = fuzzyGraphMatching.computeMatching(this.sourceGraphDatabase.get(bestGraphIdx), this.queryGraph);
            JInternalFrame internalFrame = new JInternalFrame("Matches found.", true, true, true);
            internalFrame.setSize(ImageFuzzyGraphFrame.GP_SIZE * 2, ImageFuzzyGraphFrame.GP_SIZE);
            internalFrame.setBackground(Color.WHITE);
            GraphPlotter gp = new GraphPlotter();
            gp.plotMatches(this.queryGraph, this.sourceGraphDatabase.get(bestGraphIdx), matches, internalFrame.getWidth(), internalFrame.getHeight());
            internalFrame.add(gp);
            this.desktop.add(internalFrame);
            internalFrame.setVisible(true);
        }
    }//GEN-LAST:event_viewMatchesButtonActionPerformed

    private void drawSortedMatchesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawSortedMatchesButtonActionPerformed
        ImageListInternalFrame listFrame = new ImageListInternalFrame();
        listFrame.setTitle("Sorted matches.");
        GraphPlotter gp = new GraphPlotter();
        for(Tuple<Integer, Double> match: this.inclusionDegrees) {
            Graph graph = this.sourceGraphDatabase.get(match.getFirst());
            listFrame.add(gp.plotGraph(graph, ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE), graph.getId() + ": " + match.getSecond());
        }

        this.desktop.add(listFrame);
        listFrame.setVisible(true);
        Dimension listFrameSize = listFrame.getSize();
        listFrame.setSize(listFrameSize.height * 3, listFrameSize.height);
    }//GEN-LAST:event_drawSortedMatchesButtonActionPerformed

    private void createQueryDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createQueryDBButtonActionPerformed
        this.createDatabase("query");
    }//GEN-LAST:event_createQueryDBButtonActionPerformed

    private void openQueryDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openQueryDBButtonActionPerformed
        this.loadDatabase("query");
    }//GEN-LAST:event_openQueryDBButtonActionPerformed

    private void plotRandomGraphQueryDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotRandomGraphQueryDBButtonActionPerformed
        this.plotRandomGraph("query");
    }//GEN-LAST:event_plotRandomGraphQueryDBButtonActionPerformed

    private void plotQueryDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotQueryDBButtonActionPerformed
        this.plotDatabase("query");
    }//GEN-LAST:event_plotQueryDBButtonActionPerformed

    private void plotNextGraphQueryDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotNextGraphQueryDBButtonActionPerformed
        Graph selectedGraph = this.queryGraphDatabase.get(this.lastGraphPlotted);
        this.plotGraphInInternalFrame(selectedGraph, selectedGraph.getId());
        this.lastGraphPlotted = (this.lastGraphPlotted + 1) % this.queryGraphDatabase.size();
    }//GEN-LAST:event_plotNextGraphQueryDBButtonActionPerformed

    private void explainMatchesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_explainMatchesButtonActionPerformed
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        if (this.queryGraph != null && !this.inclusionDegrees.isEmpty()) {
            int bestGraphIdx = this.inclusionDegrees.get(0).getFirst();
            Graph sourceGraph = this.sourceGraphDatabase.get(bestGraphIdx);
            Map<String, Map<String, Double>> similarities = fuzzyGraphMatching.computeSimilarities(sourceGraph.getNodes(), this.queryGraph.getNodes());
            Tuple<ListOfMatches, ListOfMatches> matches = fuzzyGraphMatching.computeMatching(this.sourceGraphDatabase.get(bestGraphIdx), this.queryGraph, similarities);
            
            JInternalFrame internalFrame = new JInternalFrame("Matches explanation.", true, true, true);
            internalFrame.setSize(ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE);
            internalFrame.setVisible(true);
            
            StringBuilder areaTextBuilder = new StringBuilder();
            areaTextBuilder.append("Query edge -> Source edge\n");
            
            ListOfMatches edgesMatches = matches.getSecond();
            if (edgesMatches.size() > 0) {
                Map<String, Edge> sourceEdges = this.sourceGraphDatabase.get(bestGraphIdx).getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
                Map<String, Edge> queryEdges = this.queryGraph.getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
                for (Tuple<String, String> edgesMatch : edgesMatches) {
                    Edge sourceEdge = sourceEdges.get(edgesMatch.getFirst());
                    Edge queryEdge = queryEdges.get(edgesMatch.getSecond());
                    double inclusionDegree = fuzzyGraphMatching.fuzzyEdgeInclusionConsideringNodes(sourceEdge, queryEdge, similarities);
                    areaTextBuilder.append(queryEdge.getStartNodeId()).append("-").append(queryEdge.getEndNodeId()).append(" -> ");
                    areaTextBuilder.append(sourceEdge.getStartNodeId()).append("-").append(sourceEdge.getEndNodeId()).append(": ").append(inclusionDegree).append("\n");
                }
            } else {
                for (Tuple<String, String> nodesMatch : matches.getFirst()) {
                    double inclusionDegree = similarities.get(nodesMatch.getFirst()).get(nodesMatch.getSecond());
                    areaTextBuilder.append(nodesMatch.getFirst()).append(" -> ").append(nodesMatch.getSecond()).append(": ").append(inclusionDegree).append("\n");
                }
            }
            
            JTextArea textArea = new JTextArea(1, 1);
            textArea.setText(areaTextBuilder.toString());
            textArea.setEditable(false);
            textArea.setVisible(true);
            
            internalFrame.add(textArea);
            internalFrame.pack();
            this.desktop.add(internalFrame);
            internalFrame.moveToFront();
        }
    }//GEN-LAST:event_explainMatchesButtonActionPerformed

    private void generateRandomGraphSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateRandomGraphSourceDBButtonActionPerformed
        try {
            this.sourceGraphDatabase.buildRandomDatabase(1000);
            this.changeSourceDBButtonsVisibility(true);
            this.sourceGraphDatabase.saveDatabase(System.getProperty("user.dir") + "/random_database.json");
        } catch (IOException ex) {
            Logger.getLogger(ImageFuzzyGraphFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generateRandomGraphSourceDBButtonActionPerformed

    private void textSearchPreferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSearchPreferencesButtonActionPerformed
        TextSearchDialog tsd = new TextSearchDialog(this);
        tsd.setTitle("Text Search");
        tsd.showDialog();
        this.textSearchButton.setEnabled(true);
    }//GEN-LAST:event_textSearchPreferencesButtonActionPerformed

    private void textSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSearchButtonActionPerformed
        String firstObject = TextSearchPreferences.getFirstObject();
        String firstObjectColor = TextSearchPreferences.getFirstObjectColor();
        String secondObject = TextSearchPreferences.getSecondObject();
        String secondObjectColor = TextSearchPreferences.getSecondObjectColor();
        String relation = TextSearchPreferences.getRelation();
        
        GraphExamples graphExamples = new GraphExamples();
        BuildGraph bg = new BuildGraph();
        ArrayList<String> labels = new ArrayList<>(Arrays.asList(firstObject, secondObject));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(graphExamples.buildRegion(firstObject, firstObjectColor, 500.0, 500.0));
        
        switch (relation) {
            case "up":
                regions.add(graphExamples.buildRegion(secondObject, secondObjectColor, 500.0, 750.0));
                break;
            case "right":
                regions.add(graphExamples.buildRegion(secondObject, secondObjectColor, 250.0, 500.0));
                break;
            case "left":
                regions.add(graphExamples.buildRegion(secondObject, secondObjectColor, 750.0, 500.0));
                break;
            case "down":
                regions.add(graphExamples.buildRegion(secondObject, secondObjectColor, 500.0, 250.0));
                break;
            default:
                break;
        }
        
        try {
            Graph textSearchGraph = bg.buildGraph("text_search_graph", regions, labels);
            this.queryDatabase(textSearchGraph);
            this.queryGraph = textSearchGraph;
            Graph bestMatchGraph = this.sourceGraphDatabase.get(this.inclusionDegrees.get(0).getFirst());
            this.plotGraphInInternalFrame(bestMatchGraph, bestMatchGraph.getId() + ": " + this.inclusionDegrees.get(0).getSecond());
            this.viewMatchesButton.setEnabled(true);
            this.drawSortedMatchesButton.setEnabled(true);
            this.explainMatchesButton.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(ImageFuzzyGraphFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_textSearchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createQueryDBButton;
    private javax.swing.JButton createSourceDBButton;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton drawSortedMatchesButton;
    private javax.swing.JButton explainMatchesButton;
    private javax.swing.JButton generateRandomGraphSourceDBButton;
    private javax.swing.JButton matchingAlgorithmPreferencesButton;
    private javax.swing.JToolBar matchingAlgorithmToolBar;
    private javax.swing.JToolBar matchingAlgorithmToolBar1;
    private javax.swing.JButton matchingButton;
    private javax.swing.JButton openQueryDBButton;
    private javax.swing.JButton openSourceDBButton;
    private javax.swing.JButton plotNextGraphQueryDBButton;
    private javax.swing.JButton plotQueryDBButton;
    private javax.swing.JButton plotRandomGraphQueryDBButton;
    private javax.swing.JButton plotRandomGraphSourceDBButton;
    private javax.swing.JButton plotSourceDBButton;
    private javax.swing.JToolBar queryDBToolBar;
    private javax.swing.JToolBar sourceDBToolBar;
    private javax.swing.JButton textSearchButton;
    private javax.swing.JButton textSearchPreferencesButton;
    private javax.swing.JPanel toolsPanel;
    private javax.swing.JButton viewMatchesButton;
    // End of variables declaration//GEN-END:variables
}
