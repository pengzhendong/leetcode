/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/description/
 *
 * algorithms
 * Easy (58.52%)
 * Total Accepted:    38.8K
 * Total Submissions: 66.4K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 反转一个单链表。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode reverse(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        ListNode node = l2;
        l2 = l2.next;
        node.next = l1;
        return reverse(node, l2);        
    }

    public ListNode reverseList(ListNode head) {
        ListNode ret = reverse(null, head);
        return ret;
    }
}
