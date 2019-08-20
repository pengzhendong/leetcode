import java.util.*;
/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (63.34%)
 * Total Accepted:    15.5K
 * Total Submissions: 24.4K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *  [2,2,2,2],
 *  [2,3,3],
 *  [3,5]
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void findCombinations(int[] candidates, int start, int target, List<Integer> c) {
        if (target == 0) ret.add(new ArrayList<>(c));
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) break;
            c.add(candidates[i]);
            findCombinations(candidates, i, target - candidates[i], c);
            c.remove(c.size() - 1);
        }        
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        findCombinations(candidates, 0, target, new ArrayList<>());
        return ret;
    }
}

