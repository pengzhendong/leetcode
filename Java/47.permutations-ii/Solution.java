import java.util.*;
/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (51.24%)
 * Total Accepted:    9.5K
 * Total Submissions: 18.5K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private boolean[] used;
    
    private void generatePermutation(int[] nums, int count, List<Integer> p) {
        if (count == nums.length) ret.add(new ArrayList<>(p));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
                used[i] = true;
                p.add(nums[i]);
                generatePermutation(nums, count + 1, p);
                p.remove(p.size() - 1);
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return ret;
        Arrays.sort(nums);
        used = new boolean[nums.length];
        generatePermutation(nums, 0, new ArrayList<>());
        return ret;
    }
}

