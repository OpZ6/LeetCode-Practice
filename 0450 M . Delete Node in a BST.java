//450. Delete Node in a BST
//https://leetcode.com/problems/delete-node-in-a-bst/description/


//change node but not to use null
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // Find the node to be deleted.
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.val = minValue(root.right);
            
            // Delete the inorder successor
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private int minValue(TreeNode root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
}


// /** WRONG ANSWER
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */
// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
//         TreeNode target = findNode(root, key);
//         if (target == null) {
//             return null;
//         }
//         if (target.left == null && target.right == null) {
//             target = null;
//         } else if (target.left != null && target.right == null) {
//             target = target.left;
//         } else {
//             TreeNode tmp = target.right;
//             while (tmp.left != null) {
//                 tmp = tmp.left;
//             }
//             target.val = tmp.val;
//             tmp = null;
//         }
//         return root;
//     }

//     private TreeNode findNode(TreeNode root, int key) {
//         TreeNode cur = root;
//         while (cur != null && cur.val != key) {
//             if (key < cur.val) {
//                 cur = cur.left;
//             } else {
//                 cur = cur.right;
//             }
//         }
//         return cur;
//     }
// }
