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
     * Fuzzy color descriptor of the node.
     */
    private final FuzzyDescriptor fuzzyColorDescriptor;

    /**
     * Label descriptor of the node.
     */
    private final FuzzyDescriptor labelDescriptor;

    /**
     * Construct a node.
     *
     * @param id                   id of the node.
     * @param fuzzyColorDescriptor fuzzy color descriptor of the node.
     * @param labelDescriptor      label descriptor of the node.
     */
    public Node(String id, FuzzyDescriptor fuzzyColorDescriptor, FuzzyDescriptor labelDescriptor) {
        this.id = id;
        this.fuzzyColorDescriptor = fuzzyColorDescriptor;
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
     * Return the fuzzy color descriptor of the node.
     *
     * @return the fuzzy color descriptor of the node.
     */
    public FuzzyDescriptor getFuzzyColorDescriptor() {
        return fuzzyColorDescriptor;
    }

    /**
     * Return the label descriptor of the node.
     *
     * @return the label descriptor of the node.
     */
    public FuzzyDescriptor getLabelDescriptor() {
        return labelDescriptor;
    }
}
