//234. Palindrome Linked List
//Easy
//https://leetcode.com/problems/palindrome-linked-list/description

class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head, tmp = null, pre = null;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        pre = slow;
        slow = slow.next;
        pre.next = null;
        while(slow != null){
            tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }

        slow = pre;
        fast = head;
        while(slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        
        return true;
    }
}

//listNode need parctice, especially for singly linked list!!!
