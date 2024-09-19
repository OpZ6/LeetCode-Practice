//https://www.nowcoder.com/practice/bd828af269cd493c86cc915389b02b9f?tpId=354&tqId=10595837&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E9%259D%25A2%25E8%25AF%2595%26topicId%3D354

// NB13 牛的品种排序IV
// 题目
// 题解(34)
// 讨论(8)
// 排行
// 较难  通过率：13.16%  时间限制：5秒  空间限制：256M
// 描述
// 在一个牧场中，有n头牛，牛的品种分为黑牛和白牛，用0和1分别表示。现在需要对牛群进行排序，使得相同品种的牛相邻，并按照黑牛和白牛的顺序排列。这些牛是按照链表的形式存储的。

// 请你在不使用库内置的sort函数的情况下解决这个问题。

// 示例1
// 输入：
// {1,0,1,0,1,0}
// 复制
// 返回值：
// {0,0,0,1,1,1}
// 复制
// 示例2
// 输入：
// {1,0,0}
// 复制
// 返回值：
// {0,0,1}
// 复制
// 备注：
// n == cows.length
// 1 <= n <= 3000
// Node.val 为 0 或 1

import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param head ListNode类 
     * @return ListNode类
     */
    public ListNode sortCowsIV (ListNode head) {
        if (head == null) {
            return null; // 如果链表为空，直接返回null
        }

        // 1. 统计黑牛（0）和白牛（1）的数量
        int blackCount = 0;
        int whiteCount = 0;
        ListNode current = head;

        while (current != null) {
            if (current.val == 0) {
                blackCount++;
            } else if (current.val == 1) {
                whiteCount++;
            }
            current = current.next;
        }

        // 2. 根据统计结果重新构建链表
        ListNode dummy = new ListNode(-1);  // 使用哨兵节点简化操作
        current = dummy;

        // 先放置blackCount个黑牛（0）
        for (int i = 0; i < blackCount; i++) {
            current.next = new ListNode(0);
            current = current.next;
        }

        // 再放置whiteCount个白牛（1）
        for (int i = 0; i < whiteCount; i++) {
            current.next = new ListNode(1);
            current = current.next;
        }

        // 返回排序后的链表（跳过哨兵节点）
        return dummy.next;
    }
}
