package com.imagefuzzygraph.data;

/**
 * Class representing a fuzzy property
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es)
 */
public class FuzzyProperty {

    /**
     * Label associated to the fuzzy set associated to the FuzzyProperty
     */
    private String label;

    /**
     * Membership degree of the element to the fuzzy set associated to the FuzzyProperty
     */
    private double degree;

    /**
     * Construct a FuzzyProperty
     *
     * @param label  label associated to the fuzzy set associated to the FuzzyProperty
     * @param degree membership degree of the element to the fuzzy set associated to the FuzzyProperty
     */
    public FuzzyProperty(String label, double degree) {
        this.label = label;
        this.degree = degree;
    }

    /**
     * Returns the label of the FuzzyProperty
     *
     * @return the label of the FuzzyProperty
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of the FuzzyProperty
     *
     * @param label new label of the FuzzyProperty
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns membership degree of the element to the fuzzy set associated to the FuzzyProperty
     *
     * @return membership degree of the element to the fuzzy set associated to the FuzzyProperty
     */
    public double getDegree() {
        return degree;
    }

    /**
     * Set the membership degree of the element to the fuzzy set associated to the FuzzyProperty
     *
     * @param degree membership degree of the element to the fuzzy set associated to the FuzzyProperty
     */
    public void setDegree(double degree) {
        this.degree = degree;
    }
}
