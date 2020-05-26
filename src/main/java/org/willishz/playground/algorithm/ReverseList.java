package org.willishz.playground.algorithm;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/dong-hua-yan-shi-duo-chong-jie-fa-206-fan-zhuan-li/
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode each = listNode;
        while (each != null) {
            System.out.print(each.val + " > ");
            each = each.next;
        }
        System.out.println();
//        listNode = ReverseList.reverseListRecursion(listNode);
        listNode = ReverseList.reverseList(listNode);
        each = listNode;
        while (each != null) {
            System.out.print(each.val + " > ");
            each = each.next;
        }
    }

    /**
     * recursion
     *
     * @param head
     * @return
     */
    public static ListNode reverseListRecursion(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }

    /**
     * 双指针
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
