package com.imagefuzzy.graph;

import com.imagefuzzy.data.FuzzyDescriptor;

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
    private final FuzzyDescriptor colorFuzzyDescriptor;

    /**
     * Constructs a node.
     *
     * @param id                   id of the node.
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     */
    public Node(String id, FuzzyDescriptor colorFuzzyDescriptor) {
        this.id = id;
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
    }

    /**
     * Returns the id of the node.
     *
     * @return the id of the node.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the color fuzzy descriptor of the node.
     *
     * @return the color fuzzy descriptor of the node.
     */
    public FuzzyDescriptor getColorFuzzyDescriptor() {
        return colorFuzzyDescriptor;
    }
}
