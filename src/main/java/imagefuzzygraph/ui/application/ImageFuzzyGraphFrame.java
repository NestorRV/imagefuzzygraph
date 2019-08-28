package imagefuzzygraph.ui.application;

import imagefuzzygraph.algorithm.FuzzyGraphMatching;
import imagefuzzygraph.data.AggregationOperator;
import imagefuzzygraph.data.AggregationOperators;
import imagefuzzygraph.data.ListOfMatches;
import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.graphdb.GraphDatabase;
import imagefuzzygraph.ui.elements.MatchingAlgorithmPreferencesDialog;
import imagefuzzygraph.ui.elements.GraphPlotter;
import imagefuzzygraph.ui.elements.ImageListInternalFrame;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Class representing the main JFrame of the application.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
class ImageFuzzyGraphFrame extends javax.swing.JFrame {

    private final GraphDatabase sourceGraphDatabase;
    private Graph queryGraph;
    private double bestSimilarity;
    private int bestGraph;
    private String sourceDatabasePath;
    private ArrayList<Tuple<Integer, Double>> inclusionDegrees;
    private final Comparator<Tuple<Integer, Double>> tupleComparator = (Tuple<Integer, Double> a, Tuple<Integer, Double> b) -> a.getSecond().compareTo(b.getSecond());

    /**
     * Create main window.
     */
    public ImageFuzzyGraphFrame() {
        initComponents();
        setIconImage((new ImageIcon(getClass().getResource("/icons/unfold.png"))).getImage());
        this.sourceGraphDatabase = new GraphDatabase();
        this.sourceDatabasePath = "";
        this.queryGraph = null;
        this.bestSimilarity = Double.NEGATIVE_INFINITY;
        this.bestGraph = -1;
        this.inclusionDegrees = new ArrayList<>();
        this.changeDBButtonsVisibility(false);
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
     * Get the graph of the current selected image frame (ImageInternalFrame).
     *
     * @return Get the graph of the current selected image frame
     * (ImageInternalFrame).
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
     * Method to change the visibility of the DataBase section buttons. 
     * 
     * @param visibility new visibility of those buttons.
     */
    private void changeDBButtonsVisibility(boolean visibility){
        this.saveSourceDBButton.setEnabled(visibility);
        this.plotRandomGraphSourceDBButton.setEnabled(visibility);
        this.plotSourceDatabaseButton.setEnabled(visibility);
        this.matchingAlgorithmPreferencesButton.setEnabled(visibility);
        this.matchingButton.setEnabled(visibility);
        this.viewMatchesButton.setEnabled(visibility);
        this.drawSortedMatchingsButton.setEnabled(visibility);
    }
    
    /**
     * Method to plot a graph in an JInternalFrame.
     * 
     * @param graph              graph to be plotted.
     * @param internalFrameTitle title of the JInternalFrame.
     */
    private void plotGraphInInternalFrame(Graph graph, String internalFrameTitle){
        JInternalFrame internalFrame = new JInternalFrame(internalFrameTitle, true, true, true);
        internalFrame.setSize(400, 400);
        internalFrame.setBackground(Color.WHITE);
        GraphPlotter gp = new GraphPlotter(graph, internalFrame.getWidth(), internalFrame.getHeight());
        internalFrame.add(gp);
        this.desktop.add(internalFrame);
        internalFrame.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPanelCentral = new javax.swing.JSplitPane();
        desktop = new javax.swing.JDesktopPane();
        showPanelInfo = new javax.swing.JLabel();
        tabbedPaneInfo = new javax.swing.JTabbedPane();
        panelOutput = new javax.swing.JPanel();
        toolsPanel = new javax.swing.JPanel();
        dbToolBar = new javax.swing.JToolBar();
        createSourceDBButton = new javax.swing.JButton();
        openSourceDBButton = new javax.swing.JButton();
        saveSourceDBButton = new javax.swing.JButton();
        plotRandomGraphSourceDBButton = new javax.swing.JButton();
        plotSourceDatabaseButton = new javax.swing.JButton();
        queryParametersToolBar = new javax.swing.JToolBar();
        matchingAlgorithmPreferencesButton = new javax.swing.JButton();
        matchingButton = new javax.swing.JButton();
        drawSortedMatchingsButton = new javax.swing.JButton();
        viewMatchesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ImageFuzzyGraph");
        setName("PrincipalWindow"); // NOI18N

        splitPanelCentral.setDividerLocation(1.0);
        splitPanelCentral.setDividerSize(3);
        splitPanelCentral.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPanelCentral.setPreferredSize(new java.awt.Dimension(0, 0));
        splitPanelCentral.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPanelCentralPropertyChange(evt);
            }
        });

        desktop.setBackground(java.awt.Color.lightGray);
        desktop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unfold.png"))); // NOI18N
        showPanelInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPanelInfoMousePressed(evt);
            }
        });

        desktop.setLayer(showPanelInfo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopLayout.createSequentialGroup()
                .addGap(0, 1094, Short.MAX_VALUE)
                .addComponent(showPanelInfo))
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showPanelInfo))
        );

        splitPanelCentral.setTopComponent(desktop);

        tabbedPaneInfo.setMinimumSize(new java.awt.Dimension(0, 0));
        tabbedPaneInfo.setPreferredSize(new java.awt.Dimension(0, 0));

        panelOutput.setMinimumSize(new java.awt.Dimension(0, 0));
        panelOutput.setPreferredSize(new java.awt.Dimension(0, 0));
        panelOutput.setLayout(new java.awt.BorderLayout());
        tabbedPaneInfo.addTab("Output", panelOutput);

        splitPanelCentral.setBottomComponent(tabbedPaneInfo);

        getContentPane().add(splitPanelCentral, java.awt.BorderLayout.CENTER);

        toolsPanel.setAlignmentX(0.0F);
        toolsPanel.setAlignmentY(0.0F);
        toolsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dbToolBar.setRollover(true);

        createSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create-database.png"))); // NOI18N
        createSourceDBButton.setToolTipText("Generate source database");
        createSourceDBButton.setFocusable(false);
        createSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createSourceDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSourceDBButtonActionPerformed(evt);
            }
        });
        dbToolBar.add(createSourceDBButton);

        openSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/load-database.png"))); // NOI18N
        openSourceDBButton.setToolTipText("Open a source database");
        openSourceDBButton.setFocusable(false);
        openSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openSourceDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSourceDBButtonActionPerformed(evt);
            }
        });
        dbToolBar.add(openSourceDBButton);

        saveSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-database.png"))); // NOI18N
        saveSourceDBButton.setToolTipText("Save the source database");
        saveSourceDBButton.setFocusable(false);
        saveSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveSourceDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSourceDBButtonActionPerformed(evt);
            }
        });
        dbToolBar.add(saveSourceDBButton);

        plotRandomGraphSourceDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/random.png"))); // NOI18N
        plotRandomGraphSourceDBButton.setToolTipText("Plot random graph from source database");
        plotRandomGraphSourceDBButton.setFocusable(false);
        plotRandomGraphSourceDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotRandomGraphSourceDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotRandomGraphSourceDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotRandomGraphSourceDBButtonActionPerformed(evt);
            }
        });
        dbToolBar.add(plotRandomGraphSourceDBButton);

        plotSourceDatabaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw.png"))); // NOI18N
        plotSourceDatabaseButton.setToolTipText("Plot all the graphs in the source database");
        plotSourceDatabaseButton.setFocusable(false);
        plotSourceDatabaseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotSourceDatabaseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotSourceDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotSourceDatabaseButtonActionPerformed(evt);
            }
        });
        dbToolBar.add(plotSourceDatabaseButton);

        toolsPanel.add(dbToolBar);

        queryParametersToolBar.setRollover(true);

        matchingAlgorithmPreferencesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        matchingAlgorithmPreferencesButton.setToolTipText("Set matching algorithm preferences");
        matchingAlgorithmPreferencesButton.setFocusable(false);
        matchingAlgorithmPreferencesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingAlgorithmPreferencesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingAlgorithmPreferencesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchingAlgorithmPreferencesButtonActionPerformed(evt);
            }
        });
        queryParametersToolBar.add(matchingAlgorithmPreferencesButton);

        matchingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compare.png"))); // NOI18N
        matchingButton.setToolTipText("Perform the matching algorithm");
        matchingButton.setFocusable(false);
        matchingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchingButtonActionPerformed(evt);
            }
        });
        queryParametersToolBar.add(matchingButton);

        drawSortedMatchingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw-sorted-matchings.png"))); // NOI18N
        drawSortedMatchingsButton.setToolTipText("Plot the graphs in the database sorted by the inclussion degree with the query graph.");
        drawSortedMatchingsButton.setFocusable(false);
        drawSortedMatchingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        drawSortedMatchingsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        drawSortedMatchingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawSortedMatchingsButtonActionPerformed(evt);
            }
        });
        queryParametersToolBar.add(drawSortedMatchingsButton);

        viewMatchesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view-matches.png"))); // NOI18N
        viewMatchesButton.setToolTipText("Draw the matching between the queryGraph and the best matching one");
        viewMatchesButton.setFocusable(false);
        viewMatchesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewMatchesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewMatchesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMatchesButtonActionPerformed(evt);
            }
        });
        queryParametersToolBar.add(viewMatchesButton);

        toolsPanel.add(queryParametersToolBar);

        getContentPane().add(toolsPanel, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void splitPanelCentralPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPanelCentralPropertyChange
        if (evt.getPropertyName().equals("dividerLocation")) {
            float dividerLocation = (float) splitPanelCentral.getDividerLocation() / splitPanelCentral.getMaximumDividerLocation();
            if (dividerLocation >= 1) {
                showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/unfold.png")));
            } else {
                showPanelInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fold.png")));
            }
        }
    }//GEN-LAST:event_splitPanelCentralPropertyChange

    private void showPanelInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPanelInfoMousePressed
        float dividerLocation = (float) splitPanelCentral.getDividerLocation() / splitPanelCentral.getMaximumDividerLocation();
        if (dividerLocation >= 1) {
            splitPanelCentral.setDividerLocation(0.8);
        } else {
            splitPanelCentral.setDividerLocation(1.0);
        }
    }//GEN-LAST:event_showPanelInfoMousePressed

    private void plotRandomGraphSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotRandomGraphSourceDBButtonActionPerformed
        if (this.sourceGraphDatabase.size() > 0) {
            int selectedGraph = new Random().nextInt(this.sourceGraphDatabase.size());
            this.plotGraphInInternalFrame(this.sourceGraphDatabase.get(selectedGraph), "Graph " + selectedGraph);
        }
    }//GEN-LAST:event_plotRandomGraphSourceDBButtonActionPerformed

    private void openSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSourceDBButtonActionPerformed
        JFileChooser dlg = new JFileChooser(new File(System.getProperty("user.dir")));
        dlg.setMultiSelectionEnabled(true);
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            java.awt.Cursor cursor = this.getCursor();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            File file = dlg.getSelectedFile();
            try {
                this.sourceDatabasePath = file.getAbsolutePath();
                this.sourceGraphDatabase.readDatabase(this.sourceDatabasePath);
                this.changeDBButtonsVisibility(true);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            setCursor(cursor);
        }
    }//GEN-LAST:event_openSourceDBButtonActionPerformed

    private void saveSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSourceDBButtonActionPerformed
        try {
            java.awt.Cursor cursor = this.getCursor();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            this.sourceGraphDatabase.saveDatabase(this.sourceDatabasePath);
            setCursor(cursor);
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_saveSourceDBButtonActionPerformed

    private void matchingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingButtonActionPerformed
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        this.queryGraph = this.getSelectedGraph();
        if (this.queryGraph != null) {
            double threshold = MatchingAlgorithmPreferences.getThreshold();
            AggregationOperator aggregationOperator;
            if (MatchingAlgorithmPreferences.getAggregationOperator().equals("atLeast")) {
                aggregationOperator = AggregationOperators.atLeast(MatchingAlgorithmPreferences.getAggregationOperatorPercentage());
            } else {
                aggregationOperator = AggregationOperators.all();
            }

            this.inclusionDegrees = new ArrayList<>();
            for (int i = 0; i < this.sourceGraphDatabase.size(); i++) {
                double inclusionDegree = fuzzyGraphMatching.greedyInclusion(this.sourceGraphDatabase.get(i), this.queryGraph, threshold, aggregationOperator);
                inclusionDegrees.add(new Tuple<>(i, inclusionDegree));
            }
            
            Collections.sort(this.inclusionDegrees, Collections.reverseOrder(this.tupleComparator));
            
            this.bestGraph = this.inclusionDegrees.get(0).getFirst();
            this.bestSimilarity = this.inclusionDegrees.get(0).getSecond();
            this.plotGraphInInternalFrame(this.sourceGraphDatabase.get(this.bestGraph), "Graph " + this.bestGraph + ". Inclusion degree: " + this.bestSimilarity);
        }
    }//GEN-LAST:event_matchingButtonActionPerformed

    private void createSourceDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSourceDBButtonActionPerformed
        try {
            this.sourceGraphDatabase.buildDatabase("source");
            this.sourceDatabasePath = System.getProperty("user.dir") + "/database.json";
            this.changeDBButtonsVisibility(true);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            Logger.getLogger(ImageFuzzyGraphFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createSourceDBButtonActionPerformed

    private void matchingAlgorithmPreferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingAlgorithmPreferencesButtonActionPerformed
        MatchingAlgorithmPreferencesDialog mapd = new MatchingAlgorithmPreferencesDialog(this);
        mapd.showDialog();
    }//GEN-LAST:event_matchingAlgorithmPreferencesButtonActionPerformed

    private void plotSourceDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotSourceDatabaseButtonActionPerformed
        ImageListInternalFrame listFrame = new ImageListInternalFrame();
        listFrame.setSize(800, 430);
        listFrame.setTitle("Database graphs.");
        for (Graph graph : this.sourceGraphDatabase) {
            listFrame.add(new GraphPlotter(graph, 400, 400).getImageGraph(), graph.getId());
        }

        this.desktop.add(listFrame);
        listFrame.setVisible(true);
    }//GEN-LAST:event_plotSourceDatabaseButtonActionPerformed

    private void viewMatchesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMatchesButtonActionPerformed
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        if (this.queryGraph != null && this.bestGraph != -1) {
            double threshold = MatchingAlgorithmPreferences.getThreshold();
            Tuple<ListOfMatches, ListOfMatches> matches = fuzzyGraphMatching.greedyMatching(this.queryGraph, this.sourceGraphDatabase.get(this.bestGraph), threshold);
            JInternalFrame internalFrame = new JInternalFrame("Matchings found.", true, true, true);
            internalFrame.setSize(800, 400);
            internalFrame.setBackground(Color.WHITE);
            GraphPlotter gp = new GraphPlotter(this.queryGraph, this.sourceGraphDatabase.get(this.bestGraph), matches, internalFrame.getWidth(), internalFrame.getHeight());
            internalFrame.add(gp);
            this.desktop.add(internalFrame);
            internalFrame.setVisible(true);
        }
    }//GEN-LAST:event_viewMatchesButtonActionPerformed

    private void drawSortedMatchingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawSortedMatchingsButtonActionPerformed
        ImageListInternalFrame listFrame = new ImageListInternalFrame();
        listFrame.setSize(800, 430);
        listFrame.setTitle("Sorted matchings.");
        for(Tuple<Integer, Double> match: this.inclusionDegrees) {
            listFrame.add(new GraphPlotter(this.sourceGraphDatabase.get(match.getFirst()), 400, 400).getImageGraph(), "Inclussion degree: " + match.getSecond());
        }

        this.desktop.add(listFrame);
        listFrame.setVisible(true);
    }//GEN-LAST:event_drawSortedMatchingsButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createSourceDBButton;
    private javax.swing.JToolBar dbToolBar;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton drawSortedMatchingsButton;
    private javax.swing.JButton matchingAlgorithmPreferencesButton;
    private javax.swing.JButton matchingButton;
    private javax.swing.JButton openSourceDBButton;
    private javax.swing.JPanel panelOutput;
    private javax.swing.JButton plotRandomGraphSourceDBButton;
    private javax.swing.JButton plotSourceDatabaseButton;
    private javax.swing.JToolBar queryParametersToolBar;
    private javax.swing.JButton saveSourceDBButton;
    private javax.swing.JLabel showPanelInfo;
    public javax.swing.JSplitPane splitPanelCentral;
    private javax.swing.JTabbedPane tabbedPaneInfo;
    private javax.swing.JPanel toolsPanel;
    private javax.swing.JButton viewMatchesButton;
    // End of variables declaration//GEN-END:variables
}
