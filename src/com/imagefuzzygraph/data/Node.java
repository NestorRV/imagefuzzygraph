package com.imagefuzzygraph.data;

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
    private ArrayList<Property> colorProperties;

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
    public Node(ArrayList<Property> colorProperties, String label) {
        this.colorProperties = colorProperties;
        this.label = label;
    }
}
