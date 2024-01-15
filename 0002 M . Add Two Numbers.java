// 2. Add Two Numbers
// https://leetcode.com/problems/add-two-numbers/description/

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode current = res;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 == null) ? 0 : l1.val;
            int val2 = (l2 == null) ? 0 : l2.val;
            int tmp = val1 + val2 + carry;
            carry = tmp / 10;
            current.next = new ListNode(tmp % 10);
            current = current.next;
          
            if (l1 == null && l2 == null) {
                break;
            } else if (l1 == null) {
                l2 = l2.next;
            } else if (l2 == null) {
                l1 = l1.next;                            
            } else {
                l2 = l2.next;
                l1 = l1.next;             
            }
          
        }
        return res.next;
    }
}

//better exchange!!!
l1 = (l1 != null) ? l1.next : null;
l2 = (l2 != null) ? l2.next : null;

//thus:
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode current = res;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 == null) ? 0 : l1.val;
            int val2 = (l2 == null) ? 0 : l2.val;
            int tmp = val1 + val2 + carry;
            carry = tmp / 10;
            current.next = new ListNode(tmp % 10);
            current = current.next;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        return res.next;
    }
}
