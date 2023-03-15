package edu.uopeople.temp.graph.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;

    class NodeCost {

        int node; // Adjacent node
        int cost; // cost to adjacent node

        NodeCost(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    class Prims {

        int Find_MST(List<List<NodeCost>> graph) {

            // Comparator lambda function that enables the priority queue to store the nodes
            // based on the cost in the ascending order.
            Comparator<NodeCost> NodeCostComparator = Comparator.comparingInt(obj -> obj.cost);

            // Priority queue stores the object node-cost into the queue with
            // the smallest cost node at the top.
            PriorityQueue<NodeCost> pq = new PriorityQueue<>(NodeCostComparator);

            // The cost of the source node to itself is 0
            pq.add(new NodeCost(0, 0));

            boolean[] added = new boolean[graph.size()];
            Arrays.fill(added, false);

            int mst_cost = 0;

            while (!pq.isEmpty()) {

                // Select the item <node, cost> with minimum cost
                NodeCost item = pq.peek();
                pq.remove();

                int node = item.node;
                int cost = item.cost;

                // If the node is node not yet added to the minimum spanning tree, add it and increment the cost.
                if (!added[node]) {
                    mst_cost += cost;
                    added[node] = true;

                    // Iterate through all the nodes adjacent to the node taken out of priority queue.
                    // Push only those nodes (node, cost) that are not yet present in the minimum spanning tree.
                    for (NodeCost pair_node_cost : graph.get(node)) {
                        int adj_node = pair_node_cost.node;
                        if (!added[adj_node]) {
                            pq.add(pair_node_cost);
                        }
                    }
                }
            }
            return mst_cost;
        }


    public static void main(String[] args) {

        Prims p = new Prims();

        // Outgoing edges from the node:<cost, adjacent_node> in graph.
        int num_nodes = 8; // Nodes (0, 1, 2, 3, 4, 5, 6, 7)

        List<List<NodeCost>> graph = new ArrayList<>(num_nodes);
        for (int i = 0; i < num_nodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Node 0
        Collections.addAll(graph.get(0), new NodeCost(1, 4), new NodeCost(4, 5), new NodeCost(7, 8));
        // Node 1
        Collections.addAll(graph.get(1), new NodeCost(2, 3), new NodeCost(3, 4), new NodeCost(0, 4));
        // Node 2
        Collections.addAll(graph.get(2), new NodeCost(1, 3));
        // Node 3
        Collections.addAll(graph.get(3), new NodeCost(1, 4));
        // Node 4
        Collections.addAll(graph.get(4), new NodeCost(5, 1), new NodeCost(6, 2), new NodeCost(0, 5));
        // Node 5
        Collections.addAll(graph.get(5), new NodeCost(4, 1));
        // Node 6
        Collections.addAll(graph.get(6), new NodeCost(4, 2), new NodeCost(7, 3));
        // Node 7
        Collections.addAll(graph.get(7), new NodeCost(6, 3), new NodeCost(0, 8));

        // Start adding nodes to minimum spanning tree with 0 as the source node
        System.out.println("Cost of the minimum spanning tree in graph: " + p.Find_MST(graph));
    }
}

