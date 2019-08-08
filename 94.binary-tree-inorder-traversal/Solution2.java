import java.util.*;
/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (64.38%)
 * Total Accepted:    22.2K
 * Total Submissions: 34.5K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的中序 遍历。
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
 * 输出: [1,3,2]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
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
    public List<Integer> inorderTraversal(TreeNode root) {
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
                stack.push(new Command(Command.PRINT, root));
                if (root.left != null) stack.push(new Command(Command.GO, root.left));
            }
        }
        return ret;
    }
}

