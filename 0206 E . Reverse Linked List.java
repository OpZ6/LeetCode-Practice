// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/description/

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
    public ListNode reverseList(ListNode head) {
        ListNode current_node = head;
        ListNode prev_node = null;
        if (current_node == null) {
            return null;
        }
        while (current_node.next != null) {
            ListNode next_node = current_node.next;
            current_node.next = prev_node;
            prev_node = current_node;
            current_node = next_node;
        }
        current_node.next = prev_node;//important!!!
        return current_node;
    }
}

//better!!!
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode current_node = head;
        ListNode prev_node = null;
        // if (current_node == null) {
        //     return null;
        // }
        while (current_node != null) {
            ListNode next_node = current_node.next;
            current_node.next = prev_node;
            prev_node = current_node;
            current_node = next_node;
        }
        //current_node.next = prev_node;//important!!!
        return prev_node;//last node is the actuall end, and the current node is null!!!
    }

