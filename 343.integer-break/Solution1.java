/*
 * @lc app=leetcode.cn id=343 lang=java
 *
 * [343] 整数拆分
 *
 * https://leetcode-cn.com/problems/integer-break/description/
 *
 * algorithms
 * Medium (49.50%)
 * Total Accepted:    4.8K
 * Total Submissions: 9.7K
 * Testcase Example:  '2'
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 
 * 示例 1:
 * 
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 
 * 示例 2:
 * 
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
class Solution {
    private int max3(int a, int b, int c) { 
        return Math.max(a, Math.max(b, c)); 
    }

    private int[] memo;

    private int breakInteger(int n) {
        if (memo[n] == 0) {
            for (int i = 1; i < n; i++) {
                memo[n] = max3(memo[n], i * (n - i), i * breakInteger(n - i));
            }
        }
        return memo[n];
    }

    public int integerBreak(int n) {
        memo = new int[n + 1];
        memo[2] = 1;
        return breakInteger(n);
    }
}

