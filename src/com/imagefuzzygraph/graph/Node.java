package com.imagefuzzygraph.graph;

import java.util.ArrayList;

/**
 * Class representing a node.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Node {

    /**
     * Color properties of the node.
     */
    private ArrayList<FuzzyValue> colorProperties;

    /**
     * Label of the node.
     */
    private String label;

    /**
     * Constructs a node.
     *
     * @param colorProperties color properties of the node.
     * @param label           label of the node.
     */
    public Node(ArrayList<FuzzyValue> colorProperties, String label) {
        this.colorProperties = colorProperties;
        this.label = label;
    }
}
