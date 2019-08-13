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

public class Kruskal2 {
    private static final int INF = Integer.MAX_VALUE;

    private int[] ids;
    private int[] sz;

    private List<List<Edge>> graph;
    private int vNum;
    private List<Edge> edges;

    public Kruskal2(List<List<Edge>> graph) {
        this.graph = graph;
        // 初始化每个顶点对应一个连通分量，连通分量的触点数为 1
        vNum = graph.size();
        ids = new int[vNum];
        sz = new int[vNum];
        for (int i = 0; i < vNum; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
        // 对边进行排序
        edges = getSortedEdges();
    }

    private List<Edge> getSortedEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < vNum; i++) {
            for (Edge edge : graph.get(i)) {
                // i: edge.from
                edges.add(new Edge(edge.from, edge.to, edge.weight));
            }
        }
        Collections.sort(edges, (o1, o2) -> o1.weight - o2.weight);
        return edges;
    }

    public boolean connected(int p, int q) { return find(p) == find(q); }

    private int find(int p) {
        while (p != ids[p]) p = ids[p];
        return p;
    }

    public void weightedQuickUnion(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            ids[pRoot] = ids[qRoot];
            sz[qRoot] += sz[pRoot];
        } else {
            ids[qRoot] = ids[pRoot];
            sz[pRoot] += sz[qRoot];
        }
    }

    public Edge[] kruskal() {
        // 最小生成树最终保留 V - 1 条边
        int index = 0;
        Edge[] mst = new Edge[vNum - 1];

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            // 该边的两个端点原来没有连通，不属于同一个连通分量，即选择该边后不会形成环路
            if (!connected(edge.from, edge.to)) {
                // 将这两个触点所在连通分量合并
                weightedQuickUnion(edge.from, edge.to);
                // 将该边添加到最小生成树中
                mst[index++] = edge;
            }
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

        Kruskal2 kruskal = new Kruskal2(graph);
        Edge[] mst = kruskal.kruskal();
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