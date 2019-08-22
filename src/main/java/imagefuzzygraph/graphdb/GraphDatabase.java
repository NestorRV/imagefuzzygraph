package imagefuzzygraph.graphdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import imagefuzzygraph.graph.Graph;
import imagefuzzygraph.visualization.GraphPlotter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class representing a database of graphs.
 *
 * @author @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class GraphDatabase extends ArrayList<Graph> {

    /**
     * Build a DataBase querying all the methods in {@link GraphExamples}.
     *
     * @throws NoSuchMethodException     exception thrown if a method is not found.
     * @throws InvocationTargetException exception thrown if a method throws an exception.
     * @throws IllegalAccessException    exception thrown if a method is not accessible.
     */
    public void buildDatabase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GraphExamples graphExamples = new GraphExamples();
        List<Method> methods = Arrays.asList(graphExamples.getClass().getDeclaredMethods());
        long nGraphsExamples = methods.stream().filter(m -> m.getName().startsWith("example")).count();
        for (int i = 1; i <= nGraphsExamples; i++) {
            Method method = graphExamples.getClass().getMethod("example" + i);
            this.add((Graph) method.invoke(graphExamples));
        }
    }

    /**
     * Read database from a file.
     *
     * @param fileName file to read from.
     * @throws IOException exception thrown if file can not be opened.
     */
    public void readDatabase(String fileName) throws IOException {
        try (Reader reader = new FileReader(fileName)) {
            this.clear();
            Type type = new TypeToken<ArrayList<Graph>>() {
            }.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            this.addAll(gson.fromJson(new JsonReader(reader), type));
        }
    }

    /**
     * Save the database to a file.
     *
     * @param fileName file to write to.
     * @throws IOException exception thrown if file can not be opened.
     */
    public void saveDatabase(String fileName) throws IOException {
        try (Writer writer = new FileWriter(fileName)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(this, writer);
        }
    }

    /**
     * Plot all the graphs in the database.
     */
    public void plotDatabase() {
        for (Graph graph : this) {
            new GraphPlotter(graph).plot();
        }
    }

    /**
     * Plot random graphs of the database.
     *
     * @param nGraphs number of graphs to plot.
     */
    public void plotRandomGraphs(int nGraphs) {
        List<Integer> integers = IntStream.range(0, this.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(integers);
        for (int i = 0; i < nGraphs && i < this.size(); i++) {
            new GraphPlotter(this.get(i)).plot();
        }
    }

    /**
     * Return a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
