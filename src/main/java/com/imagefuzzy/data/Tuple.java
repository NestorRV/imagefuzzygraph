package com.imagefuzzy.data;

/**
 * Class representing a tuple.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Tuple<T, S> {
    /**
     * First element of the tuple.
     */
    private final T first;

    /**
     * Second element of the tuple.
     */
    private final S second;

    /**
     * Construct an empty tuple.
     */
    public Tuple() {
        this(null, null);
    }

    /**
     * Construct a tuple.
     *
     * @param first  first element of the tuple.
     * @param second second element of the tuple.
     */
    public Tuple(T first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element of the tuple.
     *
     * @return the first element of the tuple.
     */
    public T getFirst() {
        return first;
    }

    /**
     * Returns the second element of the tuple.
     *
     * @return the second element of the tuple.
     */
    public S getSecond() {
        return second;
    }
}
