//705. Design HashSet
//https://leetcode.com/problems/design-hashset/

//https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1142/

//My approach
class MyHashSet {
    private int Range;
    private List<List<Integer>> buckets;

    public MyHashSet() {
        Range = 769;
        buckets = new ArrayList<>(Range);
        for (int i = 0; i < Range; i++) {
            buckets.add(new LinkedList<>());
        }
    }
    
    private int _hash(int key) {
        return (key % this.Range);
    }

    public void add(int key) {
        int index = this._hash(key);
        List<Integer> buckets = this.buckets.get(index);
        if (!buckets.contains(key)) {
            buckets.add(key);
        }
    }
    
    public void remove(int key) {
        int index = this._hash(key);
        List<Integer> buckets = this.buckets.get(index);
        buckets.remove(Integer.valueOf(key));
    }
    
    public boolean contains(int key) {
        int index = this._hash(key);
        List<Integer> bucket = this.buckets.get(index);
        return bucket.contains(key);
    }
}


//Approach 1: LinkedList as Bucket
class MyHashSet {
    private Bucket[] bucketArray;
    private int keyRange;

    /** Initialize your data structure here. */
    public MyHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; ++i)
            this.bucketArray[i] = new Bucket();
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }
}

class Bucket {
    private LinkedList<Integer> container;

    public Bucket() {
        container = new LinkedList<Integer>();
    }

    public void insert(Integer key) {
        int index = this.container.indexOf(key);
        if (index == -1) {
            this.container.addFirst(key);
        }
    }

    public void delete(Integer key) {
        this.container.remove(key);
    }

    public boolean exists(Integer key) {
        int index = this.container.indexOf(key);
        return (index != -1);
    }
}

//Approach 2: Binary Search Tree (BST) as Bucket

class MyHashSet {
  private Bucket[] bucketArray;
  private int keyRange;

  /** Initialize your data structure here. */
  public MyHashSet() {
    this.keyRange = 769;
    this.bucketArray = new Bucket[this.keyRange];
    for (int i = 0; i < this.keyRange; ++i)
      this.bucketArray[i] = new Bucket();
  }

  protected int _hash(int key) {
    return (key % this.keyRange);
  }

  public void add(int key) {
    int bucketIndex = this._hash(key);
    this.bucketArray[bucketIndex].insert(key);
  }

  public void remove(int key) {
    int bucketIndex = this._hash(key);
    this.bucketArray[bucketIndex].delete(key);
  }

  /** Returns true if this set contains the specified element */
  public boolean contains(int key) {
    int bucketIndex = this._hash(key);
    return this.bucketArray[bucketIndex].exists(key);
  }
}


class Bucket {
  private BSTree tree;

  public Bucket() {
    tree = new BSTree();
  }

  public void insert(Integer key) {
    this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
  }

  public void delete(Integer key) {
    this.tree.root = this.tree.deleteNode(this.tree.root, key);
  }

  public boolean exists(Integer key) {
    TreeNode node = this.tree.searchBST(this.tree.root, key);
    return (node != null);
  }
}

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class BSTree {
  TreeNode root = null;

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null || val == root.val)
      return root;

    return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
  }

  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null)
      return new TreeNode(val);

    if (val > root.val)
      // insert into the right subtree
      root.right = insertIntoBST(root.right, val);
    else if (val == root.val)
      // skip the insertion
      return root;
    else
      // insert into the left subtree
      root.left = insertIntoBST(root.left, val);
    return root;
  }

  /*
   * One step right and then always left
   */
  public int successor(TreeNode root) {
    root = root.right;
    while (root.left != null)
      root = root.left;
    return root.val;
  }

  /*
   * One step left and then always right
   */
  public int predecessor(TreeNode root) {
    root = root.left;
    while (root.right != null)
      root = root.right;
    return root.val;
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null)
      return null;

    // delete from the right subtree
    if (key > root.val)
      root.right = deleteNode(root.right, key);
    // delete from the left subtree
    else if (key < root.val)
      root.left = deleteNode(root.left, key);
    // delete the current node
    else {
      // the node is a leaf
      if (root.left == null && root.right == null)
        root = null;
      // the node is not a leaf and has a right child
      else if (root.right != null) {
        root.val = successor(root);
        root.right = deleteNode(root.right, root.val);
      }
      // the node is not a leaf, has no right child, and has a left child
      else {
        root.val = predecessor(root);
        root.left = deleteNode(root.left, root.val);
      }
    }
    return root;
  }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
