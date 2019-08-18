package com.imagefuzzy.data;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Interface representing an aggregation operator. An aggregation operator is a function that takes a list of values and
 * return an aggregated value.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public interface AggregationOperator extends Function<ArrayList<Double>, Double> {
}

