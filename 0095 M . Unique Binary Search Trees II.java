//95. Unique Binary Search Trees II
//https://leetcode.com/problems/unique-binary-search-trees-ii/


//BST!!!!
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
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int start, int end) {
        List<TreeNode> subtrees = new ArrayList<>();
        if (start > end) {
            subtrees.add(null);
            return subtrees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = generateSubtrees(start, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, end);

            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    subtrees.add(root);
                }
            }
        }
        return subtrees;
    }
}
