//106. Construct Binary Tree from Inorder and Postorder Traversal
//


// 这个问题的解法基于一个事实：后序遍历的最后一个元素一定是树的根节点。在中序遍历中，根节点的左侧是左子树，右侧是右子树。通过这些信息，我们可以递归地构建整棵树。

// 下面是详细的步骤解释：

// 确定根节点：后序遍历的最后一个元素是当前子树的根节点。在例子中，后序遍历[9,15,7,20,3]的最后一个元素是3，所以3是整棵树的根节点。
// 分割中序遍历数组：在中序遍历[9,3,15,20,7]中找到根节点3的位置，根节点左侧的元素构成左子树的中序遍历[9]，右侧的元素构成右子树的中序遍历[15,20,7]。
// 分割后序遍历数组：根据中序遍历的分割，后序遍历也可以分割为左子树的后序遍历[9]和右子树的后序遍历[15,7,20]。注意，后序遍历的分割不需要再次查找根节点，因为我们已经知道根节点是最后一个元素。
// 递归构建子树：对左子树和右子树重复上述步骤，直到子树为空（即后序遍历数组为空）。
// 现在，让我们用这个思路来构建整棵树：

// Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
// Output: [3,9,20,null,null,15,7]

// 根节点是3。
// 左子树的中序遍历是[9]，后序遍历是[9]。左子树只有一个节点，即9。
// 右子树的中序遍历是[15,20,7]，后序遍历是[15,7,20]。右子树的根节点是20，15是左子节点，7是右子节点。
// 按照这个方法，我们可以递归地构建出整棵树。这个算法的关键在于利用中序遍历和后序遍历的特点来确定子树的边界，并通过递归将大问题分解为小问题。

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) {
            return null;
        }
        
        // 后序遍历的最后一个元素是根节点
        TreeNode root = new TreeNode(postorder[pEnd]);
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
        root.left = helper(inorder, iStart, index - 1, postorder, pStart, pStart + leftSize - 1);
        root.right = helper(inorder, index + 1, iEnd, postorder, pStart + leftSize, pEnd - 1);
        
        return root;
    }
}
