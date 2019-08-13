import java.util.*;

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

    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int size = graph.size();

        int[] distances = new int[size];
        // 可以使用优先队列维护每个顶点到 source 的最短距离
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        Arrays.fill(distances, INF);
        distances[source] = 0;
        pq.add(new Edge(source, 0));
        
        while (!pq.isEmpty()) {
            // 取出距离 source 最近的顶点
            Edge min_edge = pq.poll();
            // 每次更新顶点到 source 的最短距离的时候，都会将该顶点加入优先队列。
            // 所以队列中某一个顶点可能会出现多次，但到 source 的最短距离是一直在减小的，因为 distances 记录着最短的距离。
            // 因此 min_edge.weight != distances[min_edge.to]，表示该顶点的信息不是这一轮的，继续取。
            if (min_edge.weight != distances[min_edge.to]) continue;

            for (Edge edge : graph.get(min_edge.to)) {
                int distance = min_edge.weight + edge.weight;
                if (distance < distances[edge.to]) {
                    distances[edge.to] = distance;
                    // 此处加入优先队列的不是边，只是记录了每个顶点到 source 的最短距离
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