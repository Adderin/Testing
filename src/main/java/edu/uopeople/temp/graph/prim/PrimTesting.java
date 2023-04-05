package edu.uopeople.temp.graph.prim;

import java.util.ArrayList;
import java.util.List;

public class PrimTesting {

    public static void main(String[] args) {
        Prim prim = new Prim(createGraph());

        prim.run();
        prim.resetPrintHistory();
        System.out.println(prim.minimumSpanningTreeToString());
    }

    public static List<Vertex> createGraph() {
        List<Vertex> graph = new ArrayList<>();
        Vertex vertex1 = new Vertex("1");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");

        Edge edge12 = new Edge(9);
        vertex1.addEdge(vertex2, edge12);
        vertex2.addEdge(vertex1, edge12);

        Edge edge13 = new Edge(2);
        vertex1.addEdge(vertex3, edge13);
        vertex3.addEdge(vertex1, edge13);

        Edge edge14 = new Edge(5);
        vertex1.addEdge(vertex4, edge14);
        vertex4.addEdge(vertex1, edge14);

        Edge edge23 = new Edge(6);
        vertex2.addEdge(vertex3, edge23);
        vertex3.addEdge(vertex2, edge23);

        Edge edge25 = new Edge(4);
        vertex2.addEdge(vertex5, edge25);
        vertex5.addEdge(vertex2, edge25);

        Edge edge34 = new Edge(4);
        vertex3.addEdge(vertex4, edge34);
        vertex4.addEdge(vertex3, edge34);

        Edge edge35 = new Edge(4);
        vertex3.addEdge(vertex5, edge35);
        vertex5.addEdge(vertex3, edge35);

        Edge edge45 = new Edge(5);
        vertex4.addEdge(vertex5, edge45);
        vertex5.addEdge(vertex4, edge45);

        graph.add(vertex1);
        graph.add(vertex2);
        graph.add(vertex3);
        graph.add(vertex4);
        graph.add(vertex5);

        return graph;
    }
}
