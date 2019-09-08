package imagefuzzygraph.ui.elements;

import imagefuzzygraph.data.ListOfMatches;
import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.graph.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to plot graphs.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphPlotter extends JComponent {

    private Graph graph;
    private BufferedImage imageGraph;
    private final int resizeFactor = 4;

    /**
     * Construct a GraphPlotter
     */
    public GraphPlotter() {
        super();
    }

    /**
     * Plot a graph.
     *
     * @param graph graph to be plotted.
     * @param w     width of the JComponent.
     * @param h     height of the JComponent.
     * @return image of the graph.
     */
    public BufferedImage plotGraph(Graph graph, int w, int h) {
        this.graph = new Graph(graph);
        this.setSize(w, h);
        this.imageGraph = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) this.imageGraph.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.imageGraph.getWidth(), this.imageGraph.getHeight());
        this.addGraphToImage(g2d, this.graph, 0);
        g2d.dispose();
        return this.imageGraph;
    }

    /**
     * Plot matches between two graphs.
     *
     * @param queryGraph queryGraph to be plotted.
     * @param bestGraph  bestGraph to be plotted.
     * @param matches    matched between queryGraph and bestGraph.
     * @param w          width of the JComponent.
     * @param h          height of the JComponent.
     */
    public void plotMatches(Graph queryGraph, Graph bestGraph, Tuple<ListOfMatches, ListOfMatches> matches, int w, int h) {
        this.graph = new Graph(bestGraph);
        this.setSize(w, h);
        this.imageGraph = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) this.imageGraph.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.imageGraph.getWidth(), this.imageGraph.getHeight());

        this.addGraphToImage(g2d, queryGraph, 0);
        this.addGraphToImage(g2d, bestGraph, w / 2);

        Map<String, Node> queryNodesMap = queryGraph.getNodes().stream().collect(Collectors.toMap(Node::getId, s -> s));
        Map<String, Node> bestNodesMap = bestGraph.getNodes().stream().collect(Collectors.toMap(Node::getId, s -> s));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            for (Tuple<String, String> match : matches.getFirst()) {
                Node fromBest = bestNodesMap.get(match.getFirst());
                Node fromQuery = queryNodesMap.get(match.getSecond());
                BufferedImage fromBestImg = ImageIO.read(new File(fromQuery.getImagePath()));
                BufferedImage fromQueryImg = ImageIO.read(new File(fromBest.getImagePath()));
                g2d.draw(new Line2D.Double((fromQuery.getLocation().getFirst() + fromQueryImg.getWidth() / 2) / this.resizeFactor,
                        (fromQuery.getLocation().getSecond() + fromQueryImg.getHeight() / 2) / this.resizeFactor,
                        (fromBest.getLocation().getFirst() + fromBestImg.getWidth() / 2) / this.resizeFactor + (w / 2),
                        (fromBest.getLocation().getSecond() + fromBestImg.getHeight() / 2) / this.resizeFactor));
            }
        } catch (IOException ignored) {
        }

        g2d.dispose();
    }

    /**
     * Add a graph to a Graphics2D.
     *
     * @param g2d    Graphics2D where to add the graph.
     * @param graph  graph to be added.
     * @param offset offset to move the graph to the right if wanted.
     */
    private void addGraphToImage(Graphics2D g2d, Graph graph, int offset) {
        try {
            for (Node node : graph.getNodes()) {
                Tuple<Double, Double> location = node.getLocation();
                BufferedImage img = ImageIO.read(new File(node.getImagePath()));
                int x = location.getFirst().intValue() / this.resizeFactor + offset;
                int y = location.getSecond().intValue() / this.resizeFactor;
                g2d.drawImage(img, x, y, img.getWidth() / this.resizeFactor, img.getHeight() / this.resizeFactor, null);
            }

            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(12f));
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            for (Node node : graph.getNodes()) {
                Tuple<Double, Double> location = node.getLocation();
                BufferedImage img = ImageIO.read(new File(node.getImagePath()));
                int x = location.getFirst().intValue() / this.resizeFactor + offset;
                int y = location.getSecond().intValue() / this.resizeFactor;
                g2d.drawString(node.getId(), x, y);
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

    /**
     * Return the image representation of the graph contained in the GraphPlotter object.
     *
     * @return the image representation of the graph contained in the GraphPlotter object.
     */
    public BufferedImage getImageGraph() {
        return imageGraph;
    }
}
