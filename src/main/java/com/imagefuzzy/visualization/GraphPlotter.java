package com.imagefuzzy.visualization;

import com.imagefuzzy.data.Tuple;
import com.imagefuzzy.graph.Graph;
import com.imagefuzzy.graph.Node;

import javax.swing.*;
import java.awt.*;

/**
 * Class to plot graphs.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphPlotter extends JComponent {
    private Graph graph;

    public GraphPlotter(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Node node : this.graph.getNodes()) {
            Tuple<Double, Double> location = node.getLocation();
            g2d.drawImage(node.getImage(), location.getFirst().intValue(), location.getSecond().intValue(), null);
        }
    }

    public void plot() {
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }
}