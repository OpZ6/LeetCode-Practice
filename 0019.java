//19. Remove Nth Node From End of List
//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

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
//two while
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode Current_Node = head;
        int length = 0;
        while (Current_Node != null) {
            length++;
            Current_Node = Current_Node.next;
        }
        if (n > length) {
            return null;
        }
        int pos = length - n;
        if (pos == 0) {
            return head.next;
        }
        Current_Node = head;
        while (pos > 1) {
            Current_Node = Current_Node.next;
            pos--;
        }
        Current_Node.next = Current_Node.next.next;
        return head;
    }
}

//two point
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode Node_Fast = head;
        ListNode Node_Slow = head;
        while (n > 0) {
            if (Node_Fast.next == null){
                return head.next;
            }
            else {
                Node_Fast = Node_Fast.next; 
            }
            n--;
        }
        while (Node_Fast.next != null) {
            Node_Fast = Node_Fast.next;
            Node_Slow = Node_Slow.next;
        }
        Node_Slow.next = Node_Slow.next.next;
        return head;
    }
}

