//141. Linked List Cycle
//https://leetcode.com/problems/linked-list-cycle/description/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {//two pointer
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while(count < 10000) {
            ListNode slow_next = slow.next;
            ListNode fast_next = fast.next;
            if(slow_next == null || fast_next == null || fast_next.next == null) {
                return false;
            }else {
                slow = slow_next;
                fast = fast_next.next;
                if(slow == fast) {
                    return true;
                }                
            }
        }
        return false;
    }
}

//much cleaner!!!
public class Solution {
    public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) 
            return true;
    }
        return false;
    }
}

//hash table
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> visited_nodes = new HashSet<>();
        ListNode current_node = head;
        while (current_node != null) {
            if (visited_nodes.contains(current_node)) {
                return true;
            }
            visited_nodes.add(current_node);
            current_node = current_node.next;
        }
        return false;
    }
}
