package com.imagefuzzy.data;

import java.awt.image.BufferedImage;

/**
 * Class representing a region.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Region extends Tuple<BufferedImage, Tuple<Double, Double>> {

    /**
     * Construct a region.
     *
     * @param image    image of the region.
     * @param location location of the region.
     */
    public Region(BufferedImage image, Tuple<Double, Double> location) {
        super(image, location);
    }

    /**
     * Return the image of the region.
     *
     * @return the image of the region.
     */
    public BufferedImage getImage() {
        return this.getFirst();
    }

    /**
     * Return the location of the region.
     *
     * @return the location of the region.
     */
    public Tuple<Double, Double> getLocation() {
        return this.getSecond();
    }
}
