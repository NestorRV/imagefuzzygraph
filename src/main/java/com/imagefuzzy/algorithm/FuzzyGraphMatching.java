package com.imagefuzzy.algorithm;

import com.imagefuzzy.data.Descriptor;
import com.imagefuzzy.data.ListOfMatches;
import com.imagefuzzy.data.PropertyWithDegree;
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
     * Compute the inclusion degree of the fuzzy descriptor FDi in the fuzzy descriptor FDj. The implication operator
     * used is the following one:
     *
     * <pre>
     *          | 1, x ≤ y
     * I(x,y) = |
     *          | y/x, otherwise
     * </pre>
     * <br><br>
     * The resemblance relation used is the following one:
     * <br><br>
     * <pre>
     *          | 1, x = y
     * S(x,y) = |
     *          | 0, otherwise
     * </pre>
     *
     * @param FDi fuzzy descriptor i.
     * @param FDj fuzzy descriptor j.
     * @return the inclusion degree of the fuzzy descriptor FDi in the fuzzy descriptor FDj.
     */
    private double fuzzyInclusion(Descriptor FDi, Descriptor FDj) {
        ArrayList<ArrayList<Double>> finalValues = new ArrayList<>();
        for (PropertyWithDegree fpi : FDi) {
            ArrayList<Double> values = new ArrayList<>();
            for (PropertyWithDegree fpj : FDj) {
                double S = fpi.getLabel().equals(fpj.getLabel()) ? 1.0 : 0.0;
                double I = fpi.getDegree() <= fpj.getDegree() ? 1.0 : fpj.getDegree() / fpi.getDegree();
                double theta = this.tNorm(S, I);
                values.add(theta);
            }
            finalValues.add(values);
        }

        return Collections.min(finalValues.stream().map(Collections::max).collect(Collectors.toList()));
    }

    /**
     * Compute the inclusion degree of the source edge in the query edge.
     *
     * @param source source edge.
     * @param query  query  edge.
     * @return the inclusion degree of the source edge in the query edge.
     */
    private double fuzzyEdgeInclusion(Edge source, Edge query) {
        Descriptor sourceDescriptor = source.getSpatialRelationshipFuzzyDescriptor();
        Descriptor queryDescriptor = query.getSpatialRelationshipFuzzyDescriptor();
        return this.fuzzyInclusion(sourceDescriptor, queryDescriptor);
    }

    /**
     * Compute the inclusion degree of the source edge in the query edge considering the nodes similarity.
     *
     * @param source       source edge.
     * @param query        query  edge.
     * @param similarities similarities of the nodes.
     * @return the inclusion degree of the source edge in the query edge.
     */
    private double fuzzyEdgeInclusionConsideringNodes(Edge source, Edge query, Map<String, Map<String, Double>> similarities) {
        double startNodesSimilarity = similarities.get(source.getStartNodeId()).get(query.getStartNodeId());
        double endNodesSimilarity = similarities.get(source.getEndNodeId()).get(query.getEndNodeId());
        double edgesSimilarity = this.fuzzyEdgeInclusion(source, query);
        return this.tNorm(Arrays.asList(startNodesSimilarity, endNodesSimilarity, edgesSimilarity));
    }

    /**
     * Compute the inclusion degree of the source node in the query node.
     *
     * @param source source node.
     * @param query  query  node.
     * @return the inclusion degree of the source node in the query node.
     */
    private double fuzzyNodeInclusion(Node source, Node query) {
        Descriptor sourceColorFuzzyDescriptor = source.getColorFuzzyDescriptor();
        Descriptor queryColorFuzzyDescriptor = query.getColorFuzzyDescriptor();
        Descriptor sourceLabelDescriptor = source.getLabelDescriptor();
        Descriptor queryLabelDescriptor = query.getLabelDescriptor();

        double colorInclusion = this.fuzzyInclusion(sourceColorFuzzyDescriptor, queryColorFuzzyDescriptor);
        double labelInclusion = this.fuzzyInclusion(sourceLabelDescriptor, queryLabelDescriptor);

        return this.tNorm(colorInclusion, labelInclusion);
    }

    /**
     * Get the best pair of edges based on their similarity, including the nodes similarities.
     *
     * @param sourceEdges  source edges.
     * @param queryEdges   query edges.
     * @param similarities similarities of the nodes.
     * @return tuple with both edges and the similarity of the triplet. The first element the pair of edges, where the
     * first element is the source edge and the second element query edge.
     */
    private Tuple<Tuple<Edge, Edge>, Double> getBestPairOfEdges(Collection<Edge> sourceEdges, Collection<Edge> queryEdges,
                                                                Map<String, Map<String, Double>> similarities) {
        double bestTripletSimilarity = Double.NEGATIVE_INFINITY;
        Edge bestSourceEdge = null;
        Edge bestQueryEdge = null;
        for (Edge sourceEdge : sourceEdges) {
            for (Edge queryEdge : queryEdges) {
                double tripletSimilarity = this.fuzzyEdgeInclusionConsideringNodes(sourceEdge, queryEdge, similarities);
                if (tripletSimilarity >= bestTripletSimilarity) {
                    bestSourceEdge = sourceEdge;
                    bestQueryEdge = queryEdge;
                    bestTripletSimilarity = tripletSimilarity;
                }
            }
        }

        return new Tuple<>(new Tuple<>(bestSourceEdge, bestQueryEdge), bestTripletSimilarity);
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
     * Compute the inclusion degree of the graph query in the graph source.
     *
     * @param source source graph.
     * @param query  query graph.
     * @return the inclusion degree of the graph query in the graph source.
     */
    public double greedyInclusion(Graph source, Graph query) {
        return this.greedyInclusion(source, query, 0.0);
    }

    /**
     * Compute the inclusion degree of the graph query in the graph source.
     *
     * @param source    source graph.
     * @param query     query graph.
     * @param threshold stop the algorithm if the similarity degree obtained in an iteration is lower than the threshold.
     * @return the inclusion degree of the graph query in the graph source.
     */
    public double greedyInclusion(Graph source, Graph query, double threshold) {
        Map<String, Map<String, Double>> similarities = this.computeSimilarities(source.getNodes(), query.getNodes());
        Tuple<ListOfMatches, ListOfMatches> matchings = this.greedyMatching(source, query, threshold, similarities);
        ListOfMatches nodesMatching = matchings.getFirst();
        ListOfMatches edgesMatching = matchings.getSecond();

        ArrayList<Double> finalInclusions = new ArrayList<>();
        if (edgesMatching.size() > 0) {
            Map<String, Edge> sourceEdges = source.getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
            Map<String, Edge> queryEdges = query.getEdges().stream().collect(Collectors.toMap(Edge::getId, s -> s));
            for (Tuple<String, String> edgesMatch : edgesMatching) {
                finalInclusions.add(this.fuzzyEdgeInclusionConsideringNodes(sourceEdges.get(edgesMatch.getFirst()), queryEdges.get(edgesMatch.getSecond()), similarities));
            }
        } else {
            for (Tuple<String, String> nodesMatch : nodesMatching) {
                finalInclusions.add(similarities.get(nodesMatch.getFirst()).get(nodesMatch.getSecond()));
            }
        }

        return this.tNorm(finalInclusions);
    }

    /**
     * Find a matching between the source graph and the query graph.
     *
     * @param source    source graph.
     * @param query     query graph.
     * @param threshold stop the algorithm if the similarity degree obtained in an iteration is lower than the threshold.
     * @return tuple containing nodes matches and ege matches.
     */
    public Tuple<ListOfMatches, ListOfMatches> greedyMatching(Graph source, Graph query, double threshold) {
        Map<String, Map<String, Double>> similarities = this.computeSimilarities(source.getNodes(), query.getNodes());
        return this.greedyMatching(source, query, threshold, similarities);
    }

    /**
     * Find a matching between the source graph and the query graph.
     *
     * @param source       source graph.
     * @param query        query graph.
     * @param threshold    stop the algorithm if the similarity degree obtained in an iteration is lower than the threshold.
     * @param similarities similarities of the nodes.
     * @return tuple containing nodes matches and ege matches.
     */
    private Tuple<ListOfMatches, ListOfMatches> greedyMatching(Graph source, Graph query, double threshold,
                                                               Map<String, Map<String, Double>> similarities) {
        ListOfMatches nodesMatching = new ListOfMatches();
        ListOfMatches edgesMatching = new ListOfMatches();
        Tuple<String, String> bestPair = this.getBestPairOfNodes(similarities);

        if (bestPair != null) {
            nodesMatching.add(new Tuple<>(bestPair.getFirst(), bestPair.getSecond()));
            Collection<Edge> sourceAdjacentEdges = source.getAdjacentEdges(bestPair.getFirst());
            Collection<Edge> queryAdjacentEdges = query.getAdjacentEdges(bestPair.getSecond());
            double iterationSimilarity = similarities.get(bestPair.getFirst()).get(bestPair.getSecond());

            while (!queryAdjacentEdges.isEmpty() && !sourceAdjacentEdges.isEmpty() && (iterationSimilarity > threshold)) {
                Tuple<Tuple<Edge, Edge>, Double> bestTriplet = this.getBestPairOfEdges(sourceAdjacentEdges, queryAdjacentEdges, similarities);
                Tuple<Edge, Edge> pairOfEdges = bestTriplet.getFirst();

                source.deleteNode(pairOfEdges.getFirst().getStartNodeId());
                query.deleteNode(pairOfEdges.getSecond().getStartNodeId());

                sourceAdjacentEdges = source.getAdjacentEdges(pairOfEdges.getFirst().getEndNodeId());
                queryAdjacentEdges = query.getAdjacentEdges(pairOfEdges.getSecond().getEndNodeId());

                nodesMatching.add(new Tuple<>(pairOfEdges.getFirst().getEndNodeId(), pairOfEdges.getSecond().getEndNodeId()));
                edgesMatching.add(new Tuple<>(pairOfEdges.getFirst().getId(), pairOfEdges.getSecond().getId()));

                iterationSimilarity = bestTriplet.getSecond();
            }
        }

        return new Tuple<>(nodesMatching, edgesMatching);
    }

    /**
     * Perform a t-norm between two values.
     *
     * @param a first value.
     * @param b second value.
     * @return t-norm of the two values. Min is used as the t-norm.
     */
    private double tNorm(double a, double b) {
        return Math.min(a, b);
    }

    /**
     * Perform a t-norm among N values.
     *
     * @param values values to be processed.
     * @return t-norm of values. Min is used as the t-norm.
     */
    private double tNorm(Collection<Double> values) {
        return Collections.min(values);
    }
}
