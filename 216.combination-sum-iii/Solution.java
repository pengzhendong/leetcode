import java.awt.*;
/*
 * @lc app=leetcode.cn id=216 lang=java
 *
 * [216] 组合总和 III
 *
 * https://leetcode-cn.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (64.59%)
 * Total Accepted:    3.7K
 * Total Submissions: 5.7K
 * Testcase Example:  '3\n7'
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 
 * 说明：
 * 
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 示例 1:
 * 
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 
 * 示例 2:
 * 
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void findCombinations(int k, int start, int n, List<Integer> c) {
        if (k == 0 && n == 0) ret.add(new ArrayList<>(c));
        for (int i = start; i <= 9; i++) {
            if (i > n) break;
            c.add(i);
            findCombinations(k - 1, i + 1, n - i, c);
            c.remove(c.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n < k) return ret;
        findCombinations(k, 1, n, new ArrayList<>());
        return ret;
    }
}

