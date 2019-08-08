import java.util.*;
/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (43.81%)
 * Total Accepted:    17.3K
 * Total Submissions: 39K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入:
 * [
 *  1->4->5,
 *  1->3->4,
 *  2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
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
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) { return o1.val - o2.val; }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) pq.offer(lists[i]);
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        while (!pq.isEmpty()) {
            current.next = pq.poll();
            current = current.next;
            if (current.next != null) pq.offer(current.next);
        }
        return dummyHead.next;
    }
}

