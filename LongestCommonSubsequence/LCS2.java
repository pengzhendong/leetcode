import java.util.*;

public class LCS2 {
    public static int longestCommonSubsequence(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] memo = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) memo[i][j] = memo[i - 1][j - 1] + 1;
                else memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }

        return memo[m][n];
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
 
