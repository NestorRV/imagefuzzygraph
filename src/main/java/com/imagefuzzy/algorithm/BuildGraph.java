package com.imagefuzzy.algorithm;

import com.imagefuzzy.data.Descriptor;
import com.imagefuzzy.data.PropertyWithDegree;
import com.imagefuzzy.data.Region;
import com.imagefuzzy.data.Tuple;
import com.imagefuzzy.graph.Edge;
import com.imagefuzzy.graph.Graph;
import com.imagefuzzy.graph.Node;
import jfi.color.ISCCColorMap;
import jfi.color.fuzzy.FuzzyColor;
import jfi.color.fuzzy.FuzzyColorSpace;
import jfi.fuzzy.FunctionBasedFuzzySet;
import jfi.fuzzy.FuzzySetCollection;
import jfi.fuzzy.membershipfunction.TrapezoidalFunction;
import jfi.geometry.Point3D;
import jmr.initial.descriptor.mpeg7.MPEG7DominantColors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class to build a graph automatically.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class BuildGraph {

    /**
     * Build the fuzzy descriptor of the color.
     *
     * @param color color to be processed.
     * @return fuzzy descriptor of the color.
     */
    private Descriptor buildColorFuzzyDescriptor(Point3D color) {
        Descriptor colorDescriptor = new Descriptor();
        FuzzyColorSpace<Point3D> fcs = FuzzyColorSpace.Factory.createFuzzyCMeansFCS(new ISCCColorMap(ISCCColorMap.TYPE_BASIC));
        ArrayList<FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem> pd = fcs.getPossibilityDistribution(color);
        for (FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem possibilityDistributionItem : pd) {
            colorDescriptor.add(new PropertyWithDegree(possibilityDistributionItem.fuzzySet.getLabel(), possibilityDistributionItem.degree));
        }
        return colorDescriptor;
    }

    /**
     * Generate a fuzzy descriptor for the dominant color of the given image.
     *
     * @param img image to be processed.
     * @return fuzzy descriptor for the dominant color of the given image.
     */
    private Descriptor buildDominantColorFuzzyDescriptor(BufferedImage img) {
        ArrayList<Descriptor> colorDescriptors = new ArrayList<>();
        MPEG7DominantColors dcd = new MPEG7DominantColors(img);
        ArrayList<MPEG7DominantColors.MPEG7SingleDominatColor> dominantColors = dcd.getDominantColors();
        for (MPEG7DominantColors.MPEG7SingleDominatColor dominantColor : dominantColors) {
            Color colorData = dominantColor.getColorData();
            Point3D color = new Point3D(colorData.getRed(), colorData.getGreen(), colorData.getBlue());
            colorDescriptors.add(this.buildColorFuzzyDescriptor(color));
        }
        return this.mergeDescriptors(colorDescriptors);
    }

    /**
     * Build a edge identifier.
     *
     * @param startNodeId id of the start node.
     * @param endNodeId   id of the end node.
     * @return edge identifier.
     */
    private String buildEdgeId(String startNodeId, String endNodeId) {
        return "edge_" + startNodeId + "_" + endNodeId;
    }

    /**
     * Build a node identifier.
     *
     * @param i index of the node.
     * @return node identifier.
     */
    private String buildNodeId(int i) {
        return "node_" + i;
    }

    /**
     * Build the fuzzy descriptor of the spatial relationship for two points.
     *
     * @param x1 x coordinate of the first point.
     * @param y1 y coordinate of the first point.
     * @param x2 x coordinate of the second point.
     * @param y2 y coordinate of the second point.
     * @return fuzzy descriptor of the spatial relationship for two points.
     */
    private Descriptor buildSpatialRelationshipFuzzyDescriptor(double x1, double y1, double x2, double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        // Keep angle between 0 and 360
        angle = angle + Math.ceil(-angle / 360) * 360;

        FunctionBasedFuzzySet<Double> rightFirstPart = new FunctionBasedFuzzySet<>("right", new TrapezoidalFunction<>(0.0, 0.0, 20.0, 70.0));
        FunctionBasedFuzzySet<Double> up = new FunctionBasedFuzzySet<>("up", new TrapezoidalFunction<>(20.0, 70.0, 110.0, 160.0));
        FunctionBasedFuzzySet<Double> left = new FunctionBasedFuzzySet<>("left", new TrapezoidalFunction<>(110.0, 160.0, 200.0, 250.0));
        FunctionBasedFuzzySet<Double> down = new FunctionBasedFuzzySet<>("down", new TrapezoidalFunction<>(200.0, 250.0, 290.0, 340.0));
        FunctionBasedFuzzySet<Double> rightSecondPart = new FunctionBasedFuzzySet<>("right", new TrapezoidalFunction<>(290.0, 340.0, 360.0, 360.0));

        Descriptor fuzzySpatialRelationshipDescriptor = new Descriptor();
        FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double> fsc = new FuzzySetCollection<>(Arrays.asList(rightFirstPart, up, left, down, rightSecondPart));
        ArrayList<FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem> pd = fsc.getPossibilityDistribution(angle);
        for (FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem pdItem : pd) {
            fuzzySpatialRelationshipDescriptor.add(new PropertyWithDegree(pdItem.fuzzySet.getLabel(), pdItem.degree));
        }
        return fuzzySpatialRelationshipDescriptor;
    }

    /**
     * Merge multiple fuzzy descriptors into one.
     *
     * @param descriptors fuzzy descriptors to be merged.
     * @return merged fuzzy descriptor.
     */
    private Descriptor mergeDescriptors(ArrayList<Descriptor> descriptors) {
        HashMap<String, Double> fuzzyProperties = new HashMap<>();
        for (Descriptor descriptor : descriptors) {
            for (PropertyWithDegree fuzzyProperty : descriptor) {
                double degree = fuzzyProperties.getOrDefault(fuzzyProperty.getLabel(), Double.NEGATIVE_INFINITY);
                if (fuzzyProperty.getDegree() > degree) {
                    fuzzyProperties.put(fuzzyProperty.getLabel(), fuzzyProperty.getDegree());
                }
            }
        }

        Descriptor finalDescriptor = new Descriptor();
        fuzzyProperties.forEach((label, degree) -> finalDescriptor.add(new PropertyWithDegree(label, degree)));
        return finalDescriptor;
    }

    /**
     * Build a label descriptor using the information of a string.
     *
     * @param info information about the label.
     * @return label descriptor using the information of a string.
     */
    private Descriptor buildLabelDescriptor(String info) {
        Descriptor labelDescriptor = new Descriptor();
        String[] parts = info.split("_");
        for (int i = 0; i < parts.length; i += 2) {
            labelDescriptor.add(new PropertyWithDegree(parts[i], Double.parseDouble((parts[i + 1]))));
        }

        return labelDescriptor;
    }

    /**
     * Build a graph for a list regions and information about labels of those regions.
     *
     * @param regions    regions of an image.
     * @param labelsInfo information about the labels.
     * @return graph for a list regions and information about labels of those regions.
     */
    public Graph buildGraph(ArrayList<Region> regions, ArrayList<String> labelsInfo) {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < regions.size(); i++) {
            BufferedImage image = regions.get(i).getImage();
            Descriptor colorFuzzyDescriptor = this.buildDominantColorFuzzyDescriptor(image);
            String startNodeId = this.buildNodeId(i);
            nodes.add(new Node(this.buildNodeId(i), colorFuzzyDescriptor, this.buildLabelDescriptor(labelsInfo.get(i)),
                    regions.get(i).getImage(), regions.get(i).getLocation()));

            for (int j = 0; j < regions.size(); j++) {
                // Do not create auto-edges.
                if (i != j) {
                    String endNodeId = this.buildNodeId(j);
                    Tuple<Double, Double> firstPoint = regions.get(i).getLocation();
                    Tuple<Double, Double> secondPoint = regions.get(j).getLocation();
                    Descriptor locationFuzzyDescriptor = this.buildSpatialRelationshipFuzzyDescriptor(firstPoint.getFirst(),
                            firstPoint.getSecond(), secondPoint.getFirst(), secondPoint.getSecond());

                    edges.add(new Edge(this.buildEdgeId(startNodeId, endNodeId), startNodeId, endNodeId, locationFuzzyDescriptor));
                }
            }
        }

        return new Graph(nodes, edges);
    }
}