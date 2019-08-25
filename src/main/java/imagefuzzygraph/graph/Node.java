package imagefuzzygraph.graph;

import imagefuzzygraph.data.Descriptor;
import imagefuzzygraph.data.Tuple;

/**
 * Class representing a node.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Node {

    /**
     * Id of the node.
     */
    private final String id;

    /**
     * Color fuzzy descriptor of the node.
     */
    private final Descriptor colorFuzzyDescriptor;

    /**
     * Label descriptor of the node.
     */
    private final Descriptor labelDescriptor;

    /**
     * Path to the image of the region represented by the node.
     */
    private final String imagePath;

    /**
     * Location of the region.
     */
    private final Tuple<Double, Double> location;

    /**
     * Construct a node.
     *
     * @param id                   id of the node.
     * @param colorFuzzyDescriptor color fuzzy descriptor of the node.
     * @param labelDescriptor      label descriptor of the node.
     * @param imagePath            path to the image of the region represented by the node.
     * @param location             location of the region.
     */
    public Node(String id, Descriptor colorFuzzyDescriptor, Descriptor labelDescriptor, String imagePath,
                Tuple<Double, Double> location) {
        this.id = id;
        this.colorFuzzyDescriptor = colorFuzzyDescriptor;
        this.labelDescriptor = labelDescriptor;
        this.imagePath = imagePath;
        this.location = location;
    }

    /**
     * Return the id of the node.
     *
     * @return the id of the node.
     */
    public String getId() {
        return id;
    }

    /**
     * Return the color fuzzy descriptor of the node.
     *
     * @return the color fuzzy descriptor of the node.
     */
    public Descriptor getColorFuzzyDescriptor() {
        return colorFuzzyDescriptor;
    }

    /**
     * Return the label descriptor of the node.
     *
     * @return the label descriptor of the node.
     */
    public Descriptor getLabelDescriptor() {
        return labelDescriptor;
    }

    /**
     * Return the path to the image of the region represented by the node.
     *
     * @return the path to the image of the region represented by the node.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Return the location of the region.
     *
     * @return the location of the region.
     */
    public Tuple<Double, Double> getLocation() {
        return location;
    }
}
