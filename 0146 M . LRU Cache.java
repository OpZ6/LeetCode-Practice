//146. LRU Cache
//https://leetcode.com/problems/lru-cache/description/

class LRUCache {
    Map<Integer, Node> check = new HashMap();
    int capacity;
    int size;
    Node head;
    Node tail;

    class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    
        this.head = new Node(-1, -1);
        this.tail = new Node(-2, -2);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        //判断存在
        Node tmp = check.get(key);
        if(tmp != null) {
            delete(tmp);
            addToHead(tmp);
            return tmp.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node node = check.get(key);
        if (node != null) {
            delete(node);
            node.value = value;
            addToHead(node);
        } else {
            node = new Node(key, value);
            if (size >= capacity) {
                Node tail = this.tail.pre;
                delete(tail);
                check.remove(tail.key);
                size--;
            }
            addToHead(node);
            check.put(key, node);
            size++;
        }
    }


    private void delete(Node target) {
        target.pre.next = target.next;
        target.next.pre = target.pre;
    }

    private void addToHead(Node target) {
        target.next = head.next;
        head.next.pre = target;
        target.pre = head;
        head.next = target;
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
