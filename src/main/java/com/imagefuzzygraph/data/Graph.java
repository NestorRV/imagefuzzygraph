package com.imagefuzzygraph.data;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class representing a graph
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Graph implements Iterable<Node> {

    /**
     * Nodes of the graph
     */
    private final ArrayList<Node> nodes = new ArrayList<>();

    /**
     * Edges of the graph
     */
    private final ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Constructs a graph
     *
     * @param nodes list of nodes
     * @param edges list of edges
     */
    public Graph(Collection<Node> nodes, Collection<Edge> edges) {
        this.nodes.addAll(nodes);
        this.edges.addAll(edges);
    }

    /**
     * Returns the edges of the graph
     *
     * @return the edges of the graph
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Returns the nodes of the graph
     *
     * @return the nodes of the graph
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns an iterator to iterate through the nodes of the graph
     *
     * @return an iterator to iterate through the nodes of the graph
     */
    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    /**
     * Returns a string representation of the object
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}

