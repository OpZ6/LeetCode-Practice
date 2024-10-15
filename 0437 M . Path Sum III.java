//437. Path Sum III
//https://leetcode.com/problems/path-sum-iii/description/

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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);  // 初始化前缀和，表示路径和为 0 的情况出现了一次
        return dfs(root, prefix, 0, targetSum);  // 开始 DFS 搜索
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;  // 递归终止条件：如果节点为空，返回 0 表示没有路径
        }

        int ret = 0;  // 存储当前节点能够形成的路径数
        curr += root.val;  // 更新当前路径的前缀和

        // 计算从某个前缀和到当前节点的路径和是否等于 targetSum
        // 如果 curr - targetSum 在 prefix 中存在，说明存在从某个之前的节点到当前节点的路径和为 targetSum
        ret = prefix.getOrDefault(curr - targetSum, 0);

        // 更新当前路径的前缀和到哈希表中
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        // 递归遍历左右子树，并累加路径数
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);

        // 回溯：移除当前节点的前缀和，防止影响其他路径
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;  // 返回从当前节点开始的路径数
    }
}
