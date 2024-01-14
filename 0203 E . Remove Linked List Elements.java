// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/description/

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        ListNode current = tmp;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            }
            else { //important!!! dont do at any time but if else choice
                current = current.next;                
            }  
        }
        return tmp.next;
    }
}

//use recursion
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head==null)
        {
            return null;
        }
        head.next=removeElements(head.next,val);
        if (head.val == val)
        {
            return head.next;
        }    
        else 
        {
            return head;
        }
    }        
}
