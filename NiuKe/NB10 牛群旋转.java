//https://www.nowcoder.com/practice/5137e606573843e5bf4d8ea0d8ade7f4?tpId=354&tqId=10591410&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj

// 描述
// 农场里有一群牛，每头牛都有一个编号，编号由一个整数表示，整数范围是[-100, 100]。牛群中的牛用单链表表示。

// 现在，农场主想要调整牛群的顺序。给你一个链表的头节点 head ，将链表每头牛向右移动 k 个位置。

// 示例1
// 输入：
// {1,2,3,4,5,6,7,8,9},3
// 复制
// 返回值：
// {7,8,9,1,2,3,4,5,6}
// 复制
// 示例2
// 输入：
// {1,2,3},4
// 复制
// 返回值：
// {3,1,2}
// 复制
// 备注：
// 链表中节点的数目在范围 [0, 500] 内
// -100 <= Node.val <= 100
// 0 <= k <= 2 * 10^9

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
     * @param k int整型
     * @return ListNode类
     */
    public ListNode rotateLeft(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;  // 空链表或只有一个节点时，直接返回
        }

        // 1. 计算链表的长度
        ListNode current = head;
        int length = 1;
        while (current.next != null) {
            current = current.next;
            length++;
        }

        // 2. 取模处理，减少不必要的旋转次数
        k = k % length;
        if (k == 0) {
            return head;  // 如果旋转次数是链表长度的整数倍，直接返回原链表
        }

        // 3. 将链表的最后一个节点连接到头部，形成环形链表
        current.next = head;

        // 4. 找到新的链表的断点位置，即新的头结点
        int newHeadPos = length - k;
        current = head;
        for (int i = 1; i < newHeadPos; i++) {
            current = current.next;
        }

        // 5. 断开链表，并返回新的头结点
        ListNode newHead = current.next;
        current.next = null;

        return newHead;
    }
}
