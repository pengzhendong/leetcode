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

public class Kruskal1 {
    private static final int INF = Integer.MAX_VALUE;

    private static List<Edge> getSortedEdges(int[][] graph) {
        int size = graph.length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (graph[i][j] != INF) edges.add(new Edge(i, j, graph[i][j]));
            }
        }
        Collections.sort(edges, (o1, o2) -> o1.weight - o2.weight);

        return edges;
    }


    // 记录顶点在"最小生成树"中的终点，顶点的终点是"在最小生成树中与它连通的最大顶点"。
    // 然后每次需要将一条边添加到最小生存树时，判断该边的两个顶点的终点是否重合，重合的话则会构成回路。
    public static void kruskal(int[][] graph) {
        List<Edge> edges = getSortedEdges(graph);
        int eNum = edges.size();
        
        Edge[] ret = new Edge[eNum];
        int[] dests = new int[eNum];
        Arrays.fill(dests, -1);

        for (int i = 0; i < eNum; i++) {
            Edge  = edges.get(i)
        }
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

        kruskal(graph);

        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}