//707. Design Linked List
//https://leetcode.com/problems/design-linked-list/description/

class MyLinkedList {

  Node head;
	int length;

    public class Node {//create by ourselves
        int val;
        Node next;
        Node(int x){
            this.val = x;
        }        
    }

    public MyLinkedList() {//it's the default initializer, we need a new class of NODE.
        this.head = null;
        this.length = 0;
    }
    
    public int get(int index) {
      
        //important!
        if(index >= length){
            return -1;
        }

        Node tmp = head;
        for(int i = 0; i < index; i++){
            tmp = tmp.next;
        }
      
        // if (tmp == null) {
        //     return -1; // Only exist when index = length, which is considered in the fisrt if
        // }
      
        return tmp.val;
    }
    
    public void addAtHead(int val) {
        Node tmp = new Node(val);
        tmp.next = head;
        head = tmp;
        length++;
    }
    
    public void addAtTail(int val) {
      
        //important!
        if(head == null){
            addAtHead(val);
        }
        
        else{
            Node tmp = head;
            for(int i = 1; i < length; i++){
                tmp = tmp.next;
            }
            Node tail = new Node(val);
            tmp.next = tail;
            length++;            
        }
    }
    
    public void addAtIndex(int index, int val) {
        //important! consider extreme case and also use extreme example to test.
        if(index > length){
            return;
        }
        if(index == 0){
            addAtHead(val);
        }
          
        else{
            Node tmp = head;
            for(int i = 1; i < index; i++){
                tmp = tmp.next;
            }
            Node mid = new Node(val);
            Node next = tmp.next;
            tmp.next = mid;
            mid.next = next;
            length++;
        }
    }
    
    public void deleteAtIndex(int index) {
        if(index >= length){
            return;
        }
        if(index == 0){
            head = head.next;//do not use null!!!
            length--;
        }else{
            Node tmp = head;
            for(int i = 1; i < index; i++){
                tmp = tmp.next;
            }
            tmp.next = tmp.next.next;
            length--;         
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

//double linked list
// Definition for doubly-linked list.
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int x) {val = x;}
}
class MyLinkedList {
    private DoublyListNode head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
    }
}
/** Helper function to return the index-th node in the linked list. */
private DoublyListNode getNode(int index) {
    DoublyListNode cur = head;
    for (int i = 0; i < index && cur != null; ++i) {
        cur = cur.next;
    }
    return cur;
}
/** Helper function to return the last node in the linked list. */
private DoublyListNode getTail() {
    DoublyListNode cur = head;
    while (cur != null && cur.next != null) {
        cur = cur.next;
    }
    return cur;
}
/** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
public int get(int index) {
    DoublyListNode cur = getNode(index);
    return cur == null ? -1 : cur.val;
}
/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
public void addAtHead(int val) {
    DoublyListNode cur = new DoublyListNode(val);
    cur.next = head;
    if (head != null) {
        head.prev = cur;
    }
    head = cur;
    return;
}

/** Append a node of value val to the last element of the linked list. */
public void addAtTail(int val) {
    if (head == null) {
        addAtHead(val);
        return;
    }
    DoublyListNode prev = getTail();
    DoublyListNode cur = new DoublyListNode(val);
    prev.next = cur;
    cur.prev = prev;
}

/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
public void addAtIndex(int index, int val) {
    if (index == 0) {
        addAtHead(val);
        return;
    }
    DoublyListNode prev = getNode(index - 1);
    if (prev == null) {
        return;
    }
    DoublyListNode cur = new DoublyListNode(val);
    DoublyListNode next = prev.next;
    cur.prev = prev;
    cur.next = next;
    prev.next = cur;
    if (next != null) {
        next.prev = cur;
    }
}
/** Delete the index-th node in the linked list, if the index is valid. */
public void deleteAtIndex(int index) {
    DoublyListNode cur = getNode(index);
    if (cur == null) {
        return;
    }
    DoublyListNode prev = cur.prev;
    DoublyListNode next = cur.next;
    if (prev != null) {
        prev.next = next;
    } else {
        // modify head when deleting the first node.
        head = next;
    }
    if (next != null) {
        next.prev = prev;
    }
}
