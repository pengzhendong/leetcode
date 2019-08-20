/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 *
 * https://leetcode-cn.com/problems/perfect-squares/description/
 *
 * algorithms
 * Medium (48.79%)
 * Total Accepted:    8.8K
 * Total Submissions: 18K
 * Testcase Example:  '12'
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 
 * 示例 1:
 * 
 * 输入: n = 12
 * 输出: 3 
 * 解释: 12 = 4 + 4 + 4.
 * 
 * 示例 2:
 * 
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
class Solution {
    private int[] memo;

    private int squaresNum(int n) {
        if (memo[n] == Integer.MAX_VALUE) {
            for (int i = 1; i * i <= n; i++) {
                memo[n] = Math.min(memo[n], 1 + squaresNum(n - i * i));
            }
        }
        return memo[n];
    }

    public int numSquares(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = 0;
        return squaresNum(n);
    }
}

