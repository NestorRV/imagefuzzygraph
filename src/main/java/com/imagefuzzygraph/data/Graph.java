package com.imagefuzzygraph.data;

import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.Collectors;

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
     * Constructs a graph.
     *
     * @param nodes list of nodes.
     * @param edges list of edges.
     */
    public Graph(Collection<Node> nodes, Collection<Edge> edges) {
        this.adjacencyList = new LinkedHashMap<>();
        this.nodes.addAll(nodes);
        edges.stream().forEach(this::addEdge);
    }

    /**
     * Adds an edge to the graph.
     *
     * @param edge new edge to be added.
     */
    private void addEdge(Edge edge) {
        this.edges.add(edge);
        this.adjacencyList.computeIfAbsent(edge.getStartNodeId(), k -> new LinkedHashSet<>()).add(edge);
    }

    /**
     * Deletes a node from the graph and all the edges with the origin or destination node equals to the node to be
     * deleted.
     *
     * @param node node to be deleted.
     */
    public void deleteNode(Node node) {
        this.adjacencyList.remove(node);
        this.adjacencyList.forEach((k, v) -> v.removeIf(edge -> edge.getStartNodeId().equals(node) || edge.getEndNodeId().equals(node)));
    }

    /**
     * Deletes an edge.
     *
     * @param edge edge to be deleted.
     */
    public void deleteEdge(Edge edge) {
        this.adjacencyList.forEach((k, v) -> v.removeIf(actualEdge -> actualEdge.equals(edge)));
    }

    /**
     * Returns the adjacent of a node.
     *
     * @param nodeId id of node to get the adjacent from.
     * @return the adjacent of a node.
     */
    public Collection<Edge> getAdjacent(String nodeId) {
        return this.adjacencyList.get(nodeId);
    }

    /**
     * Returns the adjacent nodes of a node.
     *
     * @param nodeId id of the node to get the adjacent from.
     * @return the adjacent nodes of a node.
     */
    public Collection<String> getAdjacentNodes(String nodeId) {
        Collection<String> nodes = new ArrayList<>();
        Collection<Edge> edges = this.getAdjacent(nodeId);
        if (edges != null) {
            nodes = edges.stream().map(Edge::getStartNodeId).collect(Collectors.toList());
        }
        return nodes;
    }

    /**
     * Returns the edges of the graph.
     *
     * @return the edges of the graph.
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Returns the nodes of the graph.
     *
     * @return the nodes of the graph.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns an iterator to iterate through the nodes of the graph.
     *
     * @return an iterator to iterate through the nodes of the graph.
     */
    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}

