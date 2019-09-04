package imagefuzzygraph.ui.application;

/**
 * Class to represent the preferences of the text search.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class TextSearchPreferences {

    static private String firstObject = "circle_1.0";
    static private String firstObjectColor = "blue1";
    static private String secondObject = "circle_1.0";
    static private String secondObjectColor = "blue1";
    static private String relation = "up";

    /**
     * Return the firstObject of the text search.
     *
     * @return the firstObject of the text search.
     */
    public static String getFirstObject() {
        return firstObject;
    }

    /**
     * Set the the firstObject of the text search.
     *
     * @param firstObject the firstObject of the text search.
     */
    public static void setFirstObject(String firstObject) {
        TextSearchPreferences.firstObject = firstObject;
    }
    
    /**
     * Return the firstObjectColor of the text search.
     *
     * @return the firstObjectColor of the text search.
     */
    public static String getFirstObjectColor() {
        return firstObjectColor;
    }

    /**
     * Set the the firstObjectColor of the text search.
     *
     * @param firstObjectColor the firstObjectColor of the text search.
     */
    public static void setFirstObjectColor(String firstObjectColor) {
        TextSearchPreferences.firstObjectColor = firstObjectColor;
    }
    
    /**
     * Return the secondObject of the text search.
     *
     * @return the secondObject of the text search.
     */
    public static String getSecondObject() {
        return secondObject;
    }

    /**
     * Set the the secondObject of the text search.
     *
     * @param secondObject the secondObject of the text search.
     */
    public static void setSecondObject(String secondObject) {
        TextSearchPreferences.secondObject = secondObject;
    }
    
    /**
     * Return the secondObjectColor of the text search.
     *
     * @return the secondObjectColor of the text search.
     */
    public static String getSecondObjectColor() {
        return secondObjectColor;
    }

    /**
     * Set the the secondObjectColor of the text search.
     *
     * @param secondObjectColor the secondObjectColor of the text search.
     */
    public static void setSecondObjectColor(String secondObjectColor) {
        TextSearchPreferences.secondObjectColor = secondObjectColor;
    }
    
        /**
     * Return the relation of the text search.
     *
     * @return the relation of the text search.
     */
    public static String getRelation() {
        return relation;
    }

    /**
     * Set the the relation of the text search.
     *
     * @param relation the relation of the text search.
     */
    public static void setRelation(String relation) {
        TextSearchPreferences.relation = relation;
    }
}
