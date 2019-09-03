package imagefuzzygraph.ui.application;

/**
 * Class to represent the preferences of the matching algorithm.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class MatchingAlgorithmPreferences {

    static private String aggregationOperator = "all";
    static private double alpha = 0.0;
    static private double beta = 1.0;

    /**
     * Return the aggregation operator of the algorithm.
     *
     * @return the aggregation operator of the algorithm.
     */
    public static String getAggregationOperator() {
        return aggregationOperator;
    }

    /**
     * Set the aggregation operator of the algorithm.
     *
     * @param aggregationOperator aggregation operator of the algorithm.
     */
    public static void setAggregationOperator(String aggregationOperator) {
        MatchingAlgorithmPreferences.aggregationOperator = aggregationOperator;
    }

    /**
     * Return the alpha for the atLeast quantifier.
     *
     * @return the alpha for the atLeast quantifier.
     */
    public static double getAlpha() {
        return alpha;
    }

    /**
     * Set the alpha for the atLeast quantifier.
     *
     * @param alpha alpha for the atLeast quantifier.
     */
    public static void setAlpha(double alpha) {
        MatchingAlgorithmPreferences.alpha = alpha;
    }
    
    /**
     * Return the beta for the atLeast quantifier.
     *
     * @return the beta for the atLeast quantifier.
     */
    public static double getBeta() {
        return beta;
    }

    /**
     * Set the beta for the atLeast quantifier.
     *
     * @param beta beta for the atLeast quantifier.
     */
    public static void setBeta(double beta) {
        MatchingAlgorithmPreferences.beta = beta;
    }
}
