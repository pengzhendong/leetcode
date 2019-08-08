/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (42.08%)
 * Total Accepted:    7.1K
 * Total Submissions: 16.8K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        head = dummyHead;
        for (int i = 1; i < m; i++) {
            head = head.next;
        }

        ListNode current = head.next;
        ListNode begin = current;
        ListNode end = null;
        for (int i = m; i <= n; i++) {
            ListNode next = current.next;
            current.next = end;
            end = current;
            current = next;
        }
        head.next = end;
        begin.next = current;
        return dummyHead.next;
    }
}

