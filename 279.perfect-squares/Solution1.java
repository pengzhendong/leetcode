import java.util.*;
/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 *
 * https://leetcode-cn.com/problems/perfect-squares/description/
 *
 * algorithms
 * Medium (47.83%)
 * Total Accepted:    6.5K
 * Total Submissions: 13.4K
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
    public int numSquares(int n) {
        Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(new AbstractMap.SimpleEntry<>(n, 0));
        visited[n] = true;
        while (!queue.isEmpty()) {
            int num = queue.peek().getKey();
            int step = queue.peek().getValue();
            queue.poll();

            for (int i = 1; ; i++) {
                int tmp = num - i * i;
                if (tmp < 0) break;
                if (tmp == 0) return step + 1;

                if (!visited[tmp]) {
                    queue.add(new AbstractMap.SimpleEntry<>(tmp, step + 1));
                    visited[tmp] = true;
                }
            }
        }
        return 0;
    }
}

