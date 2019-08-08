import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class Edge {
	int to;
	int weight;
	Edge(int to, int weight) {
		this.to  = to;
		this.weight = weight;
	}
}

class Dijkstra4 {
    private static final int INF = Integer.MAX_VALUE;

    // 由于需要多次取最小值，可以使用优先队列（插入一个元素的时间复杂度为 O(logn)）维护 found 中的点到 source 的最短距离
    // 单源最短路径，时间复杂度 O(ElogV)
    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int size = graph.size();

        int[] distances = new int[size];
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        Arrays.fill(distances, INF);
        distances[source] = 0;
        pq.add(new Edge(source, 0));
        
        // 循环 size - 1 次，每次找出一个顶点到 source 的最短距离
        while (!pq.isEmpty()) {
            // 从 unfound 集合中找一个距离 source 最近的点 min_edge.to，将该点标记为 found
            Edge min_edge = pq.poll();
            // found[min_edge.to] = true;
            if (distances[min_edge.to] < min_edge.weight) continue;

            for (Edge edge : graph.get(min_edge.to)) {
                int distance = min_edge.weight + edge.weight;
                if (distance < distances[edge.to]) {
                    distances[edge.to] = distance;
                    pq.add(new Edge(edge.to, distance));
                }
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
            List<Edge> edges = new LinkedList<>();
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