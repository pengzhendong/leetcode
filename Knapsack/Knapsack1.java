import java.util.*;

public class Knapsack1 {
    private static int[] weight;
    private static int[] value;
    private static int[][] memo;

    private static int bestValue(int index, int C) {
        if (index < 0 || C <= 0) return 0;

        if (memo[index][C] == -1) {
            memo[index][C] = bestValue(index - 1, C);
            if (C >= weight[index]) memo[index][C] = Math.max(memo[index][C], value[index] + bestValue(index - 1, C - weight[index]));
        }
        return memo[index][C];
    }

    public static int knapsack01(int[] w, int[] v, int C) {
        weight = w;
        value = v;

        int len = w.length;
        memo = new int[len][C + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return bestValue(len - 1, C);
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