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
        MatchingAlgorithmPreferences.setAggregationOperator("all");
        MatchingAlgorithmPreferences.setAlpha(1.0);
        this.alphaSpinner.setVisible(false);
        this.betaSpinner.setVisible(false);
        this.alphaLabel.setVisible(false);
        this.betaLabel.setVisible(false);
    }

    /**
     * Update MatchingAlgorithmPreferences.
     */
    private void updateMatchingAlgorithmPreferences() {
        int aggregationOperatorSelectedIndex = this.aggregationOperatorList.getSelectedIndex();
        String aggregationOperatorSelected = this.aggregationOperatorList.getItemAt(aggregationOperatorSelectedIndex);
        MatchingAlgorithmPreferences.setAggregationOperator(aggregationOperatorSelected);
        MatchingAlgorithmPreferences.setAlpha((double) this.alphaSpinner.getValue());
        MatchingAlgorithmPreferences.setBeta((double) this.betaSpinner.getValue());
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShape = new javax.swing.JPanel();
        aggregationOperatorLabel = new javax.swing.JLabel();
        aggregationOperatorList = new javax.swing.JComboBox<>();
        alphaSpinner = new javax.swing.JSpinner();
        alphaLabel = new javax.swing.JLabel();
        betaLabel = new javax.swing.JLabel();
        betaSpinner = new javax.swing.JSpinner();
        buttonsPanel = new javax.swing.JPanel();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");

        panelShape.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matching Algorithms Preferences", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelShape.setPreferredSize(new java.awt.Dimension(150, 150));

        aggregationOperatorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        aggregationOperatorLabel.setText("Aggregation Operator");
        aggregationOperatorLabel.setToolTipText("Aggregation Operator to be used");

        aggregationOperatorList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all", "atLeast" }));
        aggregationOperatorList.addActionListener(this::aggregationOperatorListActionPerformed);

        alphaSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.005d));
        alphaSpinner.setToolTipText("");

        alphaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        alphaLabel.setText("alpha");
        alphaLabel.setToolTipText("");

        betaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        betaLabel.setText("beta");
        betaLabel.setToolTipText("");

        betaSpinner.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.0d, 1.0d, 0.005d));
        betaSpinner.setToolTipText("");

        javax.swing.GroupLayout panelShapeLayout = new javax.swing.GroupLayout(panelShape);
        panelShape.setLayout(panelShapeLayout);
        panelShapeLayout.setHorizontalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addComponent(aggregationOperatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aggregationOperatorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addComponent(alphaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addComponent(betaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(betaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        panelShapeLayout.setVerticalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aggregationOperatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aggregationOperatorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alphaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alphaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(betaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(betaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panelShape, java.awt.BorderLayout.CENTER);
        panelShape.getAccessibleContext().setAccessibleName("Matching algorithm preferences");

        buttonsPanel.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        acceptButton.setText("OK");
        acceptButton.addActionListener(this::acceptButtonActionPerformed);
        buttonsPanel.add(acceptButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        buttonsPanel.add(cancelButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(256, 183));
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
        this.alphaSpinner.setVisible(this.aggregationOperatorList.getSelectedIndex() == 1);
        this.betaSpinner.setVisible(this.aggregationOperatorList.getSelectedIndex() == 1);
        this.alphaLabel.setVisible(this.aggregationOperatorList.getSelectedIndex() == 1);
        this.betaLabel.setVisible(this.aggregationOperatorList.getSelectedIndex() == 1);
    }//GEN-LAST:event_aggregationOperatorListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel aggregationOperatorLabel;
    private javax.swing.JComboBox<String> aggregationOperatorList;
    private javax.swing.JLabel alphaLabel;
    private javax.swing.JSpinner alphaSpinner;
    private javax.swing.JLabel betaLabel;
    private javax.swing.JSpinner betaSpinner;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel panelShape;
    // End of variables declaration//GEN-END:variables
}
