// 708. Insert into a Sorted Circular Linked List
// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/description/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node addNode = new Node(insertVal,null);
        addNode.next = addNode;
        if (head == null) {
            return addNode;
        // } else if (head.next == head) { //NO NEED
        //     head.next = addNode;
        //     addNode.next = head;
        //     return head;
        } else {
            Node current = head;
            int count = 0;
            while (count < 2) {
                if ( (current.val <= insertVal && current.next.val >= insertVal) 
                    || (current.val > current.next.val && (insertVal > current.val || insertVal < current.next.val) ) ){
                    addNode.next = current.next;
                    current.next = addNode;
                    return head;
                } else {
                    current = current.next;
                }   
                if (current == head.next) {
                    count++;
                }
            }
          
            addNode.next = current.next; // the case like [3,3,3]!!!
            current.next = addNode;
        }
        return head;
    }
}

//others' code seems easier
public Node insert(Node head, int val) {
      Node node = new Node(val);
      
      if(head == null) {
          node.next = node;
          return node;
      }

      Node pre = head;
      Node next = head.next;

      while(next != head) {

          if(( val < next.val && pre.val <= val)           // e.g [1 -> 3] with val=1 or 2      (same as pre OR between pre and next)
              || (pre.val <= val && pre.val > next.val )   // e.g [3 -> 1] with val=3 or higher (same as pre OR higher than pre)
              || (pre.val > next.val && val <= next.val )){// e.g [3 -> 1] with val=1 or lower  (same as next OR lower than next)
              break;
          }
          pre = next;
          next = next.next;
      }

      pre.next = node;
      node.next = next;
      return head;
  }

