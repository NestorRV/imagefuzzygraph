package com.imagefuzzygraph.data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing a data.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Graph implements Iterable<Node> {

    /**
     * Adjacency list to represent data's structure.
     */
    private final LinkedHashMap<Node, LinkedHashSet<Edge>> adjacencyList;

    /**
     * Constructs an empty data.
     */
    public Graph() {
        this(new LinkedHashSet<>(), new LinkedHashSet<>());
    }

    /**
     * Constructs a data.
     *
     * @param nodes list of nodes.
     * @param edges list of edges.
     */
    public Graph(Collection<Node> nodes, Collection<Edge> edges) {
        this.adjacencyList = new LinkedHashMap<>();

        for (Node node : nodes) {
            this.addNode(node);
        }

        for (Edge edge : edges) {
            this.addEdge(edge);
        }
    }

    /**
     * Returns the number of nodes.
     *
     * @return the number of nodes.
     */
    public int getNumberOfNodes() {
        return this.adjacencyList.keySet().size();
    }

    /**
     * Returns the number of edges.
     *
     * @return the number of edges.
     */
    public int getNumberOfEdges() {
        return this.getEdges().size();
    }

    /**
     * Adds a node to the data. If the node is already in the data, it will not be added.
     *
     * @param node new node to be added.
     */
    public void addNode(Node node) {
        this.adjacencyList.computeIfAbsent(node, k -> new LinkedHashSet<>());
    }

    /**
     * Deletes a node from the data and all the edges with the origin or destination node equals to the node to be
     * deleted.
     *
     * @param node node to be deleted.
     */
    public void deleteNode(Node node) {
        this.adjacencyList.remove(node);
        this.adjacencyList.forEach((k, v) -> v.removeIf(edge -> edge.getEnd().equals(node) || edge.getStart().equals(node)));
    }

    /**
     * Adds an edge to the data.
     *
     * @param edge new edge to be added.
     */
    public void addEdge(Edge edge) {
        this.adjacencyList.computeIfAbsent(edge.getStart(), k -> new LinkedHashSet<>()).add(edge);
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
     * @param node node to get the adjacent from.
     * @return the adjacent of a node.
     */
    public Collection<Edge> getAdjacent(Node node) {
        return this.adjacencyList.get(node);
    }

    /**
     * Returns the adjacent nodes of a node.
     *
     * @param node node to get the adjacent from.
     * @return the adjacent nodes of a node.
     */
    public Collection<Node> getAdjacentNodes(Node node) {
        Collection<Node> nodes = null;
        Collection<Edge> edges = this.getAdjacent(node);
        if (edges != null) {
            nodes = edges.stream().map(Edge::getStart).collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return nodes;
    }

    /**
     * Return nodes of the data.
     *
     * @return nodes of the data.
     */
    public Collection<Node> getNodes() {
        return this.adjacencyList.keySet();
    }

    /**
     * Return edges of the data.
     *
     * @return edges of the data.
     */
    public Collection<Edge> getEdges() {
        Collection<Edge> edges = new ArrayList<>();
        this.adjacencyList.values().forEach(edges::addAll);
        return edges;
    }

    /**
     * Starting at root node, explores vertices as deeper as possible before exploring nodes at the same level.
     *
     * @param root root of the data.
     * @return nodes in depth-first order.
     * @throws NoSuchElementException Exception thrown when the specified node as root is not present in the data.
     */
    public Collection<Node> depthFirstSearch(Node root) throws NoSuchElementException {
        if (!this.adjacencyList.containsKey(root)) {
            throw new NoSuchElementException("The specified node as root is not present in the data.");
        }

        Collection<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                stack.addAll(this.getAdjacentNodes(node));
            }
        }
        return visited;
    }

    /**
     * Starting at root node, explores all neighboring vertices at the same level before going deeper in the data.
     *
     * @param root root of the data.
     * @return nodes in breadth-first order.
     * @throws NoSuchElementException Exception thrown when the specified node as root is not present in the data.
     */
    public Collection<Node> breadthFirstSearch(Node root) throws NoSuchElementException {
        if (!this.adjacencyList.containsKey(root)) {
            throw new NoSuchElementException("The specified node as root is not present in the data.");
        }

        Collection<Node> visited = new ArrayList<>(Collections.singletonList(root));
        Queue<Node> queue = new LinkedList<>(Collections.singletonList(root));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            this.getAdjacentNodes(node).stream().filter(v -> !visited.contains(v)).forEach(v -> {
                visited.add(v);
                queue.add(v);
            });
        }
        return visited;
    }

    /**
     * Returns iterator to iterate through nodes in depth-first order.
     *
     * @return iterator to iterate through nodes in depth-first order.
     */
    @Override
    public Iterator<Node> iterator() {
        if (!this.adjacencyList.isEmpty()) {
            return this.adjacencyList.keySet().iterator();
        } else {
            return Collections.emptyIterator();
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Graph(");

        this.adjacencyList.forEach((k, v) -> {
            str.append("{ ").append(k.toString()).append(" -> Edges[");
            Iterable<String> edges = v.stream().map(Edge::toString).collect(Collectors.toCollection(LinkedHashSet::new));
            str.append(String.join(", ", edges)).append("]} ");
        });

        str.append(")");

        return str.toString();
    }
}

