package org.willishz.playground.arithmetic;

/**
 * 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode a = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode b = new ListNode(2, new ListNode(3, new ListNode(4, null)));
        ListNode ab = new MergeTwoSortedLists().mergeTwoLists(a, b);
        do {
            System.out.println(ab.val + ">");
            ab = ab.next;
        } while (ab != null);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}