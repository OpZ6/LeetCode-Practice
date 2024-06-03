//250. Count Univalue Subtrees
//https://leetcode.com/problems/count-univalue-subtrees/description/

class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (isUnivalTree(root)) {
            count++;
        }
        count += countUnivalSubtrees(root.left);
        count += countUnivalSubtrees(root.right);
        return count;
    }

    private boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null && root.right != null && root.left.val != root.right.val) {
            return false;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}

//BETTER
class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1]; // Use an array to pass the count by reference
        isUnivalSubtree(root, count);
        return count[0];
    }

    private boolean isUnivalSubtree(TreeNode node, int[] count) {
        if (node == null) return true;

        boolean left = isUnivalSubtree(node.left, count);
        boolean right = isUnivalSubtree(node.right, count);

        if (left && right) {
            if (node.left != null && node.val != node.left.val) return false;
            if (node.right != null && node.val != node.right.val) return false;
            count[0]++; // Increment count if current subtree is univalue
            return true;
        }

        return false;
    }
}

//hard?
public class Solution {
    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        helper(root, 0);
        return count;
    }   
    
    private boolean helper(TreeNode root, int val) {
        if (root == null)
            return true;
        if (!helper(root.left, root.val) | !helper(root.right, root.val))
            return false;
        count++;
        return root.val == val;
    }
}
