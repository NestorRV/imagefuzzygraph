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
     * Constructs a node.
     *
     * @param id                   id of the node.
     * @param fuzzyColorDescriptor fuzzy color descriptor of the node.
     */
    public Node(String id, FuzzyDescriptor fuzzyColorDescriptor) {
        this.id = id;
        this.fuzzyColorDescriptor = fuzzyColorDescriptor;
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
     * Returns the fuzzy color descriptor of the node.
     *
     * @return the fuzzy color descriptor of the node.
     */
    public FuzzyDescriptor getFuzzyColorDescriptor() {
        return fuzzyColorDescriptor;
    }
}
