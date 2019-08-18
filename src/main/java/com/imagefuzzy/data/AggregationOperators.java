package com.imagefuzzy.data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to build multiples {@link com.imagefuzzy.data.AggregationOperator} objects.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class AggregationOperators {

    /**
     * <i>At least percentage</i> of elements should match the condition.
     *
     * @param percentage percentage of elements that match the should match the condition.
     * @return operator representing the <i>At least percentage of elements should match the condition</i> semantic.
     */
    public static AggregationOperator atLeast(double percentage) {
        return collection -> {
            Collections.sort(collection);
            long numberOfDiscardedElements = Math.round(collection.size() - (percentage * collection.size()));
            List<Double> relevant = collection.stream().skip(numberOfDiscardedElements).collect(Collectors.toList());
            return Collections.min(relevant);
        };
    }

    /**
     * <i>All</i> elements should match the condition.
     *
     * @return operator representing the <i>All elements should match the condition</i> semantic.
     */
    public static AggregationOperator all() {
        return atLeast(1.0);
    }
}
