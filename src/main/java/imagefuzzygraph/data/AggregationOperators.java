package imagefuzzygraph.data;

import jfi.fuzzy.FunctionBasedFuzzySet;
import jfi.fuzzy.FuzzySetCollection;
import jfi.fuzzy.membershipfunction.TrapezoidalFunction;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to build multiples {@link AggregationOperator} objects.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class AggregationOperators {

    /**
     * <i>At least X</i> of elements should match the condition. This aggregation operator use a trapezoidal
     * function to represent the fuzzy set <i>at least</i>. The trapezoidal function can be defined as:
     *
     * <pre>
     *                | 0, x≤a
     *                | (x−a)/(b−a), a≤x≤b
     * f(x;a,b,c,d) = | 1, b≤x≤c
     *                | (d−x)/(d−c), c≤x≤d
     *                | 0, d≤x
     * </pre>
     * <p>
     * In our situation, <i>a = alpha</i>, <i>b = beta</i>, <i>c = 1</i> and <i>d = 1</i>. So, our trapezoidal
     * function looks like:
     *
     * <pre>
     * |         ________
     * |        /
     * |       /
     * | ------
     * |__________________
     * </pre>
     *
     * <i>alpha</i> represents the first point where the function changes and <i>beta</i> the second one.
     *
     * @param alpha first point where the function changes
     * @param beta  second point where the function changes
     * @return operator representing the <i>At least percentage of elements should match the condition</i> semantic.
     */
    public static AggregationOperator atLeast(double alpha, double beta) {
        return collection -> {
            double sigmaCount = collection.stream().mapToDouble(i -> i).sum() / collection.size();
            FunctionBasedFuzzySet<Double> atLeast = new FunctionBasedFuzzySet<>("atLeast", new TrapezoidalFunction<>(alpha, beta, 1.0, 1.0));
            Descriptor atLeastDescriptor = new Descriptor();
            FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double> fsc = new FuzzySetCollection<>(Collections.singletonList(atLeast));
            ArrayList<FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem> pd = fsc.getPossibilityDistribution(sigmaCount);
            for (FuzzySetCollection<FunctionBasedFuzzySet<Double>, Double>.PossibilityDistributionItem pdItem : pd) {
                atLeastDescriptor.add(new PropertyWithDegree(pdItem.fuzzySet.getLabel(), pdItem.degree));
            }

            if (!atLeastDescriptor.isEmpty()) {
                return atLeastDescriptor.get(0).getDegree();
            } else {
                return 0.0;
            }
        };
    }

    /**
     * <i>All</i> elements should match the condition.
     *
     * @return operator representing the <i>All elements should match the condition</i> semantic.
     */
    public static AggregationOperator all() {
        return Collections::min;
    }
}
