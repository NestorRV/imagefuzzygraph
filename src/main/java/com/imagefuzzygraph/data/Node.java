package com.imagefuzzygraph.data;

import java.util.ArrayList;

/**
 * Class representing a node.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Node {

    /**
     * Color fuzzy descriptor of the node.
     */
    private ArrayList<FuzzyProperty> colorFuzzyDescriptor;

    /**
     * Label of the node.
     */
    private String label;

    /**
     * Constructs a node.
     *
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     * @param label                label of the node.
     */
    public Node(ArrayList<FuzzyProperty> colorFuzzyDescriptor, String label) {
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
        this.label = label;
    }
}
