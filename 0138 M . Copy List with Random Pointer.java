// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/description/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

//three loop: Interweaving Nodes Method
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }

        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = new Node(current.val);
            current.next.next = next;
            current = next;
        }

        current = head;
        while (current != null) {
            Node next = current.next.next;
            Node random = current.random;
            current.next.random = (random != null) ? current.random.next : null;
            current = next;
        }

        current = head;
        Node newHead = head.next;
        while (current.next.next != null) {
            Node newNode = current.next;
            Node nextOld = current.next.next;
            newNode.next = newNode.next.next;
            current.next = nextOld;
            current = nextOld;
        }
        current.next = null;
           
        return newHead;
    }
}

//others' code
public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        while (curr != null) {
            Node new_node = new Node(curr.val, curr.next);//
            curr.next = new_node;
            curr = new_node.next;
        }
        
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next != null ? curr.next.next : null;
        }
        
        Node old_head = head;
        Node new_head = head.next;
        Node curr_old = old_head;
        Node curr_new = new_head;
        
        while (curr_old != null) {
            curr_old.next = curr_old.next.next;
            curr_new.next = curr_new.next != null ? curr_new.next.next : null;
            curr_old = curr_old.next;
            curr_new = curr_new.next;
        }
        
        return new_head;
    }
}

//hash map
public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        HashMap<Node, Node> oldToNew = new HashMap<>();//key point
        
        Node curr = head;
        while (curr != null) {
            oldToNew.put(curr, new Node(curr.val));//keep the mapping in order to create a new one
            curr = curr.next;
        }
        
        curr = head;
        while (curr != null) {
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            curr = curr.next;
        }
        
        return oldToNew.get(head);
    }
}
