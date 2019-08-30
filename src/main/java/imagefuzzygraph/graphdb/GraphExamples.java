package imagefuzzygraph.graphdb;

import imagefuzzygraph.algorithm.BuildGraph;
import imagefuzzygraph.data.Region;
import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to build graph examples.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphExamples {
    private BuildGraph bg = new BuildGraph();
    
    private Region buildRegion(String folder, String file, double x, double y) {
        String filename = String.format("db/%s/%s.png", folder, file);
        return new Region(filename, new Tuple<>(x, y));
    }

    public Graph source_example1() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red1", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 0.0, 0.0));
        regions.add(this.buildRegion(labels.get(2), "blue3", 800.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red3", 500.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "blue1", 0.0, 400.0));
        regions.add(this.buildRegion(labels.get(5), "blue3", 0.0, 700.0));
        return this.bg.buildGraph("Graph_1", regions, labels);
    }

    public Graph source_example2() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red2", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "yellow3", 0.0, 0.0));
        regions.add(this.buildRegion(labels.get(2), "blue2", 600.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red3", 300.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "blue1", 0.0, 400.0));
        return this.bg.buildGraph("Graph_2", regions, labels);
    }

    public Graph query_example1() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red2", 350.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple3", 0.0, 0.0));
        regions.add(this.buildRegion(labels.get(2), "blue1", 750.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red2", 500.0, 450.0));
        regions.add(this.buildRegion(labels.get(4), "green2", 0.0, 300.0));
        regions.add(this.buildRegion(labels.get(5), "blue1", 0.0, 700.0));
        return this.bg.buildGraph("Graph_3", regions, labels);
    }
}
