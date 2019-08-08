import java.util.*;
/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (53.22%)
 * Total Accepted:    5.8K
 * Total Submissions: 10.8K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void generateSets(int[] nums, int start, List<Integer> s) {
        ret.add(new ArrayList<>(s));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            s.add(nums[i]);
            generateSets(nums, i + 1, s);
            s.remove(s.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        generateSets(nums, 0, new ArrayList<>());
        return ret;
    }
}

