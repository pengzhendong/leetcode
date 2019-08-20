import java.util.*;
/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (54.12%)
 * Total Accepted:    10.9K
 * Total Submissions: 20K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *  [1,2,2],
 *  [5]
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void findCombinations(int[] candidates, int start, int target, List<Integer> c) {
        if (target == 0) ret.add(new ArrayList<>(c));
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) break;
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            c.add(candidates[i]);
            findCombinations(candidates, i + 1, target - candidates[i], c);
            c.remove(c.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        findCombinations(candidates, 0, target, new ArrayList<>());
        return ret;
    }
}

