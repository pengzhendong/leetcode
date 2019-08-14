class Edge {
    int from;
	int to;
	int weight;
	Edge(int from, int to, int weight) {
        this.from = from;
		this.to  = to;
		this.weight = weight;
	}
}

public class Prim1 {
    private static final int INF = Integer.MAX_VALUE;

    public static Edge[] prim(int[][] graph, int source) {
        int vNum = graph.length;
        Edge[] mst = new Edge[vNum - 1];
        boolean[] marked = new boolean[vNum];

        marked[source] = true;

        // 将 V - 1 条边加入最小生成树
        for (int n = 0; n < vNum - 1; n++) {
            Edge minEdge = new Edge(-1, -1, INF);
            // 遍历 from 在 marked， to 在 unmarked 的边，找权重最小的一条加入最小生成树，即标记 to 对应的顶点
            for (int i = 0; i < vNum; i++) {
                if (!marked[i]) continue;
                for (int j = 0; j < vNum; j++) {
                    if (marked[j]) continue;
                    if (graph[i][j] < minEdge.weight) minEdge = new Edge(i, j, graph[i][j]);
                }
            }

            marked[minEdge.to] = true;
            mst[n] = minEdge;
        }
        return mst;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {   0,  12, INF, INF, INF,  16,  14 },
            {  12,   0,  10, INF, INF,   7, INF },
            { INF,  10,   0,   3,   5,   6, INF },
            { INF, INF,   3,   0,   4, INF, INF },
            { INF, INF,   5,   4,   0,   2,   8 },
            {  16,   7,   6, INF,   2,   0,   9 },
            {  14, INF, INF, INF,   8,   9,   0 }
        };

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