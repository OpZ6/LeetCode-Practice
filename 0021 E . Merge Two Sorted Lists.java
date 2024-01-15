// 21. Merge Two Sorted Lists
// https://leetcode.com/problems/merge-two-sorted-lists/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // if (list1 == null) {
        //     return list2;
        // } else if (list2 == null) {
        //     return list1;
        // } else {
        ListNode res = new ListNode(0);
        ListNode tmp = res;
        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                res.next = list1;
                list1 = list1.next;
                res = res.next;
            } else {
                res.next = list2;
                list2 = list2.next;
                res = res.next;
            }
        }
        if (list1 == null) {
            res.next = list2;
        } else {
            res.next = list1;
        }
        return tmp.next;
        //}
    }
}

//recursion
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
        if (list1 == null)
            return list2;
        return list1;
    }
}

