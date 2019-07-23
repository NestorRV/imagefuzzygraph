package com.imagefuzzygraph.graph;

import java.util.ArrayList;

/**
 * Class representing a node.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Node {

    /**
     * Properties of the node.
     */
    private ArrayList<FuzzyValue> properties;

    /**
     * Label of the node.
     */
    private String label;

    /**
     * Constructs a node.
     *
     * @param properties properties of the node.
     * @param label      label of the node.
     */
    public Node(ArrayList<FuzzyValue> properties, String label) {
        this.properties = properties;
        this.label = label;
    }
}
