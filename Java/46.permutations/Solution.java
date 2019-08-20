import java.util.*;
/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [47] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (67.49%)
 * Total Accepted:    20.1K
 * Total Submissions: 29.8K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 */
class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private boolean[] used;

    private void generatePermutation(int[] nums, int count, List<Integer> p) {
        if (count == nums.length) ret.add(new ArrayList<>(p));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    p.add(nums[i]);
                    used[i] = true;
                    generatePermutation(nums, count + 1, p);
                    p.remove(p.size() - 1);
                    used[i] = false;
                }
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return ret;
        used = new boolean[nums.length];
        generatePermutation(nums, 0, new ArrayList<>());
        return ret;
    }
}

