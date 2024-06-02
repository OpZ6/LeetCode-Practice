//270. Closest Binary Search Tree Value
//https://leetcode.com/problems/closest-binary-search-tree-value/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(res - target)) {
                res = root.val; // Update the closest value if the current node is closer to the target
            } else if (Math.abs(root.val - target) == Math.abs(res - target)) {
                res = Math.min(root.val, res);
            }

            // Move to the left or right depending on the comparison with the target
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }

          //root = target < root.val ? root.left : root.right; //better
        }
        return res;
    }
}
