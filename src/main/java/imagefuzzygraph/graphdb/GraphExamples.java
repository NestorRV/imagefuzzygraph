package imagefuzzygraph.graphdb;

import imagefuzzygraph.algorithm.BuildGraph;
import imagefuzzygraph.data.Region;
import imagefuzzygraph.data.Tuple;
import imagefuzzygraph.graph.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Class to build graph examples.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphExamples {
    private final BuildGraph bg = new BuildGraph();
    private final Random random = new Random(1);
    private final ArrayList<Tuple<Double, Double>> locations = new ArrayList<>(Arrays.asList(
            new Tuple<>(0.0, 50.0), new Tuple<>(0.0, 250.0), new Tuple<>(0.0, 450.0), new Tuple<>(0.0, 650.0), new Tuple<>(0.0, 850.0),
            new Tuple<>(200.0, 50.0), new Tuple<>(200.0, 250.0), new Tuple<>(200.0, 450.0), new Tuple<>(200.0, 650.0), new Tuple<>(200.0, 850.0),
            new Tuple<>(400.0, 50.0), new Tuple<>(400.0, 250.0), new Tuple<>(400.0, 450.0), new Tuple<>(400.0, 650.0), new Tuple<>(400.0, 850.0),
            new Tuple<>(600.0, 50.0), new Tuple<>(600.0, 250.0), new Tuple<>(600.0, 450.0), new Tuple<>(600.0, 650.0), new Tuple<>(600.0, 850.0),
            new Tuple<>(800.0, 50.0), new Tuple<>(800.0, 250.0), new Tuple<>(800.0, 450.0), new Tuple<>(800.0, 650.0), new Tuple<>(800.0, 850.0)));

    public Region buildRegion(String folder, String file, double x, double y) {
        String filename = String.format("db/%s/%s.png", folder, file);
        return new Region(filename, new Tuple<>(x, y));
    }

    public Graph randomGraph(int graphId) throws IOException {
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Region> regions = new ArrayList<>();

        Collections.shuffle(this.locations, this.random);
        ArrayList<String> figures = new ArrayList<>(Arrays.asList("circle_1.0", "cross_1.0", "heart_1.0",
                "square_0.8", "square_1.0", "star_1.0", "triangle_1.0"));
        ArrayList<String> colors = new ArrayList<>(Arrays.asList("blue1", "blue2", "blue3", "brown1", "brown2",
                "brown3", "green1", "green2", "green3", "orange1", "orange2", "orange3", "pink1", "pink2",
                "pink3", "purple1", "purple2", "purple3", "red1", "red2", "red3", "yellow1", "yellow2", "yellow3"));

        // Graphs with 2-10 elements.
        int numberOfFigures = 2 + this.random.nextInt(9);
        for (int i = 0; i <= numberOfFigures; i++) {
            String randomFigure = figures.get(this.random.nextInt(figures.size()));
            String randomColor = colors.get(this.random.nextInt(colors.size()));
            Tuple<Double, Double> l = this.locations.get(i);
            labels.add(randomFigure);
            regions.add(this.buildRegion(randomFigure, randomColor, l.getFirst(), l.getSecond()));
        }

        return this.bg.buildGraph("Graph_" + graphId, regions, labels);
    }

    public Graph source_example1() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red1", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 0.0, 50.0));
        regions.add(this.buildRegion(labels.get(2), "blue3", 800.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red3", 500.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "blue1", 0.0, 400.0));
        regions.add(this.buildRegion(labels.get(5), "blue3", 0.0, 700.0));
        return this.bg.buildGraph("Graph_1", regions, labels);
    }

    public Graph source_example2() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red2", 300.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple3", 0.0, 50.0));
        regions.add(this.buildRegion(labels.get(2), "blue1", 800.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "orange3", 500.0, 500.0));
        regions.add(this.buildRegion(labels.get(4), "green2", 0.0, 400.0));
        regions.add(this.buildRegion(labels.get(5), "pink3", 0.0, 700.0));
        return this.bg.buildGraph("Graph_2", regions, labels);
    }

    public Graph source_example3() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("star_1.0", "heart_1.0", "circle_1.0", "cross_1.0", "square_1.0", "square_0.8"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "brown1", 380.0, 380.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 250.0, 250.0));
        regions.add(this.buildRegion(labels.get(2), "pink1", 600.0, 270.0));
        regions.add(this.buildRegion(labels.get(3), "yellow2", 200.0, 550.0));
        regions.add(this.buildRegion(labels.get(4), "red3", 800.0, 370.0));
        regions.add(this.buildRegion(labels.get(5), "green2", 0.0, 50.0));
        return this.bg.buildGraph("Graph_3", regions, labels);
    }

    public Graph source_example4() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("star_1.0", "heart_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "brown1", 180.0, 180.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 50.0, 50.0));
        regions.add(this.buildRegion(labels.get(2), "pink1", 400.0, 100.0));
        return this.bg.buildGraph("Graph_4", regions, labels);
    }

    public Graph source_example5() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("square_1.0", "square_0.8", "square_1.0", "square_0.8"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "orange3", 200.0, 50.0));
        regions.add(this.buildRegion(labels.get(1), "green2", 200.0, 250.0));
        regions.add(this.buildRegion(labels.get(2), "red3", 0.0, 50.0));
        regions.add(this.buildRegion(labels.get(3), "yellow2", 0.0, 250.0));
        return this.bg.buildGraph("Graph_5", regions, labels);
    }

    public Graph query_example1() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("triangle_1.0", "square_0.8", "cross_1.0", "heart_1.0", "star_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red2", 350.0, 120.0));
        regions.add(this.buildRegion(labels.get(1), "purple3", 0.0, 50.0));
        regions.add(this.buildRegion(labels.get(2), "blue1", 750.0, 760.0));
        regions.add(this.buildRegion(labels.get(3), "red2", 500.0, 450.0));
        regions.add(this.buildRegion(labels.get(4), "green2", 0.0, 300.0));
        regions.add(this.buildRegion(labels.get(5), "blue1", 0.0, 700.0));
        return this.bg.buildGraph("Graph_1", regions, labels);
    }

    public Graph query_example2() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("star_1.0", "heart_1.0", "circle_1.0"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "brown1", 180.0, 180.0));
        regions.add(this.buildRegion(labels.get(1), "purple2", 50.0, 50.0));
        regions.add(this.buildRegion(labels.get(2), "pink1", 400.0, 100.0));
        return this.bg.buildGraph("Graph_2", regions, labels);
    }

    public Graph query_example3() throws IOException {
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("square_1.0", "square_0.8", "square_1.0", "square_0.8"));
        ArrayList<Region> regions = new ArrayList<>();
        regions.add(this.buildRegion(labels.get(0), "red1", 150.0, 150.0));
        regions.add(this.buildRegion(labels.get(1), "yellow3", 50.0, 400.0));
        regions.add(this.buildRegion(labels.get(2), "orange2", 450.0, 210.0));
        regions.add(this.buildRegion(labels.get(3), "green1", 470.0, 460.0));
        return this.bg.buildGraph("Graph_3", regions, labels);
    }
}
