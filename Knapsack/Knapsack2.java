import java.util.Arrays;

public class Knapsack2 {
    public static int knapsack01(int[] w, int[] v, int C) {
        int len = w.length;
        if (len == 0 || C == 0) return 0;

        int[][] memo = new int[len][C + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        for (int j = 0; j <= C; j++) {
            memo[0][j] = (j >= w[0]) ? v[0] : 0;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= C; j++) {
                memo[i][j] = memo[i - 1][j];
                if (j >= w[i]) memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
            }
        }
        return memo[len - 1][C];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int C = 5;
        long startTime = System.currentTimeMillis();
        System.out.println(knapsack01(w, v, C));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}