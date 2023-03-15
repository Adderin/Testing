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
        Vertex vertex6 = new Vertex("6");
        Vertex vertex7 = new Vertex("7");
        Vertex vertex8 = new Vertex("8");

        Edge edge12 = new Edge(5);
        vertex1.addEdge(vertex2, edge12);
        vertex2.addEdge(vertex1, edge12);

        Edge edge13 = new Edge(4);
        vertex1.addEdge(vertex3, edge13);
        vertex3.addEdge(vertex1, edge13);

        Edge edge23 = new Edge(2);
        vertex2.addEdge(vertex3, edge23);
        vertex3.addEdge(vertex2, edge23);

        Edge edge24 = new Edge(3);
        vertex2.addEdge(vertex4, edge24);
        vertex4.addEdge(vertex2, edge24);

        Edge edge35 = new Edge(4);
        vertex3.addEdge(vertex5, edge35);
        vertex5.addEdge(vertex3, edge35);

        Edge edge45 = new Edge(2);
        vertex4.addEdge(vertex5, edge45);
        vertex5.addEdge(vertex4, edge45);

        Edge edge47 = new Edge(6);
        vertex4.addEdge(vertex7, edge47);
        vertex7.addEdge(vertex4, edge47);

        Edge edge56 = new Edge(1);
        vertex5.addEdge(vertex6, edge56);
        vertex6.addEdge(vertex5, edge56);

        Edge edge67 = new Edge(8);
        vertex6.addEdge(vertex7, edge67);
        vertex7.addEdge(vertex6, edge67);

        Edge edge78 = new Edge(2);
        vertex7.addEdge(vertex8, edge78);
        vertex8.addEdge(vertex7, edge78);

        graph.add(vertex1);
        graph.add(vertex2);
        graph.add(vertex3);
        graph.add(vertex4);
        graph.add(vertex5);
        graph.add(vertex6);
        graph.add(vertex7);
        graph.add(vertex8);

        return graph;
    }
}
