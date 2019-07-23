package com.imagefuzzygraph.data;

/**
 * Class representing a propery.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class Property {
    /**
     * Membership degree of the element to the fuzzy set associated to the Property.
     */
    private double degree;
    /**
     * Label associated to the fuzzy set associated to the Property.
     */
    private String label;

    /**
     * Construct a Property
     *
     * @param degree membership degree of the element to the fuzzy set associated to the Property.
     * @param label  label associated to the fuzzy set associated to the Property.
     */
    public Property(double degree, String label) {
        this.label = label;
        this.degree = degree;
    }

    /**
     * Returns the label of the Property.
     *
     * @return the label of the Property.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of the Property.
     *
     * @param label new label of the Property.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns membership degree of the element to the fuzzy set associated to the Property.
     *
     * @return membership degree of the element to the fuzzy set associated to the Property.
     */
    public double getDegree() {
        return degree;
    }

    /**
     * Set the membership degree of the element to the fuzzy set associated to the Property.
     *
     * @param degree membership degree of the element to the fuzzy set associated to the Property.
     */
    public void setDegree(double degree) {
        this.degree = degree;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return degree + "/" + label;
    }
}
