import java.util.*;
/*
 * @lc app=leetcode.cn id=145 lang=java
 *
 * [145] 二叉树的后序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Hard (64.07%)
 * Total Accepted:    11.6K
 * Total Submissions: 18K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Command.GO, root));
        while (!stack.empty()) {
            Command command = stack.pop();
            root = command.node;
            if (command.op == Command.PRINT) ret.add(root.val);
            else {
                stack.push(new Command(Command.PRINT, root));
                if (root.right != null) stack.push(new Command(Command.GO, root.right));
                if (root.left != null) stack.push(new Command(Command.GO, root.left));
            }
        }
        return ret;
    }
}

