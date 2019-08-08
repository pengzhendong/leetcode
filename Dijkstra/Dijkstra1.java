class Dijkstra1 {
    private static final int INF = Integer.MAX_VALUE;

    // 稀疏图使用临界矩阵。单源最短路径，时间复杂度 O(V^2)
    public static int[] dijkstra(int[][] graph, int source) {
        int size = graph.length;

        // 将顶点分为两个集合 found 和 unfound，前者表示已经找到 source 到其中顶点的最短距离
        boolean[] found = new boolean[size];
        // 维护数组 distances 表示 source 到其他顶点的最短距离
        int[] distances = new int[size];
        
        // 初始化 visited 和 distances 数组
        found[source] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[source][i];
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

            // 用 index 点更新其他 unfound 点到 source 的最短距离
            for (int j = 0; j < size; j++) {
                if (!found[j] && graph[index][j] != INF) {
                    int distance = min_dinstance + graph[index][j];
                    // 松弛 (Relaxation) 操作
                    if (distance < distances[j]) distances[j] = distance;
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] graph = {
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