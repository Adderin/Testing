package edu.uopeople.temp.graph;

import java.util.ArrayList;
import java.util.List;

public class HamiltonMatrix {

    public static void main(String[] args) {

    }

    public int uniquePathsIII(int[][] grid) {
        int noOfVertices = 0;

        List<Vertex> vertices = new ArrayList<>();
        Vertex source = null;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] != -1) {
                    Vertex v = new Vertex(i, j, grid[i][j]);

                    if (v.val == 1) {
                        source = v;
                    }

                    vertices.add(v);
                    noOfVertices++;
                }

            }
        }

        Vertex[] path = new Vertex[noOfVertices];
        path[0] = source;
        Result res = new Result();
        hamUtil(path, 1, grid, res, vertices);
        return res.val;
    }

    private void hamUtil(Vertex[] path, int pos, int[][] grid, Result res, List<Vertex> vertices) {
        if (pos == vertices.size() && path[pos-1].val == 2) {
            res.val++;
            return;
        }

        for(Vertex v : vertices) {

            if (isSafe(v, path, grid, pos)) {
                path[pos] = v;
                hamUtil(path, pos+1, grid, res, vertices);
                path[pos] = null;
            }

        }

    }

    private boolean isConnected(Vertex u, Vertex v) {
        return Math.abs(u.i - v.i) + Math.abs(u.j - v.j) == 1;
    }

    private boolean isSafe(Vertex v, Vertex[] path, int[][] grid, int pos) {
        if (!isConnected(v, path[pos-1]))
            return false;

        for (int i = 0; i < pos; i++)
            if (path[i].i == v.i && path[i].j == v.j)
                return false;
        return pos >= path.length - 1 || v.val != 2;
    }


    static class Vertex {
        int i;
        int j;
        int val;
        public Vertex(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    static class Result {
        int val;
    }

}
