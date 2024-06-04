//105. Construct Binary Tree from Preorder and Inorder Traversal
//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int iStart, int iEnd, int[] preorder, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) {
            return null;
        }
        // 前序遍历的No1元素是根节点
        TreeNode root = new TreeNode(preorder[pStart]);
        int index = 0;
        
        // 在中序遍历中找到根节点的位置
        for (int i = iStart; i <= iEnd; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        
        // 计算左子树的大小
        int leftSize = index - iStart;
        
        // 递归构建左子树和右子树
        root.left = helper(inorder, iStart, index - 1, preorder, pStart + 1, pStart + leftSize);
        root.right = helper(inorder, index + 1, iEnd, preorder, pStart + 1 + leftSize, pEnd);
        
        return root;
    }
}
