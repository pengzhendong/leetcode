// Weighted Quick Union
public class UF3 {
    private int[] ids;
    private int[] sz;
    private int count;

    public UF3(int N) {
        count = N;
        ids = new int[N];
        // sz[i] 记录触点 i 作为根触点时，其对应的连通分量的大小（即该连通分量中触点的个数，树中节点的个数）
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    private int find(int p) { 
        while (p != ids[p]) p = ids[p];
        return p; 
    }
    // 为了避免树的高度过大，提高查找的效率，要将触点数量较少的连通分量连接到触点数量较多的连通分量
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
        count--;
    }

    public static void main(String[] args) {
        UF3 uf = new UF3(10);
        int[][] data = {{4, 3}, {3, 8}, {6, 5}, {9, 4}, {2, 1}, {8, 9}, {5, 0}, {7, 2}, {6, 1}, {1, 0}, {6, 7}};
        for (int[] pair : data) {
            int p = pair[0];
            int q = pair[1];
            if (!uf.connected(p, q)) uf.weightedQuickUnion(p, q);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(uf.count());
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}