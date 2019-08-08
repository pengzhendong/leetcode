import java.util.Arrays;

class LCS1 {
    private static String s1;
    private static String s2;
    private static int[][] memo;

    private static int LCS(int i, int j) {
        if (i < 0 || j < 0) return 0;

        if (memo[i][j] == -1) {
            if (s1.charAt(i) == s2.charAt(j)) memo[i][j] = LCS(i - 1, j - 1) + 1;
            else memo[i][j] = Math.max(LCS(i - 1, j), LCS(i, j - 1));
        }
        return memo[i][j];
    }

    public static int longestCommonSubsequence(String A, String B) {
        int m = A.length();
        int n = B.length();

        s1 = A;
        s2 = B;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return LCS(m - 1, n - 1);
    }

    public static void main(String[] args){
        String A = "ABCBDAB";
        String B = "BDCABA";

        long startTime = System.currentTimeMillis();
        System.out.println(longestCommonSubsequence(A, B));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }
}

