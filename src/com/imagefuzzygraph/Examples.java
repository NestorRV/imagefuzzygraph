package com.imagefuzzygraph;

import jfi.color.ISCCColorMap;
import jfi.color.fuzzy.FuzzyColor;
import jfi.color.fuzzy.FuzzyColorSpace;
import jfi.fuzzy.FuzzySetCollection;
import jfi.geometry.Point3D;
import jfi.image.fuzzy.PixelFuzzyMappingOp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Examples {

    public static void main(String[] args) {
        ISCCColorMap prototipos = new ISCCColorMap(ISCCColorMap.TYPE_BASIC);
        FuzzyColorSpace<Point3D> fcs = FuzzyColorSpace.Factory.createSphereBasedFCS(prototipos);

        Point3D color = new Point3D(234, 200, 220);
        ArrayList<FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem> pd = fcs.getPossibilityDistribution(color);

        for (FuzzySetCollection<FuzzyColor<Point3D>, Point3D>.PossibilityDistributionItem o : pd) {
            System.out.println(o.degree);
            System.out.println(o.fuzzySet.getLabel());
        }

        for (FuzzyColor fc : fcs) {
            System.out.println(fc.membershipDegree(color));
            System.out.println(fc.membershipDegree(Color.RED));
        }

        PixelFuzzyMappingOp mapColorOp;
        BufferedImage imgin = null, imgout;
        for (FuzzyColor fc : fcs) {
            mapColorOp = new PixelFuzzyMappingOp(fc);
            imgout = mapColorOp.filter(imgin, null, true);
        }
    }
}
