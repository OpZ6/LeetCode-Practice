// 328. Odd Even Linked List
// https://leetcode.com/problems/odd-even-linked-list/description/

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
    public ListNode oddEvenList(ListNode head) {
      
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }//exception case!!!
        ListNode current = head;
        ListNode tmp = new ListNode(0);
        ListNode tmp_head = tmp;
      
        while (current.next != null && current.next.next != null) {
            tmp.next = current.next;
            tmp = tmp.next;
            current.next = current.next.next;
            current = current.next;
        }
      
        if (current.next != null) {
            tmp.next = current.next;
            tmp = tmp.next;
        }
        tmp.next = null;//cut tail!!!
      
        current.next = tmp_head.next;
        return head;
    }
}

//twin point! always easier
class Solution {
    public ListNode oddEvenList(ListNode head) {

        if(head==null||head.next==null){
            return head;
        }
        ListNode odd=head;
        ListNode even = head.next;
        ListNode enHead = even;
        while (even != null && even.next != null && odd!=null&&odd.next!=null) {//include the null tail! better loop
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = enHead;

        return head;
    }
}
