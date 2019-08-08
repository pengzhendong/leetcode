import java.util.*;
/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * https://leetcode-cn.com/problems/triangle/description/
 *
 * algorithms
 * Medium (58.28%)
 * Total Accepted:    11K
 * Total Submissions: 18.8K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 
 * 例如，给定三角形：
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 
 * 说明：
 * 
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
class Solution {
    private int[][] memo;
    private boolean[][] calced;

    private int minimum(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) return 0;
        if (!calced[i][j]) {
            memo[i][j] = triangle.get(i).get(j) + Math.min(minimum(triangle, i + 1, j), minimum(triangle, i + 1, j + 1));
            calced[i][j] = true;
        }
        return memo[i][j];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        memo = new int[n][n];
        calced = new boolean[n][n];
        return minimum(triangle, 0, 0);
    }
}

