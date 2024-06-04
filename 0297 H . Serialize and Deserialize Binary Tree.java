//297. Serialize and Deserialize Binary Tree
//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

// 使用前序遍历（或层序遍历）序列化二叉树时，我们可以通过遍历的顺序来重构树的结构，因为前序遍历的第一个元素总是根节点，而接下来的元素是根节点的左子树和右子树的前序遍历结果。通过这种方式，我们可以递归地构建整棵树。

// 下面是使用前序遍历来重构二叉树的过程：

// 解析根节点：从前序遍历的结果中取出第一个元素，这就是根节点的值。如果没有元素了，说明树为空，返回 null。
// 构建根节点：根据取出的值创建一个新的树节点作为根节点。
// 构建左子树：从序列化的字符串中继续取出元素，这些元素属于根节点的左子树。使用这些元素递归地构建左子树。
// 构建右子树：在左子树构建完成后，继续从序列化的字符串中取出元素，这些元素属于根节点的右子树。使用这些元素递归地构建右子树。
// 返回根节点：当左右子树都构建完成后，返回根节点。
// 这个过程的关键在于，前序遍历的顺序确保了每个节点的值在它的左右子节点之前被处理，因此我们可以递归地构建树的每个部分。

// 下面是一个简单的例子来说明这个过程：

// 假设我们有以下前序遍历的结果：[1, 2, 4, #, #, 5, 3, #, #]

// 第一个元素是 1，这是根节点的值。
// 接下来的元素是 2，这是根节点的左子节点的值。
// 然后是 4，这是左子节点的左子节点的值。
// 接着是 #，表示左子节点的左子节点为空。
// 再接着是 #，表示左子节点的右子节点为空。
// 然后是 5，这是根节点的右子节点的值。
// 接下来是 3，这是右子节点的左子节点的值。
// 然后是 #，表示右子节点的左子节点为空。
// 最后是 #，表示右子节点的右子节点为空。
// 通过这种方式，我们可以从前序遍历的结果中重构出原始的二叉树。

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] nodes = data.split(",");
        return deserialize(new ArrayDeque<>(Arrays.asList(nodes)));
    }

    private TreeNode deserialize(ArrayDeque<String> nodes) {
        String val = nodes.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

//SIMPLER
public class Codec {
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }
    
    // Generate preorder string
    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }
    
    // Use queue to simplify position move
    private TreeNode deserial(Queue<String> q) {
        String val = q.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }

}
