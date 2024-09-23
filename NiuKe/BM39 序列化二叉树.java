// https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=295&tqId=23455&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
// 描述
// 请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。

// 二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）

// 二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

// 例如，可以根据层序遍历的方案序列化，如下图:

// 层序序列化(即用函数Serialize转化)如上的二叉树转为"{1,2,3,#,#,6,7}"，再能够调用反序列化(Deserialize)将"{1,2,3,#,#,6,7}"构造成如上的二叉树。

// 当然你也可以根据满二叉树结点位置的标号规律来序列化，还可以根据先序遍历和中序遍历的结果来序列化。不对序列化之后的字符串进行约束，所以欢迎各种奇思妙想。

// 数据范围：节点数 
// n
// ≤
// 100
// n≤100，树上每个节点的值满足 
// 0
// ≤
// v
// a
// l
// ≤
// 150
// 0≤val≤150
// 要求：序列化和反序列化都是空间复杂度 
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
// {1,2,3,#,#,6,7}
// 复制
// 返回值：
// {1,2,3,#,#,6,7}
// 复制
// 说明：
// 如题面图   
// 示例2
// 输入：
// {8,6,10,5,7,9,11}
// 复制
// 返回值：
// {8,6,10,5,7,9,11}

import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
*/

public class Solution {

    // 层次遍历序列化二叉树
    public String Serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

        StringBuilder result = new StringBuilder();
        result.append("{");

        // 使用队列进行层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.append("#,");
            }
        }

        // 删除最后的逗号
        result.deleteCharAt(result.length() - 1);
        result.append("}");

        return result.toString();
    }

    // 反序列化字符串，重建二叉树
    public TreeNode Deserialize(String data) {
        if (data.equals("{}")) {
            return null;
        }

        // 去掉大括号并按逗号分割
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!nodes[index].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.left);
            }
            index++;

            if (!nodes[index].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(node.right);
            }
            index++;
        }

        return root;
    }
}

//两种排序方法重构
import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    // Serialize a binary tree to a string using level-order traversal
    String Serialize(TreeNode root) {
        if (root == null) return "#"; // Represent an empty tree

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // Remove trailing commas and null markers
        int i = sb.length() - 1;
        while (i >= 0 && (sb.charAt(i) == ',' || sb.charAt(i) == '#')) {
            i--;
        }
        sb.setLength(i + 1);

        return sb.toString();
    }

    // Deserialize a string back to a binary tree using level-order traversal
    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0 || str.equals("#")) return null;

        String[] vals = str.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;

        // Create the root node
        TreeNode root = new TreeNode(Integer.parseInt(vals[index++]));
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Left child
            if (index < vals.length) {
                String leftVal = vals[index++];
                if (!leftVal.equals("#")) {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(leftVal));
                    node.left = leftNode;
                    queue.offer(leftNode);
                } else {
                    node.left = null;
                }
            }

            // Right child
            if (index < vals.length) {
                String rightVal = vals[index++];
                if (!rightVal.equals("#")) {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(rightVal));
                    node.right = rightNode;
                    queue.offer(rightNode);
                } else {
                    node.right = null;
                }
            }
        }

        return root;
    }
}
