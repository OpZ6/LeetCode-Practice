//112. Path Sum
//

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
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if(root == null) {
            return false;
        }
        return hasPathSumHelper(root, root.val, targetSum);

    }

    private boolean hasPathSumHelper(TreeNode root, int sum, int targetSum) {
        boolean res1 = false,res2 = false;//IMPORTANT!!
        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }
        if (root.left != null) {
            res1 = hasPathSumHelper(root.left, sum + root.left.val, targetSum);
        }
        if (root.right != null) {
            res2 = hasPathSumHelper(root.right, sum+ root.right.val, targetSum);
        }
 
        return (res1 || res2);
    }
}

//simpler
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasPathSumHelper(root, 0, targetSum);
    }

    private boolean hasPathSumHelper(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return false;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }
        boolean left = hasPathSumHelper(root.left, sum, targetSum);
        boolean right = hasPathSumHelper(root.right, sum, targetSum);
        return left || right;
    }
}
