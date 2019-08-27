package imagefuzzygraph.ui.application;

import javax.swing.*;

/**
 * Class to run the ImageFuzzyGraph application.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class ImageFuzzyGraphApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {
        }

        ImageFuzzyGraphFrame window = new ImageFuzzyGraphFrame();
        window.setSize(1300, 800);
        window.setLocationRelativeTo(null);
        window.splitPanelCentral.setDividerLocation(1.0);
        window.setVisible(true);
    }
}
