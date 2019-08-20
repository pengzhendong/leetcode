/*
 * @lc app=leetcode.cn id=143 lang=java
 *
 * [143] 重排链表
 *
 * https://leetcode-cn.com/problems/reorder-list/description/
 *
 * algorithms
 * Medium (47.66%)
 * Total Accepted:    3.5K
 * Total Submissions: 7.3K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 示例 1:
 * 
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 
 * 示例 2:
 * 
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
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

    private void merge(ListNode l1, ListNode l2) {
        ListNode current = l1;
        l1 = l1.next;
        while (l1 != null) {
            current.next = l2;
            l2 = l2.next;
            current = current.next;
            current.next = l1;
            l1 = l1.next;
            current = current.next;
        }
        current.next = l2;
    }

    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode current = head;
        ListNode middle = head;
        while (current.next != null && current.next.next != null) {
            current = current.next.next;
            middle = middle.next;
        }
        ListNode l = rotate(middle.next);
        middle.next = null;
        merge(head, l);
    }
}

