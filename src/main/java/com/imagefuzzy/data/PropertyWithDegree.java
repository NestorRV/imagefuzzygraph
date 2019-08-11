package com.imagefuzzy.data;

/**
 * Class representing a property with a degree.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class PropertyWithDegree {

    /**
     * Label associated to the property.
     */
    private final String label;

    /**
     * Degree associated to the property.
     */
    private final double degree;

    /**
     * Construct a property with a degree.
     *
     * @param label  label associated to the property.
     * @param degree degree associated to the property.
     */
    public PropertyWithDegree(String label, double degree) {
        this.label = label;
        this.degree = degree;
    }

    /**
     * Return the label of the property.
     *
     * @return the label of the property.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Return the degree of the property.
     *
     * @return the degree of the property.
     */
    public double getDegree() {
        return degree;
    }
}
