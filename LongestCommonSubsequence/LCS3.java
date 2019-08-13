import java.util.*;

public class LCS3 {
    public static int longestCommonSubsequence(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] memo = new int[2][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) memo[i % 2][j] = memo[(i - 1) % 2][j - 1] + 1;
                else memo[i % 2][j] = Math.max(memo[(i - 1) % 2][j], memo[i % 2][j - 1]);
            }
        }

        return memo[m % 2][n];
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
 
