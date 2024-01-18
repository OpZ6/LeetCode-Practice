// 706. Design HashMap
// https://leetcode.com/problems/design-hashmap/description/

// https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1142/

// My approach
class MyHashMap {
    private int range;
    private List<List<int[]>> buckets;

    public MyHashMap() {
        range = 769;
        buckets = new ArrayList<>(range);
        for (int i = 0; i < range; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int _hash(int key) {
        return (key % this.range);
    }
    
    public void put(int key, int value) {
        int index = this._hash(key);
        List<int[]> buckets = this.buckets.get(index);
        for (int[] data : buckets) {
            if(data[0] == key) {
                data[1] = value;
                return;
            }
        }
        int dataAdd[] = {key, value};
        buckets.add(dataAdd);
        return;
    }
    
    public int get(int key) {
        int index = this._hash(key);
        List<int[]> buckets = this.buckets.get(index);
        for (int[] data : buckets) {
            if(data[0] == key) {
                return data[1];
            }
        }
        return -1;       
    }

    public void remove(int key) { //updated!!!
        int index = this._hash(key);
        List<int[]> buckets = this.buckets.get(index);
        for (int[] data : buckets) {
            if(data[0] == key) {
                buckets.remove(data);
                return;
            }
        }
        return;      
    }
    
    // This actually works also, but for no necessity！！！
  
    // public void remove(int key) {
    //     int index = this._hash(key);
    //     List<int[]> buckets = this.buckets.get(index);
        
    //     // for (int[] data : buckets) {
    //     //     if(data[0] == key) {
    //     //         buckets.remove(data);
    //     //     }
    //     // }
    //     // ERROR: since The issue in your remove method is that you are trying to remove elements from the buckets list while iterating over it using a for-each loop. This can lead to a java.util.ConcurrentModificationException as previously mentioned because you're modifying the list while iterating over it. To safely remove elements from a list while iterating, you should use an iterator and the iterator's remove method. Here's the corrected remove method.

    //     Iterator<int[]> iterator = buckets.iterator();
    //     while (iterator.hasNext()) {
    //         int[] data = iterator.next();
    //         if (data[0] == key) {
    //             iterator.remove(); // Use iterator's remove method to remove the element
    //             return;
    //         }
    //     }
    // }
}

//Java Code w/ Hash:
class ListNode {
    int key, val;
    ListNode next;

    public ListNode(int key, int val, ListNode next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }
}

class MyHashMap {
    static final int size = 19997;
    static final int mult = 12582917;
    ListNode[] data;

    public MyHashMap() {
        this.data = new ListNode[size];
    }

    private int hash(int key) {
        return (int) ((long) key * mult % size);
    }

    public void put(int key, int val) {
        remove(key);
        int h = hash(key);
        ListNode node = new ListNode(key, val, data[h]);
        data[h] = node;
    }

    public int get(int key) {
        int h = hash(key);
        ListNode node = data[h];
        for (; node != null; node = node.next)
            if (node.key == key)
                return node.val;
        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        ListNode node = data[h];
        if (node == null)
            return;
        if (node.key == key)
            data[h] = node.next;
        else
            for (; node.next != null; node = node.next)
                if (node.next.key == key) {
                    node.next = node.next.next;
                    return;
                }
    }
}

//To define pair by ourselves.
class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Bucket {
    private List<Pair<Integer, Integer>> bucket;

    public Bucket() {
        this.bucket = new LinkedList<Pair<Integer, Integer>>();
    }

    public Integer get(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key))
                return pair.second;
        }
        return -1;
    }

    public void update(Integer key, Integer value) {
        boolean found = false;
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                pair.second = value;
                found = true;
            }
        }
        if (!found)
            this.bucket.add(new Pair<Integer, Integer>(key, value));
    }

    public void remove(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                this.bucket.remove(pair);
                break;
            }
        }
    }
}

class MyHashMap {
    private int key_space;
    private List<Bucket> hash_table;

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.key_space = 2069;
        this.hash_table = new ArrayList<Bucket>();
        for (int i = 0; i < this.key_space; ++i) {
            this.hash_table.add(new Bucket());
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).update(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping
     * for the key
     */
    public int get(int key) {
        int hash_key = key % this.key_space;
        return this.hash_table.get(hash_key).get(key);
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).remove(key);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap();
 * obj.put(key,value); int param_2 = obj.get(key); obj.remove(key);
 */
