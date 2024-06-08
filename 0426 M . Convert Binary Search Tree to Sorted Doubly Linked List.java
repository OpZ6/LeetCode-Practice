//426. Convert Binary Search Tree to Sorted Doubly Linked List
//https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

// /*
// // Definition for a Node.
// class Node {
//     public int val;
//     public Node left;
//     public Node right;

//     public Node() {}

//     public Node(int _val) {
//         val = _val;
//     }

//     public Node(int _val,Node _left,Node _right) {
//         val = _val;
//         left = _left;
//         right = _right;
//     }
// };
// */

//Stack
class Solution {
    public Node treeToDoublyList(Node root) {

        if(root == null) return null;
        Stack<Node> stack = new Stack<>();

        Node current = root;

        Node dummy = new Node(0); // 创建一个哑结点作为双向链表的头部
        Node pre = dummy; // prev用于跟踪双向链表中的前一个节点

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left; // Traverse to the leftmost node
            }
            current = stack.pop(); // Backtrack to the previous node

            pre.right = current;
            current.left = pre;
            pre = current;
            // System.out.print(current.val);

            current = current.right; // Move to the right subtree
        }

        dummy.right.left = pre;
        pre.right = dummy.right;
        return dummy.right;
    }
}

//recursion

class Solution {
    private Node first = null;
    private Node last = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        
        convert(root);
        
        // Close the circular doubly linked list
        last.right = first;
        first.left = last;
        
        return first;
    }
    
    private void convert(Node node) {
        if (node != null) {
            // Convert the left subtree
            convert(node.left);
            
            // Handle the current node
            if (last != null) {
                // Link the previous node (last) with the current node
                last.right = node;
                node.left = last;
            } else {
                // If last is null, it means we are at the start of the list
                first = node;
            }
            last = node; // Update the last node
            
            // Convert the right subtree
            convert(node.right);
        }
    }
}
