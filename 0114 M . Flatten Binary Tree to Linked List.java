// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private ArrayList<TreeNode> store;
    public void flatten(TreeNode root) {
        store = new ArrayList<>();
        preOrder(root);
        // 遍历 store 列表，将每个节点的左子树设置为 null，右子树设置为链表的下一个节点
        TreeNode tmp = root;
        for (int i = 1; i < store.size(); i++) {  // 从 store[1] 开始，因为根节点已经是 root
            tmp.left = null;  // 左指针置为 null
            tmp.right = store.get(i);  // 将右指针指向下一个节点
            tmp = tmp.right;  // 更新 tmp 为下一个节点
        }
    }

    public void preOrder(TreeNode root) {
        if (root == null) return;
        store.add(root);
        if (root.left != null) preOrder(root.left);
        if (root.right != null) preOrder(root.right);
    }
}

//O1
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;

        // 递归展开左子树和右子树
        flatten(root.left);
        flatten(root.right);

        // 保存当前的右子树
        TreeNode tempRight = root.right;

        // 把左子树放到右子树的位置
        root.right = root.left;
        root.left = null; // 左子树置为 null

        // 找到当前右子树（原左子树）的末端
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        // 将保存的右子树接到当前右子树的末端
        current.right = tempRight;
    }
}
