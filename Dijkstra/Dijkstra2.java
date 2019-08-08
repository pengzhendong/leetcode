class Dijkstra2 {
    private static final int INF = Integer.MAX_VALUE;

    public static int[][] dijkstra(int[][] graph, int source) {
        int size = graph.length;

        boolean[] found = new boolean[size];
        int[] distances = new int[size];
        // 记录 source 到每个顶点的最短路径同时，记录每个顶点的前驱顶点
        int[] preNodes = new int[size];
        
        found[source] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[source][i];
        }

        for (int i = 0; i < size - 1; i++) {
            int index = -1;
            int min_dinstance = INF;

            for (int j = 0; j < size; j++) {
                if (!found[j] && distances[j] < min_dinstance) {
                    index = j;
                    min_dinstance = distances[j];
                }
            }
            found[index] = true;

            for (int j = 0; j < size; j++) {
                if (!found[j] && graph[index][j] != INF) {
                    int distance = min_dinstance + graph[index][j];

                    if (distance < distances[j]) {
                        distances[j] = distance;
                        // 如果 index 顶点更新了 j 顶点到 source 的最短距离，index 顶点即为 j 顶点的前驱顶点
                        preNodes[j] = index;
                    }
                }
            }
        }

        return new int[][] { distances, preNodes };
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
        int[][] ret = dijkstra(graph, source);
        int[] distances = ret[0];
        int[] preNodes = ret[1];

        int size = distances.length;
        String[] paths = new String[size];

        for (int i = 0; i < size; i++) {
            int current = i;
            paths[i] = "V" + i;
            while (current != source) {
                current = preNodes[current];
                paths[i] = "V" + current + "->" + paths[i];
            }
        }

        for (int i = 0; i < size; i++) {
            if (i != source && distances[i] != INF) System.out.println(paths[i] + ": " + distances[i]);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}