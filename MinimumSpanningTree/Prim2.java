import java.util.*;

public class Prim2 {
    private static final int INF = Integer.MAX_VALUE;

    public static Edge[] prim(List<List<Edge>> graph, int source) {
        int vNum = graph.size();
        Edge[] mst = new Edge[vNum - 1];
        boolean[] visited = new boolean[vNum];

        visited[source] = true;

        // 将 V - 1 条边加入最小生成树
        for (int n = 0; n < vNum - 1; n++) {
            Edge minEdge = new Edge(-1, -1, INF);
            // 遍历 from 在 visited， to 在 unvisited 的边，找权重最小的一条加入最小生成树，即访问 to 对应的顶点
            for (int i = 0; i < vNum; i++) {
                if (!visited[i]) continue;
                List<Edge> edges = graph.get(i);
                for (Edge edge : edges) {
                    if (visited[edge.to]) continue;
                    if (edge.weight < minEdge.weight) minEdge = edge;
                }
            }

            visited[minEdge.to] = true;
            mst[n] = minEdge;
        }
        return mst;
    }

    public static void main(String[] args) {
        int[][] data = {
            {   0,  12, INF, INF, INF,  16,  14 },
            {  12,   0,  10, INF, INF,   7, INF },
            { INF,  10,   0,   3,   5,   6, INF },
            { INF, INF,   3,   0,   4, INF, INF },
            { INF, INF,   5,   4,   0,   2,   8 },
            {  16,   7,   6, INF,   2,   0,   9 },
            {  14, INF, INF, INF,   8,   9,   0 }
        };
        int size = data.length;

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (data[i][j] != INF) edges.add(new Edge(i, j, data[i][j]));
            }
            graph.add(edges);
        }

        long startTime = System.currentTimeMillis();

        Edge[] mst = prim(graph, 0);
        int weight = 0;
        for (Edge edge : mst) {
            weight += edge.weight;
            System.out.println(edge.from + "->" + edge.to + ": " + edge.weight);
        }
        System.out.println("Weight: " + weight);

        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}