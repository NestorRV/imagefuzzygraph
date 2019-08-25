package imagefuzzygraph.data;

/**
 * Class representing a region.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class Region extends Tuple<String, Tuple<Double, Double>> {

    /**
     * Construct a region.
     *
     * @param imagePath path to the image of the region.
     * @param location  location of the region.
     */
    public Region(String imagePath, Tuple<Double, Double> location) {
        super(imagePath, location);
    }

    /**
     * Return the image of the region.
     *
     * @return the image of the region.
     */
    public String getImagePath() {
        return this.getFirst();
    }

    /**
     * Return the location of the region.
     *
     * @return the location of the region.
     */
    public Tuple<Double, Double> getLocation() {
        return this.getSecond();
    }
}
