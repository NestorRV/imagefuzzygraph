package imagefuzzygraph.ui.application;

/**
 * Class to represent the preferences of the matching algorithm.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class MatchingAlgorithmPreferences {

    static private double threshold = 0.0;
    static private String aggregationOperator = "all";
    static private double aggregationOperatorPercentage = 1.0;

    /**
     * Return the threshold of the algorithm.
     *
     * @return the threshold of the algorithm.
     */
    static public double getThreshold() {
        return threshold;
    }

    /**
     * Set the threshold of the algorithm.
     *
     * @param threshold threshold of the algorithm.
     */
    static public void setThreshold(double threshold) {
        MatchingAlgorithmPreferences.threshold = threshold;
    }

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
     * Return the aggregation operator percentage of the algorithm.
     *
     * @return the aggregation operator percentage of the algorithm.
     */
    public static double getAggregationOperatorPercentage() {
        return aggregationOperatorPercentage;
    }

    /**
     * Set the aggregation operator percentage of the algorithm.
     *
     * @param aggregationOperatorPercentage aggregation operator percentage of
     * the algorithm.
     */
    public static void setAggregationOperatorPercentage(double aggregationOperatorPercentage) {
        MatchingAlgorithmPreferences.aggregationOperatorPercentage = aggregationOperatorPercentage;
    }
}
