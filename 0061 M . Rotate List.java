// 61. Rotate List
// https://leetcode.com/problems/rotate-list/description/

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
    public ListNode rotateRight(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        int len = 0;
        while (fast != null) {
            fast = fast.next;
            len++;
        }
        k = (len == 0) ? 0 : k % len;
        if (k == 0) {
            return head;
        }
        fast = head;
        while (k >= 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        slow = newHead;
        while (slow.next != null) {
            slow = slow.next;
        }
        slow.next = head;
        return newHead;
    }
}

//shorter code
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return head;
        int n = 0;
        ListNode temp = head, newHead;
        for (; temp != null; temp = temp.next, n++)
            ;
        k %= n;
        if (k == 0)
            return head;
        k = n - k + 1;
        for (temp = head; k-- > 2; temp = temp.next)
            ;
        newHead = temp.next;
        temp.next = null;
        for (temp = newHead; temp.next != null; temp = temp.next)
            ;
        temp.next = head;
        return newHead;
    }
}
