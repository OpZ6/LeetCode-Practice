// https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=295&tqId=23282&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
// 描述
// 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
// 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。


// 提示:
// 1.vin.length == pre.length
// 2.pre 和 vin 均无重复元素
// 3.vin出现的元素均出现在 pre里
// 4.只需要返回根结点，系统会自动输出整颗树做答案对比
// 数据范围：
// n
// ≤
// 2000
// n≤2000，节点的值 
// −
// 10000
// ≤
// v
// a
// l
// ≤
// 10000
// −10000≤val≤10000
// 要求：空间复杂度 
// O
// (
// n
// )
// O(n)，时间复杂度 
// O
// (
// n
// )
// O(n)
// 示例1
// 输入：
// [1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
// 复制
// 返回值：
// {1,2,3,4,#,5,6,#,7,#,#,8}
// 复制
// 说明：
// 返回根节点，系统会输出整颗二叉树对比结果，重建结果如题面图示    
// 示例2
// 输入：
// [1],[1]
// 复制
// 返回值：
// {1}
// 复制
// 示例3
// 输入：
// [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
// 复制
// 返回值：
// {1,2,5,3,4,6,7}
import java.util.*;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    // 使用一个哈希表来存储中序遍历序列中每个值的位置，以便快速查找
    private HashMap<Integer, Integer> inOrderMap;

    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        // 初始化哈希表
        inOrderMap = new HashMap<>();
        for (int i = 0; i < vinOrder.length; i++) {
            inOrderMap.put(vinOrder[i], i);
        }
        // 调用递归方法构建树，初始的前序遍历索引范围是[0, preOrder.length-1]，中序遍历索引范围是[0, vinOrder.length-1]
        return buildTree(preOrder, 0, preOrder.length - 1, 0, vinOrder.length - 1);
    }

    private TreeNode buildTree(int[] preOrder, int preStart, int preEnd,
                               int inStart, int inEnd) {
        // 递归结束条件：如果前序遍历的范围已经没有元素了，返回null
        if (preStart > preEnd) {
            return null;
        }

        // 从前序遍历的第一个元素获取当前子树的根节点
        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 在中序遍历中找到根节点的位置
        int rootIndexInOrder = inOrderMap.get(rootVal);

        // 计算左子树的节点数量
        int leftTreeSize = rootIndexInOrder - inStart;

        // 递归构建左子树和右子树
        root.left = buildTree(preOrder, preStart + 1, preStart + leftTreeSize, inStart,
                              rootIndexInOrder - 1);
        root.right = buildTree(preOrder, preStart + leftTreeSize + 1, preEnd,
                               rootIndexInOrder + 1, inEnd);

        return root;
    }
}
