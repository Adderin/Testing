package edu.uopeople.temp.graph;

import java.util.LinkedList;

public class Graph {
    private int numberOfVertices;   // No. of vertices

    // Array  of lists for Adjacency List Representation
    private final LinkedList<Integer>[] adj;

    // Constructor
    Graph(int v) {
        numberOfVertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);// Add w to v's list.
        adj[w].add(v); //The graph is undirected
    }

    // A function used by DFS
    void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Method to check if all non-zero degree vertices are
    // connected. It mainly does DFS traversal starting from
    boolean isConnected() {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[numberOfVertices];
        int i = 0;
        for (; i < numberOfVertices; i++)
            visited[i] = false;

        // Find a vertex with non-zero degree
        for (i = 0; i < numberOfVertices; i++)
            if (adj[i].size() != 0)
                break;

        // If there are no edges in the graph, return true
        if (i == numberOfVertices)
            return true;

        // Start DFS traversal from a vertex with non-zero degree
        DFSUtil(i, visited);

        // Check if all non-zero degree vertices are visited
        for (i = 0; i < numberOfVertices; i++)
            if (!visited[i] && adj[i].size() > 0)
                return false;

        return true;
    }

    /* The function returns one of the following values
       0 --> If graph is not Eulerian
       1 --> If graph has an Euler path (Semi-Eulerian)
       2 --> If graph has an Euler Circuit (Eulerian)  */
    int isEulerian() {
        // Check if all non-zero degree vertices are connected
        if (!isConnected())
            return 0;

        // Count vertices with odd degree
        int odd = 0;
        for (int i = 0; i < numberOfVertices; i++)
            if (adj[i].size() % 2 != 0)
                odd++;

        // If count is more than 2, then graph is not Eulerian
        if (odd > 2)
            return 0;

        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected graph
        return (odd == 2) ? 1 : 2;
    }

    // Function to run test cases
    void test() {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }

    public static void main(String[] args) {

        Graph graph = new Graph(8);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.test();

    }
}
