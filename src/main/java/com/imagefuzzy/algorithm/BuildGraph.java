package com.imagefuzzy.algorithm;

import com.imagefuzzy.data.FuzzyDescriptor;
import com.imagefuzzy.data.FuzzyProperty;
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
     * Build a fuzzy color descriptor for a color.
     *
     * @param color color to be processed.
     * @return fuzzy color descriptor of the a color.
     */
    private FuzzyDescriptor buildFuzzyColorDescriptor(Point3D color) {
        FuzzyDescriptor fuzzyColorDescriptor = new FuzzyDescriptor();
        FuzzyColorSpace<Point3D> fcs = FuzzyColorSpace.Factory.createFuzzyCMeansFCS(new ISCCColorMap(ISCCColorMap.TYPE_BASIC));
        ArrayList<FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem> pd = fcs.getPossibilityDistribution(color);
        for (FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem possibilityDistributionItem : pd) {
            fuzzyColorDescriptor.add(new FuzzyProperty(possibilityDistributionItem.fuzzySet.getLabel(), possibilityDistributionItem.degree));
        }
        return fuzzyColorDescriptor;
    }

    /**
     * Generate a fuzzy color descriptor for the given image.
     *
     * @param img image to be processed.
     * @return fuzzy color descriptor of the image.
     */
    public FuzzyDescriptor buildDominantFuzzyColorDescriptor(BufferedImage img) {
        ArrayList<FuzzyDescriptor> fuzzyColorDescriptors = new ArrayList<>();
        MPEG7DominantColors dcd = new MPEG7DominantColors(img);
        ArrayList<MPEG7DominantColors.MPEG7SingleDominatColor> dominantColors = dcd.getDominantColors();
        for (MPEG7DominantColors.MPEG7SingleDominatColor dominantColor : dominantColors) {
            Color colorData = dominantColor.getColorData();
            Point3D color = new Point3D(colorData.getRed(), colorData.getGreen(), colorData.getBlue());
            fuzzyColorDescriptors.add(this.buildFuzzyColorDescriptor(color));
        }
        return this.mergeFuzzyDescriptors(fuzzyColorDescriptors);
    }

    /**
     * Merge multiple fuzzy descriptors into one.
     *
     * @param fuzzyDescriptors fuzzy descriptors to be merged.
     * @return merged fuzzy descriptor.
     */
    private FuzzyDescriptor mergeFuzzyDescriptors(ArrayList<FuzzyDescriptor> fuzzyDescriptors) {
        HashMap<String, Double> fuzzyProperties = new HashMap<>();
        for (FuzzyDescriptor fuzzyDescriptor : fuzzyDescriptors) {
            for (FuzzyProperty fuzzyProperty : fuzzyDescriptor) {
                double degree = fuzzyProperties.getOrDefault(fuzzyProperty.getLabel(), Double.NEGATIVE_INFINITY);
                if (fuzzyProperty.getDegree() > degree) {
                    fuzzyProperties.put(fuzzyProperty.getLabel(), fuzzyProperty.getDegree());
                }
            }
        }

        FuzzyDescriptor finalFuzzyDescriptor = new FuzzyDescriptor();
        fuzzyProperties.forEach((label, degree) -> finalFuzzyDescriptor.add(new FuzzyProperty(label, degree)));
        return finalFuzzyDescriptor;
    }

    /**
     * Build a fuzzy spatial relationship descriptor for two points.
     *
     * @param x1 x coordinate of the first point.
     * @param y1 y coordinate of the first point.
     * @param x2 x coordinate of the second point.
     * @param y2 y coordinate of the second point.
     * @return fuzzy spatial relationship descriptor for two points.
     */
    public FuzzyDescriptor buildFuzzySpatialRelationshipDescriptor(double x1, double y1, double x2, double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        // Keep angle between 0 and 360
        angle = angle + Math.ceil(-angle / 360) * 360;

        FunctionBasedFuzzySet<Double> rightFirstPart = new FunctionBasedFuzzySet<>("right", new TrapezoidalFunction<>(0.0, 0.0, 20.0, 70.0));
        FunctionBasedFuzzySet<Double> up = new FunctionBasedFuzzySet<>("up", new TrapezoidalFunction<>(20.0, 70.0, 110.0, 160.0));
        FunctionBasedFuzzySet<Double> left = new FunctionBasedFuzzySet<>("left", new TrapezoidalFunction<>(110.0, 160.0, 200.0, 250.0));
        FunctionBasedFuzzySet<Double> down = new FunctionBasedFuzzySet<>("down", new TrapezoidalFunction<>(200.0, 250.0, 290.0, 340.0));
        FunctionBasedFuzzySet<Double> rightSecondPart = new FunctionBasedFuzzySet<>("right", new TrapezoidalFunction<>(290.0, 340.0, 360.0, 360.0));

        FuzzyDescriptor fuzzySpatialRelationshipDescriptor = new FuzzyDescriptor();
        FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double> fsc = new FuzzySetCollection<>(Arrays.asList(rightFirstPart, up, left, down, rightSecondPart));
        ArrayList<FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem> pd = fsc.getPossibilityDistribution(angle);
        for (FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem pdItem : pd) {
            fuzzySpatialRelationshipDescriptor.add(new FuzzyProperty(pdItem.fuzzySet.getLabel(), pdItem.degree));
        }
        return fuzzySpatialRelationshipDescriptor;
    }
}
