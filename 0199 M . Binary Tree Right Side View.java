//199. Binary Tree Right Side View
//https://leetcode.com/problems/binary-tree-right-side-view/description/

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
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            TreeNode tmp = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                tmp = queue.pollFirst();
                if (tmp.left != null) {
                    queue.offerLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offerLast(tmp.right);
                }
            }
            result.add(tmp.val);
        }

        return result;
    }
}

//better BFS
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // 第一次进入的节点就是该层最右边的节点
                if (i == 0) {
                    result.add(node.val);
                }
                
                // 优先将右节点入队，再将左节点入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        
        return result;
    }
}

//DFS
class Solution {
    private List<Integer> ans;

    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        
        // 如果该层还没有节点，直接添加当前节点（优先添加右侧节点）
        if (ans.size() == depth) {
            ans.add(node.val);
        }

        // 优先遍历右子树
        dfs(node.right, depth + 1);
        // 再遍历左子树
        dfs(node.left, depth + 1);
    }
}

