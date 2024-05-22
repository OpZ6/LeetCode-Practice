//94. Binary Tree Inorder Traversal
//

//Recursion
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        // 首先遍历左子树
        inorderHelper(node.left, result);
        // 然后访问当前节点
        result.add(node.val);
        // 最后遍历右子树
        inorderHelper(node.right, result);
    }
}

//while
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left; // Traverse to the leftmost node
            }
            current = stack.pop(); // Backtrack to the previous node
            res.add(current.val); // Visit the current node
            current = current.right; // Move to the right subtree
        }

        return res;
    }
}

