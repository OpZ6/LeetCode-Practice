//160. Intersection of Two Linked Lists
//https://leetcode.com/problems/intersection-of-two-linked-lists/description/

//hash
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> visited_nodes = new HashSet<>();
        ListNode current_node = headA;
        while(current_node != null){
            visited_nodes.add(current_node);
            current_node = current_node.next;
        }
        current_node = headB;
        while(current_node != null){
            if (visited_nodes.contains(current_node)) {
                return current_node;
            }
            current_node = current_node.next;
        }
        return null;
    }
}

//all two pointer
//best algorithm
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;
    
    ListNode a = headA;
    ListNode b = headB;
    
    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    
    return a;
}

//easier understand way
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int ac = 0;
        int bc = 0;
        ListNode a = headA;
        ListNode b = headB;
        while(a != null){
            ac++;
            a = a.next;
        }
        while(b != null){
            bc++;
            b = b.next;
        }
        while(ac > bc){
            ac--;
            headA = headA.next;
        }
        while(bc > ac){
            bc--;
            headB = headB.next;
        }
        
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}
