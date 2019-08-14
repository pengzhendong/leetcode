import java.util.*;

class Edge {
	int to;
	int weight;
	Edge(int to, int weight) {
		this.to  = to;
		this.weight = weight;
	}
}

public class Dijkstra3 {
    private static final int INF = Integer.MAX_VALUE;

    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int size = graph.size();

        // 将顶点分为两个集合 found 和 unfound，前者表示已经找到 source 到其中顶点的最短距离
        boolean[] found = new boolean[size];
        // 维护数组 distances 表示 source 到其他顶点的最短距离
        int[] distances = new int[size];

        // 初始化 found 和 distances 数组
        found[source] = true;
        Arrays.fill(distances, INF);
        for (Edge edge : graph.get(source)) {
            distances[edge.to] = edge.weight;
        }

        // 循环 size - 1 次，每次找出一个顶点到 source 的最短距离
        for (int i = 0; i < size - 1; i++) {
            int index = -1;
            int min_dinstance = INF;
            // 从 unfound 集合中找一个距离 source 最近的点 index，将该点标记为 found
            for (int j = 0; j < size; j++) {
                if (!found[j] && distances[j] < min_dinstance) {
                    index = j;
                    min_dinstance = distances[j];
                }
            }
            found[index] = true;

            // 用 index 点更新该点所有边上的点到 source 的最短距离
            for (Edge edge : graph.get(index)) {
                int distance = min_dinstance + edge.weight;
                // 松弛 (Relaxation) 操作
                if (!found[edge.to] && distance < distances[edge.to]) distances[edge.to] = distance;
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] data = {
            {   0,   1,   5, INF, INF, INF, INF, INF, INF },
            {   1,   0,   3,   7,   5, INF, INF, INF, INF },
            {   5,   3,   0, INF,   1,   7, INF, INF, INF },
            { INF,   7, INF,   0,   2, INF,   3, INF, INF },
            { INF,   5,   1,   2,   0,   3,   6,   9, INF },
            { INF, INF,   7, INF,   3,   0, INF,   5, INF },
            { INF, INF, INF,   3,   6, INF,   0,   2,   7 },
            { INF, INF, INF, INF,   9,   5,   2,   0,   4 },
            { INF, INF, INF, INF, INF, INF,   7,   4,   0 }
        };
        int size = data.length;

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (data[i][j] != INF) edges.add(new Edge(j, data[i][j]));
            }
            graph.add(edges);
        }

        long startTime = System.currentTimeMillis();

        int source = 0;
        int[] distances = dijkstra(graph, source);
        String from = "V" + source;
        for (int i = 0; i < distances.length; i++) {
            String to = "V" + i;
            if (i != source && distances[i] != INF) System.out.println(from + "->" + to + ": " + distances[i]);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}