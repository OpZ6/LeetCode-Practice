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
