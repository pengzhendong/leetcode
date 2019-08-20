import java.util.*;
/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (66.29%)
 * Total Accepted:    9.2K
 * Total Submissions: 13.8K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void generateCombinations(int n, int k, int start, List<Integer> c) {
        if (k == 0) ret.add(new ArrayList<>(c));
        else {
            for (int i = start; i <= n - k + 1; i++) {
                c.add(i);
                generateCombinations(n, k - 1, i + 1, c);
                c.remove(c.size() - 1);
            } 
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <=0 || k > n) return ret;
        generateCombinations(n, k, 1, new ArrayList<>());
        return ret;
    }
}

