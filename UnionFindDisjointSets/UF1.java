// Quick Find
public class UF1 {
    // ids[i] 记录的是触点 i 所在的连通分量
    private int[] ids;
    private int count;

    public UF1(int N) {
        count = N;
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return quickFind(p) == quickFind(q); }
    // 可以快速查找 p 所在的连通分量，即 ids[p]
    public int quickFind(int p) { return ids[p]; }
    // 合并操作比较慢，需要修改连通分量中所有触点对应的连通分量，时间复杂度 O(N)
    public void union(int p, int q) {
        int pID = quickFind(p);
        int qID = quickFind(q);
        if (pID == qID) return;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pID) ids[i] = qID;
        }
        count--;
    }

    public static void main(String[] args) {
        UF1 uf = new UF1(10);
        int[][] data = {{4, 3}, {3, 8}, {6, 5}, {9, 4}, {2, 1}, {8, 9}, {5, 0}, {7, 2}, {6, 1}, {1, 0}, {6, 7}};
        for (int[] pair : data) {
            int p = pair[0];
            int q = pair[1];
            if (!uf.connected(p, q)) uf.union(p, q);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(uf.count());
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}