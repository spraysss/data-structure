package com.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Graph {
    private ArrayList<String> vertices;// 顶点集
    private int[][] edges;// 边的邻接矩阵表示
    private int numOfEdges;// 多少条边
    private boolean[] isVisited;

    public void addVertex(String v) {
        vertices.add(v);
    }

    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    /**
     * @param N 顶点数
     */
    public Graph(int N) {
        vertices = new ArrayList<>(N);
        edges = new int[N][N];
        isVisited = new boolean[N];
    }

    /**
     * @param i
     * @return 得到节点i的第一个邻接节点
     */
    private int getFirstNeighbor(int i) {
        for (int j = 0; j < vertices.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;

    }

    /**
     * @param i
     * @param k
     * @return 下一个邻接节点
     */
    private int getNextNeighbor(int i, int k) {
        for (int j = k + 1; j < vertices.size(); j++) {
            if (edges[i][j] > 0) {
                return j;
            }
        }
        return -1;

    }

    private void visit(int i) {
        System.out.print(vertices.get(i));
    }

    private void dfs(int i) {
        if (!isVisited[i]) {
            visit(i);
            isVisited[i] = true;
            int w = getFirstNeighbor(i);
            //访问节点i的每个邻接节点w
            while (w != -1) {
                if (!isVisited[w]) {
                    dfs(w);
                } else {
                    w = getNextNeighbor(i, w);
                }
            }

        }
    }

    private void bfs(int i) {
        if (!isVisited[i]) {
            int u;
            int w;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addLast(i);
            while (!queue.isEmpty()) {
                u = queue.removeFirst();
                w = getFirstNeighbor(u);
                if (!isVisited[u]) {
                    visit(u);
                    isVisited[u] = true;
                }
                while (w != -1) {
                    if (!isVisited[w]) {
                        queue.addLast(w);
                    }
                    w = getNextNeighbor(u, w);
                }
            }
        }

    }


    public void bfs() {
        System.out.print("bfs:");
        //重置isVisited数组
        IntStream.range(0, isVisited.length).forEach(i -> isVisited[i] = false);
        for (int i = 0; i < vertices.size(); i++) {
            bfs(i);

        }
        System.out.println();
    }

    public void dfs() {
        System.out.print("dfs:");
        //重置isVisited数组
        IntStream.range(0, isVisited.length).forEach(i -> isVisited[i] = false);
        for (int i = 0; i < vertices.size(); i++) {
            dfs(i);

        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertices = {"1", "2", "3", "4", "5"};
        Arrays.stream(vertices).forEach(graph::addVertex);
        graph.addEdge(0, 1, 1); //A->B
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 1);

        Arrays.stream(graph.edges).forEach(e -> System.out.println(Arrays.toString(e)));
        //  graph.dfs();

        graph.bfs();
        graph.dfs();
    }

}
