import java.util.*;
/*
 * @lc app=leetcode.cn id=445 lang=java
 *
 * [445] 两数相加 II
 *
 * https://leetcode-cn.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (46.49%)
 * Total Accepted:    2.8K
 * Total Submissions: 6K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 进阶:
 * 
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 
 * 示例:
 * 
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        for (; l1 != null; l1 = l1.next) s1.push(l1.val);
        for (; l2 != null; l2 = l2.next) s2.push(l2.val);

        ListNode head = null;
        int carry = 0;
        while (!s1.empty() || !s2.empty()) {
            int p = s1.empty() ? 0 : s1.pop();
            int q = s2.empty() ? 0 : s2.pop();
            int sum = p + q + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }

        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }
}

