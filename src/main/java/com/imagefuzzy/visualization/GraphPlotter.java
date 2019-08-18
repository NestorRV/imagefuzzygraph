package com.imagefuzzy.visualization;

import com.imagefuzzy.data.Tuple;
import com.imagefuzzy.graph.Graph;
import com.imagefuzzy.graph.Node;

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
    private Graph graph;

    public GraphPlotter(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        try {
            for (Node node : this.graph.getNodes()) {
                Tuple<Double, Double> location = node.getLocation();
                BufferedImage img = ImageIO.read(new File(node.getImagePath()));
                g2d.drawImage(img, location.getFirst().intValue() / 2, location.getSecond().intValue() / 2, img.getWidth() / 2, img.getHeight() / 2, null);
            }
        } catch (IOException ignored) {
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