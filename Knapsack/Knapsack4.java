import java.util.*;

public class Knapsack2 {
    public static int knapsack01(int[] w, int[] v, int C) {
        int len = w.length;
        if (len == 0 || C == 0) return 0;

        int[] memo = new int[C + 1];
        Arrays.fill(row, -1);
        
        for (int j = 0; j <= C; j++) {
            memo[j] = (j >= w[0]) ? v[0] : 0;
        }

        for (int i = 1; i < len; i++) {
            for (int j = C; j >= w[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);
            }
        }
        return memo[C];
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