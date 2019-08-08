import java.util.*;
/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层次遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (53.82%)
 * Total Accepted:    18.8K
 * Total Submissions: 34.7K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 返回其层次遍历结果：
 * 
 * [
 * ⁠ [3],
 * ⁠ [9,20],
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        
        Queue<AbstractMap.SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new AbstractMap.SimpleEntry<>(root, 0));
        while (!queue.isEmpty()) {
            root = queue.peek().getKey();
            int level = queue.peek().getValue();
            queue.poll();
            if (ret.size() == level) ret.add(new ArrayList<>());
            ret.get(level).add(root.val);
            if (root.left != null) queue.add(new AbstractMap.SimpleEntry<>(root.left, level + 1));
            if (root.right != null) queue.add(new AbstractMap.SimpleEntry<>(root.right, level + 1));
        }
        return ret;
    }
}

