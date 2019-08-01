package com.imagefuzzygraph.data;

/**
 * Class representing an edge.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Edge {

    /**
     * Id of the edge.
     */
    private final String id;

    /**
     * Id of the start node.
     */
    private final String startNodeId;

    /**
     * Id of the start node.
     */
    private final String endNodeId;

    /**
     * Spatial relationship fuzzy descriptor of the edge.
     */
    private final FuzzyDescriptor spatialRelationshipFuzzyDescriptor;

    /**
     * Constructs an edge
     *
     * @param id                                 id of the edge.
     * @param startNodeId                        startNodeId node of the edge.
     * @param endNodeId                          endNodeId node of the edge.
     * @param spatialRelationshipFuzzyDescriptor spatial relationship fuzzy descriptor between the nodes of the edge.
     */
    public Edge(String id, String startNodeId, String endNodeId, FuzzyDescriptor spatialRelationshipFuzzyDescriptor) {
        this.id = id;
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.spatialRelationshipFuzzyDescriptor = spatialRelationshipFuzzyDescriptor;
    }

    /**
     * Returns the id of the edge.
     *
     * @return the id of the edge.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the start node.
     *
     * @return the id of the start node.
     */
    public String getStartNodeId() {
        return startNodeId;
    }

    /**
     * Returns the id of the end node.
     *
     * @return the id of the end node.
     */
    public String getEndNodeId() {
        return endNodeId;
    }

    /**
     * Returns the spatial relationship fuzzy descriptor of the edge.
     *
     * @return the spatial relationship fuzzy descriptor of the edge.
     */
    public FuzzyDescriptor getSpatialRelationshipFuzzyDescriptor() {
        return spatialRelationshipFuzzyDescriptor;
    }
}
