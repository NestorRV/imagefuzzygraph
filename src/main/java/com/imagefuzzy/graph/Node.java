package com.imagefuzzy.graph;

import com.imagefuzzy.data.Descriptor;

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
     * Construct a node.
     *
     * @param id                   id of the node.
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     * @param labelDescriptor      label descriptor of the node.
     */
    public Node(String id, Descriptor colorFuzzyDescriptor, Descriptor labelDescriptor) {
        this.id = id;
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
        this.labelDescriptor = labelDescriptor;
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
}
