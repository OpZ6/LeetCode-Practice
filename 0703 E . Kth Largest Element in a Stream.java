//703. Kth Largest Element in a Stream
//https://leetcode.com/problems/kth-largest-element-in-a-stream/description/


class KthLargest {
    // insert a node into the BST
    private Node insertNode(Node root, int num) {
        if (root == null) {
            return new Node(num, 1);
        }
        if (root.val < num) {
            root.right = insertNode(root.right, num);
        } else {
            root.left = insertNode(root.left, num);
        }
        root.cnt++;
        return root;
    }

    private int searchKth(Node root, int k) {
        // m = the size of right subtree
        int m = root.right != null ? root.right.cnt : 0;
        // root is the m+1 largest node in the BST
        if (k == m + 1) {
            return root.val;
        }
        if (k <= m) {
            // find kth largest in the right subtree
            return searchKth(root.right, k);
        } else {
            // find (k-m-1)th largest in the left subtree
            return searchKth(root.left, k - m - 1);
        }
    } 
    
    private Node root;
    private int m_k;

    public KthLargest(int k, int[] nums) {
        root = null;
        for (int i = 0; i < nums.length; ++i) {
            root = insertNode(root, nums[i]);
        }
        m_k = k;
    }
    
    public int add(int val) {
        root = insertNode(root, val);
        return searchKth(root, m_k);
    }
}

class Node {    // the structure for the tree node
    Node left;
    Node right;
    int val;
    int cnt;    // the size of the subtree rooted at the node
    public Node (int v, int c) {
        left = null;
        right = null;
        val = v;
        cnt = c;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

//eaiser

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k);

        // Add all elements from nums to the heap
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);
        if (minHeap.size()>k)minHeap.poll();     
        return minHeap.peek();
    }
}
