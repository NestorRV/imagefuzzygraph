package imagefuzzygraph.ui.application;

import imagefuzzygraph.algorithm.FuzzyGraphMatching;
import imagefuzzygraph.data.AggregationOperator;
import imagefuzzygraph.data.AggregationOperators;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.graphdb.GraphDatabase;
import imagefuzzygraph.ui.elements.MatchingAlgorithmPreferencesDialog;
import imagefuzzygraph.ui.elements.GraphPlotter;
import imagefuzzygraph.ui.elements.ImageListInternalFrame;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

    private final GraphDatabase graphDatabase;
    private String databasePath = "";

    /**
     * Create main window.
     */
    public ImageFuzzyGraphFrame() {
        initComponents();
        setIconImage((new ImageIcon(getClass().getResource("/icons/unfold.png"))).getImage());
        this.graphDatabase = new GraphDatabase();

        this.saveDBButton.setEnabled(false);
        this.plotRandomGraphButton.setEnabled(false);
        this.plotDatabaseButton.setEnabled(false);
        this.matchingAlgorithmPreferencesButton.setEnabled(false);
        this.matchingButton.setEnabled(false);
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
            JOptionPane.showInternalMessageDialog(desktop, "A graph must be selected", "Graph", JOptionPane.INFORMATION_MESSAGE);
        }

        return graph;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPanelCentral = new javax.swing.JSplitPane();
        desktop = new javax.swing.JDesktopPane();
        showPanelInfo = new javax.swing.JLabel();
        tabbedPaneInfo = new javax.swing.JTabbedPane();
        panelOutput = new javax.swing.JPanel();
        toolsPanel = new javax.swing.JPanel();
        dbToolBar = new javax.swing.JToolBar();
        createDBButton = new javax.swing.JButton();
        openDBButton = new javax.swing.JButton();
        saveDBButton = new javax.swing.JButton();
        plotRandomGraphButton = new javax.swing.JButton();
        plotDatabaseButton = new javax.swing.JButton();
        queryParametersToolBar = new javax.swing.JToolBar();
        matchingAlgorithmPreferencesButton = new javax.swing.JButton();
        matchingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ImageFuzzyGraph");
        setName("PrincipalWindow"); // NOI18N

        splitPanelCentral.setDividerLocation(1.0);
        splitPanelCentral.setDividerSize(3);
        splitPanelCentral.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPanelCentral.setPreferredSize(new java.awt.Dimension(0, 0));
        splitPanelCentral.addPropertyChangeListener(this::splitPanelCentralPropertyChange);

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

        createDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create-database.png"))); // NOI18N
        createDBButton.setToolTipText("Generate database");
        createDBButton.setFocusable(false);
        createDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createDBButton.addActionListener(this::createDBButtonActionPerformed);
        dbToolBar.add(createDBButton);

        openDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/load-database.png"))); // NOI18N
        openDBButton.setToolTipText("Open a database");
        openDBButton.setFocusable(false);
        openDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openDBButton.addActionListener(this::openDBButtonActionPerformed);
        dbToolBar.add(openDBButton);

        saveDBButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-database.png"))); // NOI18N
        saveDBButton.setToolTipText("Save the database");
        saveDBButton.setFocusable(false);
        saveDBButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveDBButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveDBButton.addActionListener(this::saveDBButtonActionPerformed);
        dbToolBar.add(saveDBButton);

        plotRandomGraphButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/random.png"))); // NOI18N
        plotRandomGraphButton.setToolTipText("Plot random graph from database");
        plotRandomGraphButton.setFocusable(false);
        plotRandomGraphButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotRandomGraphButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotRandomGraphButton.addActionListener(this::plotRandomGraphButtonActionPerformed);
        dbToolBar.add(plotRandomGraphButton);

        plotDatabaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/draw.png"))); // NOI18N
        plotDatabaseButton.setToolTipText("Plot all the graphs in the database");
        plotDatabaseButton.setFocusable(false);
        plotDatabaseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        plotDatabaseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        plotDatabaseButton.addActionListener(this::plotDatabaseButtonActionPerformed);
        dbToolBar.add(plotDatabaseButton);

        toolsPanel.add(dbToolBar);

        queryParametersToolBar.setRollover(true);

        matchingAlgorithmPreferencesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        matchingAlgorithmPreferencesButton.setToolTipText("Set matching algorithm preferences");
        matchingAlgorithmPreferencesButton.setFocusable(false);
        matchingAlgorithmPreferencesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingAlgorithmPreferencesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingAlgorithmPreferencesButton.addActionListener(this::matchingAlgorithmPreferencesButtonActionPerformed);
        queryParametersToolBar.add(matchingAlgorithmPreferencesButton);

        matchingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/compare.png"))); // NOI18N
        matchingButton.setToolTipText("Label image");
        matchingButton.setFocusable(false);
        matchingButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        matchingButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        matchingButton.addActionListener(this::matchingButtonActionPerformed);
        queryParametersToolBar.add(matchingButton);

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

    private void plotRandomGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotRandomGraphButtonActionPerformed
        if (this.graphDatabase.size() > 0) {
            int selectedGraph = new Random().nextInt(this.graphDatabase.size());
            JInternalFrame internalFrame = new JInternalFrame("Graph " + selectedGraph, true, true, true);
            internalFrame.setSize(500, 500);
            internalFrame.setBackground(Color.WHITE);
            GraphPlotter gp = new GraphPlotter(this.graphDatabase.get(selectedGraph), internalFrame.getWidth(), internalFrame.getHeight());

            internalFrame.add(gp);
            this.desktop.add(internalFrame);
            internalFrame.setVisible(true);
        }
    }//GEN-LAST:event_plotRandomGraphButtonActionPerformed

    private void openDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDBButtonActionPerformed
        JFileChooser dlg = new JFileChooser(new File(System.getProperty("user.dir")));
        dlg.setMultiSelectionEnabled(true);
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            java.awt.Cursor cursor = this.getCursor();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            File file = dlg.getSelectedFile();
            try {
                this.databasePath = file.getAbsolutePath();
                this.graphDatabase.readDatabase(this.databasePath);
                this.saveDBButton.setEnabled(true);
                this.plotRandomGraphButton.setEnabled(true);
                this.plotDatabaseButton.setEnabled(true);
                this.matchingAlgorithmPreferencesButton.setEnabled(true);
                this.matchingButton.setEnabled(true);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            setCursor(cursor);
        }
    }//GEN-LAST:event_openDBButtonActionPerformed

    private void saveDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDBButtonActionPerformed
        try {
            java.awt.Cursor cursor = this.getCursor();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            this.graphDatabase.saveDatabase(this.databasePath);
            setCursor(cursor);
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_saveDBButtonActionPerformed

    private void matchingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingButtonActionPerformed
        FuzzyGraphMatching fuzzyGraphMatching = new FuzzyGraphMatching();
        Graph queryGraph = this.getSelectedGraph();
        if (queryGraph != null) {
            double threshold = MatchingAlgorithmPreferences.getThreshold();
            AggregationOperator aggregationOperator;
            if (MatchingAlgorithmPreferences.getAggregationOperator().equals("atLeast")) {
                aggregationOperator = AggregationOperators.atLeast(MatchingAlgorithmPreferences.getAggregationOperatorPercentage());
            } else {
                aggregationOperator = AggregationOperators.all();
            }

            double bestSimilarity = Double.NEGATIVE_INFINITY;
            int bestGraph = -1;
            for (int i = 0; i < this.graphDatabase.size(); i++) {
                double inclusionDegree = fuzzyGraphMatching.greedyInclusion(this.graphDatabase.get(i), queryGraph, threshold, aggregationOperator);
                if (inclusionDegree > bestSimilarity && inclusionDegree != 1.0) {
                    bestSimilarity = inclusionDegree;
                    bestGraph = i;
                }
            }

            JInternalFrame internalFrame = new JInternalFrame("Graph " + bestGraph + ". Inclusion degree: " + bestSimilarity, true, true, true);
            internalFrame.setSize(500, 500);
            internalFrame.setBackground(Color.WHITE);
            GraphPlotter gp = new GraphPlotter(this.graphDatabase.get(bestGraph), internalFrame.getWidth(), internalFrame.getHeight());
            internalFrame.add(gp);
            this.desktop.add(internalFrame);
            internalFrame.setVisible(true);
        }
    }//GEN-LAST:event_matchingButtonActionPerformed

    private void createDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDBButtonActionPerformed
        try {
            this.graphDatabase.buildDatabase();
            this.databasePath = System.getProperty("user.dir") + "/database.json";
            this.saveDBButton.setEnabled(true);
            this.plotRandomGraphButton.setEnabled(true);
            this.plotDatabaseButton.setEnabled(true);
            this.matchingAlgorithmPreferencesButton.setEnabled(true);
            this.matchingButton.setEnabled(true);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            Logger.getLogger(ImageFuzzyGraphFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createDBButtonActionPerformed

    private void matchingAlgorithmPreferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchingAlgorithmPreferencesButtonActionPerformed
        MatchingAlgorithmPreferencesDialog mapd = new MatchingAlgorithmPreferencesDialog(this);
        mapd.showDialog();
    }//GEN-LAST:event_matchingAlgorithmPreferencesButtonActionPerformed

    private void plotDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotDatabaseButtonActionPerformed
        ImageListInternalFrame listFrame = new ImageListInternalFrame();
        listFrame.setSize(800, 480);
        listFrame.setTitle("Database graphs.");
        for (Graph graph : this.graphDatabase) {
            listFrame.add(new GraphPlotter(graph, 500, 500).getImageGraph());
        }

        this.desktop.add(listFrame);
        listFrame.setVisible(true);

    }//GEN-LAST:event_plotDatabaseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createDBButton;
    private javax.swing.JToolBar dbToolBar;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JButton matchingAlgorithmPreferencesButton;
    private javax.swing.JButton matchingButton;
    private javax.swing.JButton openDBButton;
    private javax.swing.JPanel panelOutput;
    private javax.swing.JButton plotDatabaseButton;
    private javax.swing.JButton plotRandomGraphButton;
    private javax.swing.JToolBar queryParametersToolBar;
    private javax.swing.JButton saveDBButton;
    private javax.swing.JLabel showPanelInfo;
    public javax.swing.JSplitPane splitPanelCentral;
    private javax.swing.JTabbedPane tabbedPaneInfo;
    private javax.swing.JPanel toolsPanel;
    // End of variables declaration//GEN-END:variables
}
