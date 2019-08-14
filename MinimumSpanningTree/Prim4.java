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

public class Prim4 {
    private static final int INF = Integer.MAX_VALUE;

    private static boolean[] marked;
    // minEdges/minWeights 记录 unmarked 的顶点到树中顶点的最小权值的边/权值
    // 记录边为了换掉优先队列中的这条边
    private static Edge[] minEdges;
    private static int[] minWeights;
    private static Queue<Edge> pq;

    private static void visit(List<List<Edge>> graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.get(v)) {
            if (marked[edge.to]) continue;

            if (edge.weight < minWeights[edge.to]) {
                pq.remove(minEdges[edge.to]);
                pq.add(edge);
                minEdges[edge.to] = edge;
                minWeights[edge.to] = edge.weight;
            }
        }
    }

    // 使用优先队列优化的及时版本，对于每个 unmarked 的顶点，在队列中只保留其一条到树中顶点的最小权值的边
    public static Edge[] lazyPrim(List<List<Edge>> graph, int source) {
        int vNum = graph.size();
        marked = new boolean[vNum];
        minEdges = new Edge[vNum];
        minWeights = new int[vNum];
        Arrays.fill(minWeights, INF);

        pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        Edge[] mst = new Edge[vNum - 1];
        int index = 0;

        visit(graph, source);

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            if (marked[minEdge.from] && marked[minEdge.to]) continue;
            mst[index++] = minEdge;
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