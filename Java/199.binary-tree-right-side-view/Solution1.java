import java.util.*;
/*
 * @lc app=leetcode.cn id=199 lang=java
 *
 * [199] 二叉树的右视图
 *
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/description/
 *
 * algorithms
 * Medium (56.05%)
 * Total Accepted:    3.4K
 * Total Submissions: 6K
 * Testcase Example:  '[1,2,3,null,5,null,4]'
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 示例:
 * 
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * 
 * ⁠  1            <---
 * ⁠/   \
 * 2     3         <---
 * ⁠\     \
 * ⁠ 5     4       <---
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList();
        if (root == null) return ret;
        Queue<AbstractMap.SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new AbstractMap.SimpleEntry<>(root, 0));
        while (!queue.isEmpty()) {
            root = queue.peek().getKey();
            int level = queue.peek().getValue();
            queue.poll();
            if (ret.size() == level) ret.add(root.val);
            else ret.set(level, root.val);
            if (root.left != null) queue.add(new AbstractMap.SimpleEntry<>(root.left, level + 1));
            if (root.right != null) queue.add(new AbstractMap.SimpleEntry<>(root.right, level + 1));
        }
        return ret;
    }
}

