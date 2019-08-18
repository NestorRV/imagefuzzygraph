package com.imagefuzzy.graphdb;

import com.imagefuzzy.algorithm.BuildGraph;
import com.imagefuzzy.data.Region;
import com.imagefuzzy.data.Tuple;
import com.imagefuzzy.graph.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to build graph examples.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphExamples {
    private Region buildRegion(String folder, String file, double x, double y) {
        String filename = String.format("db/%s/%s.png", folder, file);
        return new Region(filename, new Tuple<>(x, y));
    }

    public Graph example1() throws IOException {
        BuildGraph bg = new BuildGraph();
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red1", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 0.0, 0.0));
        regions.add(this.buildRegion(labels.get(2), "blue3", 800.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red3", 500.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "blue1", 0.0, 400.0));
        regions.add(this.buildRegion(labels.get(5), "blue3", 0.0, 700.0));
        return bg.buildGraph(regions, labels);
    }

    public Graph example2() throws IOException {
        BuildGraph bg = new BuildGraph();
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red2", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple3", 0.0, 0.0));
        regions.add(this.buildRegion(labels.get(2), "blue2", 600.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red3", 300.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "blue1", 0.0, 400.0));
        regions.add(this.buildRegion(labels.get(5), "blue3", 100.0, 800.0));
        return bg.buildGraph(regions, labels);
    }
}
