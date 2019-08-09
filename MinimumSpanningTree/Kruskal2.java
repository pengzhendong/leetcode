import java.util.Arrays;

class Edge {
	int to;
	int weight;
	Edge(int to, int weight) {
		this.to  = to;
		this.weight = weight;
	}
}

public class Kruskal2 {
    private static final int INF = Integer.MAX_VALUE;

    public static void kruskal(List<List<Edge>> graph) {

    }

    public static void main(String[] args) {
        int[][] data = {
            {   0,  12, INF, INF, INF,  16,  14},
            {  12,   0,  10, INF, INF,   7, INF},
            { INF,  10,   0,   3,   5,   6, INF},
            { INF, INF,   3,   0,   4, INF, INF},
            { INF, INF,   5,   4,   0,   2,   8},
            {  16,   7,   6, INF,   2,   0,   9},
            {  14, INF, INF, INF,   8,   9,   0}
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

        kruskal(graph);

        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}