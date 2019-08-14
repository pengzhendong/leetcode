import java.util.*;

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

public class Prim3 {
    private static final int INF = Integer.MAX_VALUE;

    private static boolean[] marked;
    private static Queue<Edge> pq;

    // 标记顶点 v 并将所有连接 v 和未被标记顶点的边加入 pq
    private static void visit(List<List<Edge>> graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.get(v)) {
            if (!marked[edge.to]) pq.add(edge);
        }
    }

    // 使用优先队列优化的 Lazy 版本，即将无效边留在队列中，等到要删除的时候再检查有效性
    // 邻接矩阵的实现与邻接表类似，此处使用邻接表更加直观
    public static Edge[] lazyPrim(List<List<Edge>> graph, int source) {
        int vNum = graph.size();
        marked = new boolean[vNum];
        pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        Edge[] mst = new Edge[vNum - 1];
        int index = 0;

        visit(graph, source);

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            // 如果这条边的两个端点都被标记过了，表示该边已失效
            if (marked[minEdge.from] && marked[minEdge.to]) continue;
            mst[index++] = minEdge;

            // 将顶点添加到树中
            if (!marked[minEdge.to]) visit(graph, minEdge.to);
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

        Edge[] mst = lazyPrim(graph, 0);
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