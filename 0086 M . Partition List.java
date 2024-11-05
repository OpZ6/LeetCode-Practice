//86. Partition List
//https://leetcode.com/problems/partition-list/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // 创建两个虚拟头节点，分别用于存放小于 x 和大于等于 x 的节点
        ListNode smallHead = new ListNode(-1);
        ListNode largeHead = new ListNode(-1);
        ListNode smallTail = smallHead;
        ListNode largeTail = largeHead;

        // 遍历原链表，将节点分类
        while (head != null) {
            if (head.val < x) {
                smallTail.next = head;
                smallTail = smallTail.next;
            } else {
                largeTail.next = head;
                largeTail = largeTail.next;
            }
            // 移动到下一个节点
            head = head.next;
        }

        // 结束 large 链表，避免形成环
        largeTail.next = null;

        // 连接小链表和大链表
        smallTail.next = largeHead.next;

        // 返回结果链表的头节点
        return smallHead.next;
    }
}
