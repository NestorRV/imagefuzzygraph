package com.imagefuzzygraph.data;

/**
 * Class representing a node.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Node {

    /**
     * Id of the node.
     */
    private String id;

    /**
     * Color fuzzy descriptor of the node.
     */
    private FuzzyDescriptor colorFuzzyDescriptor;

    /**
     * Label of the node.
     */
    private String label;

    /**
     * Constructs a node.
     *
     * @param id                   id of the node.
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     * @param label                label of the node.
     */
    public Node(String id, FuzzyDescriptor colorFuzzyDescriptor, String label) {
        this.id = id;
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
        this.label = label;
    }

    /**
     * Returns the id of the node.
     *
     * @return the id of the node.
     */
    public String getId() {
        return id;
    }
}
