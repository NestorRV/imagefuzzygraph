package imagefuzzygraph.ui.elements;

import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.graph.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class to plot graphs.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphPlotter extends JComponent {

    private final Graph graph;
    private final BufferedImage imageGraph;

    /**
     * Construct a GraphPlotter
     *
     * @param graph graph to be plotted.
     * @param w     width of the JComponent.
     * @param h     height of the JComponent.
     */
    public GraphPlotter(Graph graph, int w, int h) {
        super();
        this.graph = graph;
        this.setSize(w, h);
        this.imageGraph = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = (Graphics2D) this.imageGraph.getGraphics();
        try {
            for (Node node : this.graph.getNodes()) {
                Tuple<Double, Double> location = node.getLocation();
                BufferedImage img = ImageIO.read(new File(node.getImagePath()));
                g2d.drawImage(img, location.getFirst().intValue() / 3, location.getSecond().intValue() / 3, img.getWidth() / 3, img.getHeight() / 3, null);
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.imageGraph, 0, 0, null);
    }

    /**
     * Plot the graph.
     */
    public void plot() {
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }

    /**
     * Return the graph contained in the GraphPlotter object.
     *
     * @return graph contained in the GraphPlotter object.
     */
    public Graph getGraph() {
        return graph;
    }
}
