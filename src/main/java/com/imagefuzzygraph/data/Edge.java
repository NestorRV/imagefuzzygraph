package com.imagefuzzygraph.data;

/**
 * Class representing an edge.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Edge {
    private String startNodeId;
    private String endNodeId;
    private FuzzyDescriptor spatialRelationshipFuzzyDescriptor;
    private String label;

    /**
     * Constructs an edge.
     *
     * @param startNodeId                        startNodeId node of the edge.
     * @param endNodeId                          endNodeId node of the edge.
     * @param spatialRelationshipFuzzyDescriptor spatial relationship fuzzy descriptor between the nodes of the edge.
     * @param label                              label of the edge.
     */
    public Edge(String startNodeId, String endNodeId, FuzzyDescriptor spatialRelationshipFuzzyDescriptor, String label) {
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.spatialRelationshipFuzzyDescriptor = spatialRelationshipFuzzyDescriptor;
        this.label = label;
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
     * Set the id of the start node.
     *
     * @param startNodeId new id of the start node.
     */
    public void setStartNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
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
     * Set the id of the end node.
     *
     * @param endNodeId new id of the end node.
     */
    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }

    /**
     * Returns the label of the node.
     *
     * @return the label of the node.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of the node.
     *
     * @param label new label of the node.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Direction of the edge.
     */
    public enum DIRECTION {
        START_TO_END, END_TO_START, BIDIRECTIONAL;

        /**
         * Returns reversed direction
         *
         * @param direction original direction
         * @return reversed direction
         */
        public static DIRECTION reverseDirection(DIRECTION direction) {
            if (direction.equals(START_TO_END)) {
                return END_TO_START;
            } else if (direction.equals(END_TO_START)) {
                return START_TO_END;
            } else {
                return BIDIRECTIONAL;
            }
        }
    }
}
