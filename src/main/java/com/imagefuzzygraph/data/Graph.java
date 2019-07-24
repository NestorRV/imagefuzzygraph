package com.imagefuzzygraph.data;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class representing a graph.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Graph implements Iterable<Node> {

    /**
     * Nodes of the graph.
     */
    private final HashMap<String, Node> nodes = new HashMap<>();

    /**
     * Edges of the graph.
     */
    private final ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Adjacency list to represent graph's structure.
     */
    private final HashMap<String, ArrayList<Edge>> adjacencyList = new HashMap<>();

    /**
     * Constructs a graph.
     *
     * @param nodes list of nodes.
     * @param edges list of edges.
     */
    public Graph(Collection<Node> nodes, Collection<Edge> edges) {
        for (Node node : nodes) {
            this.nodes.put(node.getId(), node);
        }

        for (Edge edge : edges) {
            this.addEdge(edge);
        }
    }

    /**
     * Adds an edge to the graph.
     *
     * @param edge new edge to be added.
     */
    private void addEdge(Edge edge) {
        this.adjacencyList.computeIfAbsent(edge.getStartNodeId(), k -> new ArrayList<>()).add(edge);
        this.edges.add(edge);
    }

    /**
     * Returns iterator to iterate through nodes.
     *
     * @return iterator to iterate through nodes.
     */
    @Override
    public Iterator<Node> iterator() {
        return nodes.values().iterator();
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

