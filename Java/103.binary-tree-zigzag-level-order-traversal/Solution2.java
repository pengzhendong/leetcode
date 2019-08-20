import java.util.*;
/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层次遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (47.12%)
 * Total Accepted:    7.6K
 * Total Submissions: 16.1K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 返回锯齿形层次遍历如下：
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
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
    private List<List<Integer>> ret = new ArrayList<>();

    private void order(TreeNode root, int level) {
        if (root == null) return;
        if (ret.size() == level) ret.add(new ArrayList<>());
        if (level % 2 == 0) ret.get(level).add(root.val);
        else ret.get(level).add(0, root.val);
        order(root.left, level + 1);
        order(root.right, level + 1);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        order(root, 0);
        return ret;
    }
}

