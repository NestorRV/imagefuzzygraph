package com.imagefuzzy.algorithm;

import com.imagefuzzy.data.FuzzyDescriptor;
import com.imagefuzzy.data.FuzzyProperty;
import com.imagefuzzy.data.Tuple;
import com.imagefuzzy.graph.Edge;
import com.imagefuzzy.graph.Graph;
import com.imagefuzzy.graph.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class to perform a fuzzy graph matching.
 *
 * @author Néstor Rodríguez Vico (nrv23@correo.ugr.es).
 */
public class FuzzyGraphMatching {

    /**
     * Compute a matrix representing the similarity between two nodes.
     *
     * @param sourceNodes source nodes.
     * @param queryNodes  query nodes.
     * @return matrix where, for row i and column j we have the similarity between sourceNodes[i] and queryNodes[j]. To
     * handle deletion of nodes, a nested HashMap indexed by node identifiers is used instead of a matrix.
     */
    private Map<String, Map<String, Double>> computeSimilarities(ArrayList<Node> sourceNodes, ArrayList<Node> queryNodes) {
        Map<String, Map<String, Double>> similarities = new HashMap<>();
        for (Node sourceNode : sourceNodes) {
            Map<String, Double> queryNodeSimilarities = new HashMap<>();
            for (Node queryNode : queryNodes) {
                queryNodeSimilarities.put(queryNode.getId(), fuzzyNodeInclusion(sourceNode, queryNode));
            }
            similarities.put(sourceNode.getId(), queryNodeSimilarities);
        }

        return similarities;
    }

    /**
     * Compute the inclusion degree of the fuzzy descriptor FDi in the fuzzy descriptor FDj.
     *
     * @param FDi fuzzy descriptor i.
     * @param FDj fuzzy descriptor j.
     * @return the inclusion degree of the fuzzy descriptor FDi in the fuzzy descriptor FDj.
     */
    private double fuzzyInclusion(FuzzyDescriptor FDi, FuzzyDescriptor FDj) {
        ArrayList<ArrayList<Double>> finalValues = new ArrayList<>();
        for (FuzzyProperty fpi : FDi) {
            ArrayList<Double> values = new ArrayList<>();
            for (FuzzyProperty fpj : FDj) {
                double S = fpi.getLabel().equals(fpj.getLabel()) ? 1.0 : 0.0;
                /*
                TODO how to handle J
                 */
                double J = fpi.getDegree() + fpj.getDegree();
                double theta = this.tNorm(S, J);
                values.add(theta);
            }
            finalValues.add(values);
        }

        return Collections.min(finalValues.stream().map(Collections::max).collect(Collectors.toList()));
    }

    private double fuzzyInclusionRandom(FuzzyDescriptor source, FuzzyDescriptor query) {
        double difference = 0.0;

        for (FuzzyProperty fuzzyProperty : query) {
            difference += Math.abs(fuzzyProperty.getDegree() - source.get(0).getDegree());
        }

        return 1 - difference;
    }

    /**
     * Compute the inclusion degree of the source edge in the query edge.
     *
     * @param source source edge.
     * @param query  query  edge.
     * @return the inclusion degree of the source edge in the query edge.
     */
    private double fuzzyEdgeInclusion(Edge source, Edge query) {
        FuzzyDescriptor sourceFuzzyDescriptor = source.getSpatialRelationshipFuzzyDescriptor();
        FuzzyDescriptor queryFuzzyDescriptor = query.getSpatialRelationshipFuzzyDescriptor();
        return this.fuzzyInclusionRandom(sourceFuzzyDescriptor, queryFuzzyDescriptor);
    }

    /**
     * Compute the inclusion degree of the source node in the query node.
     *
     * @param source source node.
     * @param query  query  node.
     * @return the inclusion degree of the source node in the query node.
     */
    private double fuzzyNodeInclusion(Node source, Node query) {
        FuzzyDescriptor sourceFuzzyColorDescriptor = source.getFuzzyColorDescriptor();
        FuzzyDescriptor queryFuzzyColorDescriptor = query.getFuzzyColorDescriptor();

        FuzzyDescriptor sourceLabelDescriptor = source.getLabelDescriptor();
        FuzzyDescriptor queryLabelDescriptor = query.getLabelDescriptor();

        double colorInclusion = this.fuzzyInclusionRandom(sourceFuzzyColorDescriptor, queryFuzzyColorDescriptor);
        double labelInclusion = this.fuzzyInclusionRandom(sourceLabelDescriptor, queryLabelDescriptor);

        return this.tNorm(colorInclusion, labelInclusion);
    }

    /**
     * Get the best pair of edges based on their similarity, including the nodes similarities.
     *
     * @param sourceEdges  source edges.
     * @param queryEdges   query edges.
     * @param similarities similarities of the nodes.
     * @return tuple with both edges. The first element is the source edge and the second element query edge.
     */
    private Tuple<Edge, Edge> getBestPairOfEdges(Collection<Edge> sourceEdges, Collection<Edge> queryEdges,
                                                 Map<String, Map<String, Double>> similarities) {
        double bestTripletSimilarity = Double.NEGATIVE_INFINITY;
        Edge bestSourceEdge = null;
        Edge bestQueryEdge = null;
        for (Edge sourceEdge : sourceEdges) {
            for (Edge queryEdge : queryEdges) {
                double startNodesSimilarity = similarities.get(sourceEdge.getStartNodeId()).get(queryEdge.getStartNodeId());
                double endNodesSimilarity = similarities.get(sourceEdge.getEndNodeId()).get(queryEdge.getEndNodeId());
                double edgesSimilarity = this.fuzzyEdgeInclusion(sourceEdge, queryEdge);
                /*
                TODO How to combine startNodesSimilarity + endNodesSimilarity + edgesSimilarity
                 */
                double finalSimilarity = startNodesSimilarity + endNodesSimilarity + edgesSimilarity;

                if (finalSimilarity >= bestTripletSimilarity) {
                    bestSourceEdge = sourceEdge;
                    bestQueryEdge = queryEdge;
                    bestTripletSimilarity = finalSimilarity;
                }
            }
        }

        return new Tuple<>(bestSourceEdge, bestQueryEdge);
    }

    /**
     * Get the best pair of nodes based on their similarity
     *
     * @param similarities similarities of the nodes
     * @return tuple with the identifiers of both nodes. The first element is the id of the source node and the second
     * element is the id of the query node
     */
    private Tuple<String, String> getBestPairOfNodes(Map<String, Map<String, Double>> similarities) {
        Tuple<String, String> bestPair = null;
        double bestSimilarity = Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, Map<String, Double>> sourceNode : similarities.entrySet()) {
            Map<String, Double> queryNodes = sourceNode.getValue();
            for (Map.Entry<String, Double> queryNode : queryNodes.entrySet()) {
                Double similarity = queryNode.getValue();
                if (similarity >= bestSimilarity) {
                    bestPair = new Tuple<>(sourceNode.getKey(), queryNode.getKey());
                    bestSimilarity = similarity;
                }
            }
        }

        return bestPair;
    }

    /**
     * Compute the inclusion degree of the graph queryGraph in the graph sourceGraph.
     *
     * @param sourceGraph source graph.
     * @param queryGraph  query graph.
     * @param threshold   stop the algorithm if the similarity degree obtained in an iteration is lower than the threshold.
     * @return the inclusion degree of the graph queryGraph in the graph sourceGraph.
     */
    public double greedyInclusion(Graph sourceGraph, Graph queryGraph, double threshold) {
        /*
        TODO how to compute the final matching
         */

        /*
        Map<String, Node> sourceNodesMap = sourceGraph.getNodes().stream().collect(Collectors.toMap(Node::getId, s -> s));
        Map<String, Node> queryNodesMap = queryGraph.getNodes().stream().collect(Collectors.toMap(Node::getId, s -> s));
        Map<String, Edge> sourceEdgesMap = sourceGraph.getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
        Map<String, Edge> queryEdgesMap = queryGraph.getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
         */

        boolean shouldContinue;
        ArrayList<Tuple<String, String>> nodesMatching = new ArrayList<>();
        ArrayList<Tuple<String, String>> edgesMatching = new ArrayList<>();

        Map<String, Map<String, Double>> similarities = this.computeSimilarities(sourceGraph.getNodes(), queryGraph.getNodes());
        Tuple<String, String> bestPair = this.getBestPairOfNodes(similarities);

        if (bestPair != null) {
            nodesMatching.add(new Tuple<>(bestPair.getFirst(), bestPair.getSecond()));
            Collection<Edge> sourceAdjacentEdges = sourceGraph.getAdjacentEdges(bestPair.getFirst());
            Collection<Edge> queryAdjacentEdges = queryGraph.getAdjacentEdges(bestPair.getSecond());
            double iterationSimilarity = similarities.get(bestPair.getFirst()).get(bestPair.getSecond());
            shouldContinue = iterationSimilarity > threshold;

            while (!queryAdjacentEdges.isEmpty() && shouldContinue) {
                Tuple<Edge, Edge> bestTriplet = getBestPairOfEdges(sourceAdjacentEdges, queryAdjacentEdges, similarities);

                sourceGraph.deleteNode(bestTriplet.getFirst().getStartNodeId());
                queryGraph.deleteNode(bestTriplet.getSecond().getStartNodeId());

                sourceAdjacentEdges = sourceGraph.getAdjacentEdges(bestTriplet.getFirst().getEndNodeId());
                queryAdjacentEdges = queryGraph.getAdjacentEdges(bestTriplet.getSecond().getEndNodeId());

                nodesMatching.add(new Tuple<>(bestTriplet.getFirst().getEndNodeId(), bestTriplet.getFirst().getEndNodeId()));
                edgesMatching.add(new Tuple<>(bestTriplet.getFirst().getId(), bestTriplet.getFirst().getId()));

                 /*
                TODO Compute new iterationSimilarity
                 */

                shouldContinue = iterationSimilarity > threshold;
            }
        }

        return 0.0;
    }

    /**
     * Perform a t-norm between two values.
     *
     * @param a first value.
     * @param b second value.
     * @return t-norm of those values. Min is used as the t-norm.
     */
    private double tNorm(double a, double b) {
        return Math.min(a, b);
    }
}
