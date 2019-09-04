package imagefuzzygraph.ui.elements;

import imagefuzzygraph.ui.application.TextSearchPreferences;

/**
 * JDialog used to perform a text search..
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class TextSearchDialog extends javax.swing.JDialog {

    private static final int CANCEL_OPTION = 1;
    private static final int APPROVE_OPTION = 0;
    private int returnStatus = CANCEL_OPTION;

    /**
     * Creates the jDialog.
     *
     * @param parent parent of the JDialog.
     */
    public TextSearchDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        TextSearchPreferences.setFirstObject("circle_1.0");
        TextSearchPreferences.setSecondObject("circle_1.0");
        TextSearchPreferences.setFirstObjectColor("blue1");
        TextSearchPreferences.setSecondObjectColor("blue1");
        TextSearchPreferences.setRelation("up");
    }

    /**
     * Update MatchingAlgorithmPreferences.
     */
    private void updateMatchingAlgorithmPreferences() {
        TextSearchPreferences.setFirstObject(this.firstObjectList.getSelectedItem().toString());
        TextSearchPreferences.setFirstObjectColor(this.firstObjectColorList.getSelectedItem().toString());
        TextSearchPreferences.setSecondObject(this.secondObjectList.getSelectedItem().toString());
        TextSearchPreferences.setSecondObjectColor(this.secondObjectColorList.getSelectedItem().toString());
        TextSearchPreferences.setRelation(this.relationList.getSelectedItem().toString());
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
        firstObjectLabel = new javax.swing.JLabel();
        firstObjectList = new javax.swing.JComboBox<>();
        firstObjectColorLabel = new javax.swing.JLabel();
        firstObjectColorList = new javax.swing.JComboBox<>();
        relationLabel = new javax.swing.JLabel();
        relationList = new javax.swing.JComboBox<>();
        secondObjectLabel = new javax.swing.JLabel();
        secondObjectList = new javax.swing.JComboBox<>();
        seconObjectColorLabel = new javax.swing.JLabel();
        secondObjectColorList = new javax.swing.JComboBox<>();
        buttonsPanel = new javax.swing.JPanel();
        acceptButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");

        panelShape.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TextSearch", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelShape.setPreferredSize(new java.awt.Dimension(150, 150));

        firstObjectLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        firstObjectLabel.setText("First object");
        firstObjectLabel.setToolTipText("Aggregation Operator to be used");

        firstObjectList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "circle_1.0", "cross_1.0", "heart_1.0", "square_0.8", "square_1.0", "star_1.0", "triangle_1.0" }));

        firstObjectColorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        firstObjectColorLabel.setText("First object color");
        firstObjectColorLabel.setToolTipText("Aggregation Operator to be used");

        firstObjectColorList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "blue1", "blue2", "blue3", "brown1", "brown2", "brown3", "green1", "green2", "green3", "orange1", "orange2", "orange3", "pink1", "pink2", "pink3", "purple1", "purple2", "purple3", "red1", "red2", "red3", "yellow1", "yellow2", "yellow3" }));

        relationLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        relationLabel.setText("Relation");
        relationLabel.setToolTipText("Aggregation Operator to be used");

        relationList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "up", "right", "left", "down" }));

        secondObjectLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        secondObjectLabel.setText("Second object");
        secondObjectLabel.setToolTipText("Aggregation Operator to be used");

        secondObjectList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "circle_1.0", "cross_1.0", "heart_1.0", "square_0.8", "square_1.0", "star_1.0", "triangle_1.0" }));

        seconObjectColorLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        seconObjectColorLabel.setText("Second object color");
        seconObjectColorLabel.setToolTipText("Aggregation Operator to be used");

        secondObjectColorList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "blue1", "blue2", "blue3", "brown1", "brown2", "brown3", "green1", "green2", "green3", "orange1", "orange2", "orange3", "pink1", "pink2", "pink3", "purple1", "purple2", "purple3", "red1", "red2", "red3", "yellow1", "yellow2", "yellow3" }));

        javax.swing.GroupLayout panelShapeLayout = new javax.swing.GroupLayout(panelShape);
        panelShape.setLayout(panelShapeLayout);
        panelShapeLayout.setHorizontalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstObjectLabel)
                    .addComponent(relationLabel)
                    .addComponent(secondObjectLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstObjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondObjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addComponent(firstObjectColorLabel)
                        .addGap(25, 25, 25)
                        .addComponent(firstObjectColorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addComponent(seconObjectColorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secondObjectColorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShapeLayout.setVerticalGroup(
            panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShapeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstObjectColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstObjectColorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seconObjectColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondObjectColorList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelShapeLayout.createSequentialGroup()
                        .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstObjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstObjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(relationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secondObjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondObjectList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelShape, java.awt.BorderLayout.CENTER);

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

        setSize(new java.awt.Dimension(471, 169));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        updateMatchingAlgorithmPreferences();
        doClose(APPROVE_OPTION);
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(CANCEL_OPTION);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel firstObjectColorLabel;
    private javax.swing.JComboBox<String> firstObjectColorList;
    private javax.swing.JLabel firstObjectLabel;
    private javax.swing.JComboBox<String> firstObjectList;
    private javax.swing.JPanel panelShape;
    private javax.swing.JLabel relationLabel;
    private javax.swing.JComboBox<String> relationList;
    private javax.swing.JLabel seconObjectColorLabel;
    private javax.swing.JComboBox<String> secondObjectColorList;
    private javax.swing.JLabel secondObjectLabel;
    private javax.swing.JComboBox<String> secondObjectList;
    // End of variables declaration//GEN-END:variables
}
