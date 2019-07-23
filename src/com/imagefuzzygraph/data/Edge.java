package com.imagefuzzygraph.data;

import java.util.ArrayList;

/**
 * Class representing an edge.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Edge {
    private Node start;
    private Node end;
    private DIRECTION direction;
    private ArrayList<Property> properties;
    private String label;

    /**
     * Constructs an edge.
     *
     * @param start      start node of the edge.
     * @param end        end node of the edge.
     * @param direction  direction of the edge.
     * @param properties properties of the edge.
     * @param label      label of the edge.
     */
    public Edge(Node start, Node end, DIRECTION direction, ArrayList<Property> properties, String label) {
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.properties = properties;
        this.label = label;
    }

    /**
     * Returns the start node.
     *
     * @return the start node.
     */
    public Node getStart() {
        return start;
    }

    /**
     * Set the start node.
     *
     * @param start new start node.
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * Returns the end node.
     *
     * @return the end node.
     */
    public Node getEnd() {
        return end;
    }

    /**
     * Set the end node.
     *
     * @param end new end node.
     */
    public void setEnd(Node end) {
        this.end = end;
    }

    /**
     * Returns the direction of the edge.
     *
     * @return the direction of the edge.
     */
    public DIRECTION getDirection() {
        return direction;
    }

    /**
     * Set the direction of the edge.
     *
     * @param direction new direction node.
     */
    public void setDirection(DIRECTION direction) {
        this.direction = direction;
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
     * Returns reversed edge.
     *
     * @return reversed edge.
     */
    public Edge reverse() {
        return new Edge(this.end, this.start, DIRECTION.reverseDirection(this.direction), this.properties, this.label);
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
