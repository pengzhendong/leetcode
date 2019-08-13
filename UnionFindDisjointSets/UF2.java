// Quick Union
public class UF2 {
    // ids[i] 记录的是触点 i 所在的连通分量的另一个触点，也有可能是自己（此时表示该连通分量）
    private int[] ids;
    private int count;

    public UF2(int N) {
        count = N;
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    // 查找操作比较慢，需要往上查找直到根触点（树根）
    private int find(int p) { 
        while (p != ids[p]) p = ids[p];
        return p; 
    }
    // 可以快速合并，只需要将其中一个连通分量的根触点连接到另一个连通分量的根触点
    public void quickUnion(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        ids[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        UF2 uf = new UF2(10);
        int[][] data = {{4, 3}, {3, 8}, {6, 5}, {9, 4}, {2, 1}, {8, 9}, {5, 0}, {7, 2}, {6, 1}, {1, 0}, {6, 7}};
        for (int[] pair : data) {
            int p = pair[0];
            int q = pair[1];
            if (!uf.connected(p, q)) uf.quickUnion(p, q);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(uf.count());
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}