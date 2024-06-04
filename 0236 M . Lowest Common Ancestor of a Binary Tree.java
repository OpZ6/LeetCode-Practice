//236. Lowest Common Ancestor of a Binary Tree
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//recursion
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果根节点为空，或者根节点是 p 或 q 中的一个，直接返回根节点
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // 递归遍历左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归遍历右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 如果左子树和右子树都返回了节点，说明 p 和 q 分别位于当前节点的两侧，返回当前节点
        if (left != null && right != null) {
            return root;
        }
        
        // 如果只有左子树或右子树返回了节点，返回那个子树的节点
        return left != null ? left : right;
    }
}
