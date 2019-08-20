import java.util.*;
/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (72.35%)
 * Total Accepted:    16.7K
 * Total Submissions: 23.2K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * ⁠ [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    private void generateSets(int[] nums, int start, List<Integer> s) {
        ret.add(new ArrayList<>(s));
        for (int i = start; i < nums.length; i++) {
            s.add(nums[i]);
            generateSets(nums, i + 1, s);
            s.remove(s.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        generateSets(nums, 0, new ArrayList<>());
        return ret;
    }
}

