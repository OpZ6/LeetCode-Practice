//144. Binary Tree Preorder Traversal
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

//recursion
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        
        res.add(root.val); // 访问根节点
        if (root.left != null) {
            res.addAll(preorderTraversal(root.left)); // 遍历左子树
        }
        if (root.right != null) {
            res.addAll(preorderTraversal(root.right)); // 遍历右子树
        }

        return res;
    }
}

//stack
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> tmp = new Stack<>();
        List<Integer> res = new ArrayList<>();

        tmp.add(root);

        while (!tmp.isEmpty()) {
            TreeNode t = tmp.pop();
            if (t != null) {
                res.add(t.val);
                if (t.left != null) {
                    tmp.add(t.left);
                }
                if (t.right != null) {
                    tmp.add(t.right);
                }
            }
        }

        return res;
    }
}

