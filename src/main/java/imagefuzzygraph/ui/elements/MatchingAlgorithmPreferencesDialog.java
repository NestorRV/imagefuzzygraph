package imagefuzzygraph.ui.elements;

import imagefuzzygraph.ui.application.MatchingAlgorithmPreferences;

/**
 * JDialog representing the setting of the matching algorithm.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class MatchingAlgorithmPreferencesDialog extends javax.swing.JDialog {

    private static final int CANCEL_OPTION = 1;
    private static final int APPROVE_OPTION = 0;
    private int returnStatus = CANCEL_OPTION;

    /**
     * Creates the jDialog.
     *
     * @param parent parent of the JDialog.
     */
    public MatchingAlgorithmPreferencesDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        MatchingAlgorithmPreferences.setThreshold(0.0);
        MatchingAlgorithmPreferences.setAggregationOperator("all");
        MatchingAlgorithmPreferences.setAggregationOperatorPercentage(1.0);
        this.aggregationOperatorPercentageSpinner.setVisible(false);
    }

    /**
     * Update MatchingAlgorithmPreferences.
     */
    private void updateMatchingAlgorithmPreferences() {
        MatchingAlgorithmPreferences.setThreshold((double) this.thresholdSpinner.getValue());
        int aggregationOperatorSelectedIndex = this.aggregationOperatorList.getSelectedIndex();
        String aggregationOperatorSelected = this.aggregationOperatorList.getItemAt(aggregationOperatorSelectedIndex);
        MatchingAlgorithmPreferences.setAggregationOperator(aggregationOperatorSelected);
        MatchingAlgorithmPreferences.setAggregationOperatorPercentage((double) this.aggregationOperatorPercentageSpinner.getValue());
    }

    /**
     * Display dialog as a modal.
     *
     * @return dialog status.
     */
    public int showDialog() {
        this.setVisible(true);
        return returnStatus;
    }

    /**
     * Update the status as close the dialog.
     *
     * @param retStatus dialog status.
     */
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShape = new javax.swing.JPanel();
        thresholdLabel = new javax.swing.JLabel();
        thresholdSpinner = new javax.swing.JSpinner();
        aggregationOperatorLabel = new javax.swing.JLabel();
        aggregationOperatorList = new javax.swing.JComboBox<>();
        aggregationOperatorPercentageSpinner = new javax.swing.JSpinner();
        buttonsPanel = new javax.swing.JPanel();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");

        panelShape.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matching Algorithms Preferences", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelShape.setPreferredSize(new java.awt.Dimension(150, 150));

        thresholdLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        thresholdLabel.setText("Threshold");
        thresholdLabel.setToolTipText("Threshold of the matching algorithm");

        thresholdSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.01d));
        thresholdSpinner.setToolTipText("");
        thresholdSpinner.setPreferredSize(new java.awt.Dimension(50, 15));

        aggregationOperatorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        aggregationOperatorLabel.setText("Aggregation Operator");
        aggregationOperatorLabel.setToolTipText("Aggregation Operator to be used");

        aggregationOperatorList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all", "atLeast" }));
        aggregationOperatorList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggregationOperatorListActionPerformed(evt);
            }
        });

        aggregationOperatorPercentageSpinner.setModel(new javax.swing.SpinnerNumberModel(0.8d, 0.0d, 1.0d, 0.05d));
        aggregationOperatorPercentageSpinner.setToolTipText("Percentage of nodes to match");

        javax.swing.GroupLayout panelShapeLayout = new javax.swing.GroupLayout(panelShape);
        panelShape.setLayout(panelShapeLayout);
        panelShapeLayout.setHorizontalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thresholdLabel)
                    .addComponent(aggregationOperatorLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aggregationOperatorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aggregationOperatorPercentageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelShapeLayout.setVerticalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thresholdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aggregationOperatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aggregationOperatorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aggregationOperatorPercentageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        thresholdSpinner.getAccessibleContext().setAccessibleDescription("Threshold");

        getContentPane().add(panelShape, java.awt.BorderLayout.CENTER);
        panelShape.getAccessibleContext().setAccessibleName("Matching algorithm preferences");

        buttonsPanel.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        acceptButton.setText("OK");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(acceptButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(329, 168));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        updateMatchingAlgorithmPreferences();
        doClose(APPROVE_OPTION);
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(CANCEL_OPTION);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void aggregationOperatorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggregationOperatorListActionPerformed
        this.aggregationOperatorPercentageSpinner.setVisible(this.aggregationOperatorList.getSelectedIndex() == 1);
    }//GEN-LAST:event_aggregationOperatorListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel aggregationOperatorLabel;
    private javax.swing.JComboBox<String> aggregationOperatorList;
    private javax.swing.JSpinner aggregationOperatorPercentageSpinner;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel panelShape;
    private javax.swing.JLabel thresholdLabel;
    private javax.swing.JSpinner thresholdSpinner;
    // End of variables declaration//GEN-END:variables
}
