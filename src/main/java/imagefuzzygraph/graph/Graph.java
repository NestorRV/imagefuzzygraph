package imagefuzzygraph.graph;

import com.google.gson.GsonBuilder;

import java.util.*;

/**
 * Class representing a graph.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Graph implements Iterable<Node> {

    /**
     * Nodes of the graph.
     */
    private final ArrayList<Node> nodes = new ArrayList<>();

    /**
     * Edges of the graph.
     */
    private final ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Adjacency list to represent graph's structure.
     */
    private final LinkedHashMap<String, LinkedHashSet<Edge>> adjacencyList;

    /**
     * Construct a graph.
     *
     * @param nodes list of nodes.
     * @param edges list of edges.
     */
    public Graph(Collection<Node> nodes, Collection<Edge> edges) {
        this.adjacencyList = new LinkedHashMap<>();
        this.nodes.addAll(nodes);
        edges.forEach(this::addEdge);
    }

    /**
     * Add an edge to the graph.
     *
     * @param edge new edge to be added.
     */
    private void addEdge(Edge edge) {
        this.edges.add(edge);
        this.adjacencyList.computeIfAbsent(edge.getStartNodeId(), k -> new LinkedHashSet<>()).add(edge);
    }

    /**
     * Delete a node from the graph and all the edges with the origin or destination node equals to the node to be
     * deleted.
     *
     * @param nodeId id of the node to be deleted.
     */
    public void deleteNode(String nodeId) {
        this.adjacencyList.remove(nodeId);
        this.adjacencyList.forEach((k, v) -> v.removeIf(edge -> edge.getStartNodeId().equals(nodeId) ||
                edge.getEndNodeId().equals(nodeId)));
    }

    /**
     * Return the adjacent edges of a node.
     *
     * @param nodeId id of node to get the adjacent from.
     * @return the adjacent of a node.
     */
    public Collection<Edge> getAdjacentEdges(String nodeId) {
        return this.adjacencyList.getOrDefault(nodeId, new LinkedHashSet<>());
    }

    /**
     * Return the edges of the graph.
     *
     * @return the edges of the graph.
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Return the nodes of the graph.
     *
     * @return the nodes of the graph.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Return an iterator to iterate through the nodes of the graph.
     *
     * @return an iterator to iterate through the nodes of the graph.
     */
    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    /**
     * Return a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}

