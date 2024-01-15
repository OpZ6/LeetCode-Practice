// 430. Flatten a Multilevel Doubly Linked List
// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
//recursion
class Solution {
    public Node flatten(Node head) {
        Node current = head;
        while (current != null) {
            if (current.child != null) {
                
                flatten (current.child); 
                
                Node nextNode = current.next;
                current.next = current.child;
                current.child.prev = current;
                current.child = null;

                while (current.next != null) {
                    current = current.next;
                }
                current.next = nextNode;
                if (nextNode != null){
                    nextNode.prev = current;                    
                }
                
            }
            current = current.next;
        }
        return head;
    }
}

// Brute force: Recursion with the tail collected
class Solution {

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        
        // Helper function to flatten a node and its children
        flattenNode(head);
        
        return head;
    }

    private Node flattenNode(Node node) {
        Node current = node;
        Node tail = node; // To keep track of the tail of the flattened list
        
        while (current != null) {
            if (current.child != null) {
                Node child = current.child;
                current.child = null;
                
                // Flatten the child list and get the child list's tail
                Node childTail = flattenNode(child);
                
                // Connect the current node to the flattened child
                childTail.next = current.next;
                if (current.next != null) {
                    current.next.prev = childTail;
                }
                current.next = child;
                child.prev = current;
                
                // Update the tail to be the tail of the merged list
                tail = childTail;
            }
            
            // Move to the next node in the original list
            current = current.next;
            if (current != null) {
                tail = current; // Update the tail for non-null current nodes
            }
        }
        
        return tail; // Return the tail of the merged list
    }
}

//Efficient Method : USE STACK
//To optimize the solution, we can use an iterative approach without recursion. Here's the step-by-step explanation:

// Start at the head of the doubly linked list.
// Use a stack to keep track of nodes with potential child lists.
// While traversing the list:
    // If the current node has a child (sub-list):
        // Save the next node in the main list.
        // Connect the current node to the child list.
        // Push the next node onto the stack for later processing.
        // Update the current node to be the child list's last node.
    // If the current node does not have a child and there are nodes in the stack, pop a node from the stack and connect it to the current node.
// Continue this process until you have processed all nodes in the list.

class Solution {

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        
        Node current = head;
        Stack<Node> stack = new Stack<>();
        
        while (current != null) {
            if (current.child != null) {
                Node nextNode = current.next;
                
                // Connect current node to the child list
                current.next = current.child;
                current.child.prev = current;
                current.child = null;
                
                // Push the next node onto the stack for later processing
                if (nextNode != null) {
                    stack.push(nextNode);
                }
            } else if (current.next == null && !stack.isEmpty()) {
                // If there are no more nodes in the current level,
                // pop a node from the stack and connect it to the current node
                Node nextNode = stack.pop();
                current.next = nextNode;
                nextNode.prev = current;
            }
            
            current = current.next;
        }
        
        return head;
    }
}

//Ex.
// Input
// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
// Standard output
// current
// 1
// current
// 2
// push
// 4
// current
// 3
// current
// 7
// push
// 9
// current
// 8
// current
// 11
// pop
// 9
// current
// 12
// current
// 9
// pop
// 4
// current
// 10
// current
// 4
// current
// 5
// current
// 6
// View less
// Output: [1,2,3,7,8,11,12,9,10,4,5,6]
