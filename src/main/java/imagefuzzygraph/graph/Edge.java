package imagefuzzygraph.graph;

import imagefuzzygraph.data.Descriptor;

/**
 * Class representing an edge.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
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
    private final Descriptor spatialRelationshipFuzzyDescriptor;

    /**
     * Construct an edge
     *
     * @param id                                 id of the edge.
     * @param startNodeId                        startNodeId node of the edge.
     * @param endNodeId                          endNodeId node of the edge.
     * @param spatialRelationshipFuzzyDescriptor spatial relationship fuzzy descriptor between the nodes of the edge.
     */
    public Edge(String id, String startNodeId, String endNodeId, Descriptor spatialRelationshipFuzzyDescriptor) {
        this.id = id;
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.spatialRelationshipFuzzyDescriptor = spatialRelationshipFuzzyDescriptor;
    }

    /**
     * Return the id of the edge.
     *
     * @return the id of the edge.
     */
    public String getId() {
        return id;
    }

    /**
     * Return the id of the start node.
     *
     * @return the id of the start node.
     */
    public String getStartNodeId() {
        return startNodeId;
    }

    /**
     * Return the id of the end node.
     *
     * @return the id of the end node.
     */
    public String getEndNodeId() {
        return endNodeId;
    }

    /**
     * Return the spatial relationship fuzzy descriptor of the edge.
     *
     * @return the spatial relationship fuzzy descriptor of the edge.
     */
    public Descriptor getSpatialRelationshipFuzzyDescriptor() {
        return spatialRelationshipFuzzyDescriptor;
    }
}
