import java.util.*;
/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
 *
 * algorithms
 * Medium (57.43%)
 * Total Accepted:    15.5K
 * Total Submissions: 26.9K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]  
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3 
 * 
 * 输出: [1,2,3]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
class Command {
    public static final int GO = 0;
    public static final int PRINT = 1;
    int op;
    TreeNode node;
    Command(int op, TreeNode node) {
        this.op = op;
        this.node = node;
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Command.GO, root));
        while (!stack.empty()) {
            Command command = stack.pop();
            root = command.node;
            if (command.op == Command.PRINT) ret.add(root.val);
            else {
                if (root.right != null) stack.push(new Command(Command.GO, root.right));
                if (root.left != null) stack.push(new Command(Command.GO, root.left));
                stack.push(new Command(Command.PRINT, root));
            }
        }
        return ret;
    }
}

