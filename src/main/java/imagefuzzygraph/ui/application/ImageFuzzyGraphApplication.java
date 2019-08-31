package imagefuzzygraph.ui.application;

import java.awt.Dimension;
import java.awt.Toolkit;
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

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        ImageFuzzyGraphFrame window = new ImageFuzzyGraphFrame();
        window.setSize(screenDimension.width, screenDimension.height);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
