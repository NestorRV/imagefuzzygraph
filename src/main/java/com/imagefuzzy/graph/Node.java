package com.imagefuzzy.graph;

import com.imagefuzzy.data.Descriptor;
import com.imagefuzzy.data.Tuple;

import java.awt.image.BufferedImage;

/**
 * Class representing a node.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Node {

    /**
     * Id of the node.
     */
    private final String id;

    /**
     * Color fuzzy descriptor of the node.
     */
    private final Descriptor colorFuzzyDescriptor;

    /**
     * Label descriptor of the node.
     */
    private final Descriptor labelDescriptor;

    /**
     * Region of the full image represented by the node.
     */
    private transient BufferedImage image;

    /**
     * Location of the region.
     */
    private transient Tuple<Integer, Integer> location;

    /**
     * Construct a node.
     *
     * @param id                   id of the node.
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     * @param labelDescriptor      label descriptor of the node.
     * @param image                region of the full image represented by the node.
     * @param location             location of the region.
     */
    public Node(String id, Descriptor colorFuzzyDescriptor, Descriptor labelDescriptor, BufferedImage image,
                Tuple<Integer, Integer> location) {
        this.id = id;
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
        this.labelDescriptor = labelDescriptor;
        this.image = image;
        this.location = location;
    }

    /**
     * Return the id of the node.
     *
     * @return the id of the node.
     */
    public String getId() {
        return id;
    }

    /**
     * Return the color fuzzy descriptor of the node.
     *
     * @return the color fuzzy descriptor of the node.
     */
    public Descriptor getColorFuzzyDescriptor() {
        return colorFuzzyDescriptor;
    }

    /**
     * Return the label descriptor of the node.
     *
     * @return the label descriptor of the node.
     */
    public Descriptor getLabelDescriptor() {
        return labelDescriptor;
    }

    /**
     * Return the region of the full image represented by the node.
     *
     * @return the region of the full image represented by the node.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Return the location of the region.
     *
     * @return the location of the region.
     */
    public Tuple<Integer, Integer> getLocation() {
        return location;
    }
}
