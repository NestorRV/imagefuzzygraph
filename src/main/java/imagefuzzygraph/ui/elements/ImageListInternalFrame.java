package imagefuzzygraph.ui.elements;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import imagefuzzygraph.ui.application.ImageFuzzyGraphFrame;

/**
 * Class to represent a list of images.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class ImageListInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Init components.
     */
    public ImageListInternalFrame() {
        initComponents();
    }

    /**
     * Add an image to the list of images.
     *
     * @param image image to be added to the list of images.
     */
    public void add(BufferedImage image) {
        imageListPanel.add(image);
    }
    
    /**
     * Add an image to the list of images.
     *
     * @param image image to be added to the list of images.
     * @param label label of the image to be added to the list of images.
     */
    public void add(BufferedImage image, String label) {
        imageListPanel.add(image, label);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageListPanel = new jmr.iu.ImageListPanel(new Dimension(ImageFuzzyGraphFrame.GP_SIZE, ImageFuzzyGraphFrame.GP_SIZE));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().add(imageListPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jmr.iu.ImageListPanel imageListPanel;
    // End of variables declaration//GEN-END:variables
}
