/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/description/
 *
 * algorithms
 * Medium (59.75%)
 * Total Accepted:    14.1K
 * Total Submissions: 23.4K
 * Testcase Example:  '[[1,3,1],[1,5,1],[4,2,1]]'
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 示例:
 * 
 * 输入:
 * [
 *  [1,3,1],
 * ⁠ [1,5,1],
 * ⁠ [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] memo = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) memo[i][j] = grid[i][j];
                else if (j == 0) memo[i][j] = grid[i][j] + memo[i - 1][j];
                else if (i == 0) memo[i][j] = grid[i][j] + memo[i][j - 1];
                else memo[i][j] = grid[i][j] + Math.min(memo[i][j - 1], memo[i - 1][j]);
            }
        }
        return memo[n - 1][m - 1];
    }
}

