package com.imagefuzzygraph.graph;

/**
 * Class representing a fuzzy value.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class FuzzyValue {
    /**
     * Membership degree of the element to the fuzzy set associated to the FuzzyValue.
     */
    private double degree;
    /**
     * Label associated to the fuzzy set associated to the FuzzyValue.
     */
    private String label;

    /**
     * Construct a FuzzyValue
     *
     * @param degree membership degree of the element to the fuzzy set associated to the FuzzyValue.
     * @param label  label associated to the fuzzy set associated to the FuzzyValue.
     */
    public FuzzyValue(double degree, String label) {
        this.label = label;
        this.degree = degree;
    }

    /**
     * Returns the label of the FuzzyValue.
     *
     * @return the label of the FuzzyValue.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of the FuzzyValue.
     *
     * @param label new label of the FuzzyValue.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns membership degree of the element to the fuzzy set associated to the FuzzyValue.
     *
     * @return membership degree of the element to the fuzzy set associated to the FuzzyValue.
     */
    public double getDegree() {
        return degree;
    }

    /**
     * Set the membership degree of the element to the fuzzy set associated to the FuzzyValue.
     *
     * @param degree membership degree of the element to the fuzzy set associated to the FuzzyValue.
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
