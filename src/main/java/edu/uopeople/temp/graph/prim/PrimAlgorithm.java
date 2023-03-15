package edu.uopeople.temp.graph.prim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {

    /**
     * Private constructor with an inner class.
     */
    private static class Edge {
        int to;
        int weight;

        private Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Method to create a graph with some hard-coded values.
     * # of vertices = 8
     * # of edges = 10 with weights.
     *
     * @return List of Edges with weights.
     */
    private static List<Edge>[] createGraph(int vertices) {
        List<Edge>[] graph = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }
        addEdge(graph, 0, 1, 4);  // 1-2 -> 4
        addEdge(graph, 0, 5, 5);  // 1-6 -> 5
        addEdge(graph, 0, 3, 3);  // 1-4 -> 3
        addEdge(graph, 1, 2, 3);  // 2-3 -> 3
        addEdge(graph, 1, 4, 4);  // 2-5 -> 4
        addEdge(graph, 2, 3, 2);  // 3-4 -> 2
        addEdge(graph, 3, 5, 3);  // 4-5 -> 3
        addEdge(graph, 4, 5, 1);  // 5-6 -> 1

        return graph;
    }

    /**
     * Method to add the edge to the graph.
     *
     * @param graph  graph to add the edge to
     * @param from   beginning vertices
     * @param to     ending vertices
     * @param weight the weight of an edge
     */
    private static void addEdge(List<Edge>[] graph, int from, int to, int weight) {
        graph[from].add(new Edge(to, weight));
        graph[to].add(new Edge(from, weight));
    }

    /**
     * Method implementing Prim's algorithm.
     *
     * @param graph graph to implement algorithm
     * @return List of Edges
     */
    private static List<Edge> prim(List<Edge>[] graph) {
        // creating a boolean array of graph size to understand if edge was visited
        boolean[] visited = new boolean[graph.length];

        // the List of visited Edges
        List<Edge> edges = new ArrayList<>();

        // the priority queue to sort the weights of Edges
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        visited[0] = true;
        pq.addAll(graph[0]);

        while (!pq.isEmpty()) {
            Edge minEdge = pq.remove();
            if (visited[minEdge.to]) {
                continue;
            }
            visited[minEdge.to] = true;
            edges.add(minEdge);
            for (Edge e : graph[minEdge.to]) {
                if (!visited[e.to]) {
                    pq.add(e);
                }
            }
        }
        return edges;
    }

    /**
     * This is the runner method.
     *
     * @param args default params
     */
    public static void main(String[] args) {
        List<Edge>[] graph = createGraph(6);

        List<Edge> mst = prim(graph);
        int totalCost = 0;
        int from = 1;

        for (Edge e : mst) {
            totalCost += e.weight;
            System.out.printf("(%d -> %d) (cost %d) -> (total cost = %d)\n", from, e.to + 1, e.weight, totalCost);
            from = e.to + 1;
        }
    }
}
