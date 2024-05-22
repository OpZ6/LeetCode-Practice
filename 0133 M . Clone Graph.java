//133. Clone Graph
//https://leetcode.com/problems/clone-graph/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

//DFS
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Map<Node, Node> visited = new HashMap<>();
        return DFS(node, visited);
    }

    private Node DFS(Node node, Map<Node, Node> visited) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node clone = new Node(node.val);
        visited.put(node, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(DFS(neighbor, visited));//directly create the clone while recursion with new node to create
        }
        return clone;
    }
}

//BFS
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        // 使用一个队列进行BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        // 创建一个映射来存储原始节点到克隆节点的映射
        Map<Node, Node> clones = new HashMap<>();
        
        // 克隆第一个节点并存储到映射中
        clones.put(node, new Node(node.val));
        
        // 进行BFS遍历
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node clone = clones.get(cur);
            
            // 遍历当前节点的所有邻居
            for (Node neighbor : cur.neighbors) {
                // 如果邻居还没有被克隆，则克隆并加入队列
                if (!clones.containsKey(neighbor)) {
                    clones.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                // 将邻居的克隆添加到当前节点的克隆的邻居列表中
                clone.neighbors.add(clones.get(neighbor));
            }
        }
        
        // 返回克隆图的入口点
        return clones.get(node);
    }
}

