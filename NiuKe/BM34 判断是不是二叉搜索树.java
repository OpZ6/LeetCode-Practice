// https://www.nowcoder.com/practice/a69242b39baf45dea217815c7dedb52b?tpId=295&tqId=2288088&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
// 描述
// 给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。

// 二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点且右子树上的所有节点均大于当前节点。

// 例：

// 图1

// 图2

// 数据范围：节点数量满足 
// 1
// ≤
// n
// ≤
// 1
// 0
// 4
 
// 1≤n≤10 
// 4
//    ，节点上的值满足 
// −
// 2
// 31
// ≤
// v
// a
// l
// ≤
// 2
// 31
// −
// 1
 
// −2 
// 31
//  ≤val≤2 
// 31
//  −1 
// 示例1
// 输入：
// {1,2,3}
// 复制
// 返回值：
// false
// 复制
// 说明：
// 如题面图1 
// 示例2
// 输入：
// {2,1,3}
// 复制
// 返回值：
// true
// 复制
// 说明：
// 如题面图2 

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
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;  // 空树也是有效的二叉搜索树
        }

        int val = node.val;

        // 检查当前节点的值是否在上下限范围内
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        // 递归检查左子树和右子树
        if (!isValidBSTHelper(node.right, val, upper)) {
            return false;
        }
        if (!isValidBSTHelper(node.left, lower, val)) {
            return false;
        }

        return true;
    }
}
