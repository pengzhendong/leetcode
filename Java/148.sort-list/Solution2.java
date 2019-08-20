/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (58.54%)
 * Total Accepted:    8.1K
 * Total Submissions: 13.8K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 
 * 示例 1:
 * 
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 
 * 示例 2:
 * 
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 自底向上，循环
class Solution {
    private ListNode split(ListNode head, int size) {
        while (--size > 0 && head != null) head = head.next;
        ListNode rest = head == null ? null : head.next;
        if (head != null) head.next = null;
        return rest;
    }

    private ListNode[] merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        while (current.next != null) current = current.next;
        return new ListNode[] {dummyHead.next, current};
    }

    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        for (int size = 1; size < len; size += size) {
            ListNode tail = dummyHead;
            current = dummyHead.next;
            while (current != null) {
                ListNode l1 = current;
                ListNode l2 = split(l1, size);
                current = split(l2, size);
                ListNode[] merged = merge(l1, l2);
                tail.next = merged[0];
                tail = merged[1];
            }
        }
        return dummyHead.next;
    }
}

