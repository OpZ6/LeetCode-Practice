//230. Kth Smallest Element in a BST
//https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

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
    ArrayList<Integer> store = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root);
        return store.get(k - 1);
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        
        // 遍历左子树
        inOrder(root.left);
        // 访问根节点
        store.add(root.val);
        // 遍历右子树
        inOrder(root.right);
    }
}

//使用 count 来记录当前已经访问的节点数，当 count 达到 k 时，直接将当前节点的值赋给 result 并返回。
//不再存储所有节点的值，只要找到了第 k 个节点，立即返回，减少了不必要的递归和空间使用。
class Solution {
    private int count = 0;   // 记录已经访问的节点数量
    private int result = 0;  // 存储第 k 个最小值

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        // 递归遍历左子树
        inOrder(root.left, k);

        // 访问根节点
        count++;
        if (count == k) {
            result = root.val;
            return;  // 找到结果后直接返回，避免继续遍历
        }

        // 递归遍历右子树
        inOrder(root.right, k);
    }
}

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int leftNodes = countNodes(root.left);
        if(leftNodes < k - 1){  //答案存在右子树中
            return kthSmallest(root.right,k - leftNodes - 1);
        }else if(leftNodes == k - 1){
            return root.val;
        }else{
            return kthSmallest(root.left,k);
        }
    }
    //左神递归套路分析左右子树返回信息只需要节点数，因此无需额外定义数据结构
    public int countNodes(TreeNode root){
       //base case返回0
        if(root == null) return 0;
       //递归处理左右子树并接收返回值
        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
       //判断分析本层递归返回值的具体值
        return leftNodes + rightNodes + 1;
    }
}
