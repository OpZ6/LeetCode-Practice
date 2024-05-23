//24. Swap Nodes in Pairs
//https://leetcode.com/problems/swap-nodes-in-pairs/

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

//recursion
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode tmp = new ListNode(0, head);
        
        swapHelper(head, tmp);

        return tmp.next;
    }

    private void swapHelper(ListNode head, ListNode former) {
        if (head != null && head.next != null){
            ListNode nxt = head.next;
            ListNode tail = nxt.next;
            former.next = nxt;
            head.next = tail;
            nxt.next = head;

            swapHelper(tail, head);
        }
    }
}

//easier
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head.next;
        head.next = swapPairs(head.next.next);
        temp.next = head;

        return temp;
    }
}
