/*
 * @lc app=leetcode.cn id=234 lang=java
 *
 * [234] 回文链表
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/description/
 *
 * algorithms
 * Easy (35.35%)
 * Total Accepted:    17.3K
 * Total Submissions: 48.8K
 * Testcase Example:  '[1,2]'
 *
 * 请判断一个链表是否为回文链表。
 * 
 * 示例 1:
 * 
 * 输入: 1->2
 * 输出: false
 * 
 * 示例 2:
 * 
 * 输入: 1->2->2->1
 * 输出: true
 * 
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
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
    private ListNode rotate(ListNode head) {
        ListNode tail = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = tail;
            tail = head;
            head = next;
        }
        return tail;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode current = head;
        ListNode middle = head;
        while (current.next != null && current.next.next != null) {
            current = current.next.next;
            middle = middle.next;
        }
        middle.next = rotate(middle.next);

        ListNode p = head;
        ListNode q = middle.next;
        while (q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }
}

