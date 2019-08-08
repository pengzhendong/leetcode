import java.util.*;
/*
 * @lc app=leetcode.cn id=113 lang=java
 *
 * [113] 路径总和 II
 *
 * https://leetcode-cn.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (53.40%)
 * Total Accepted:    7K
 * Total Submissions: 13.2K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \    / \
 * ⁠       7    2  5   1
 * 
 * 返回:
 * 
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        if (root.left == null && root.right == null && root.val == sum) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            ret.add(list);
            return ret;
        }

        List<List<Integer>> leftS = pathSum(root.left, sum - root.val);
        for (int i = 0; i < leftS.size(); i++) {
            leftS.get(i).add(0, root.val);
        }

        List<List<Integer>> rightS = pathSum(root.right, sum - root.val);
        for (int i = 0; i < rightS.size(); i++) {
            rightS.get(i).add(0, root.val);
        }
        
        leftS.addAll(rightS);
        return leftS;
    }
}

