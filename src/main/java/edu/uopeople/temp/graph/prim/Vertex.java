package edu.uopeople.temp.graph.prim;

import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

    private final String label;
    private final Map<Vertex, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Map<Vertex, Edge> getEdges() {
        return edges;
    }

    public void addEdge(Vertex vertex, Edge edge) {
        if (this.edges.containsKey(vertex)) {
            if (edge.getWeight() < this.edges.get(vertex).getWeight()) {
                this.edges.replace(vertex, edge);
            }
        } else {
            this.edges.put(vertex, edge);
        }
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Pair<Vertex, Edge> nextMinimum() {
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Vertex nextVertex = this;
        for (Map.Entry<Vertex, Edge> pair : edges.entrySet()) {
            if (!pair.getKey().isVisited()) {
                if (!pair.getValue().isIncluded()) {
                    if (pair.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = pair.getValue();
                        nextVertex = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextVertex, nextMinimum);
    }

    public String includedToString(int totalCost) {
        StringBuilder sb = new StringBuilder();
        if (isVisited()) {
            for (Map.Entry<Vertex, Edge> pair : edges.entrySet()) {
                if (pair.getValue().isIncluded()) {
                    extracted(sb, pair, totalCost);
                }
            }
        }
        return sb.toString();
    }

    private void extracted(StringBuilder sb, Map.Entry<Vertex, Edge> pair, int totalCost) {
        if (!pair.getValue().isPrinted()) {
            totalCost += pair.getValue().getWeight();
            sb.append(getLabel());
            sb.append(" --- ");
            sb.append(pair.getValue().getWeight());
            sb.append(" --- ");
            sb.append(pair.getKey().getLabel());
            sb.append(" Total cost = ").append(totalCost);
            sb.append("\n");
            pair.getValue().setPrinted(true);
        }
    }
}
