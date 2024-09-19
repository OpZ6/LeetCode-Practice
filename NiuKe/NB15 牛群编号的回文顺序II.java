//https://www.nowcoder.com/practice/e887280579bb4d91934378ea359f632e?tpId=354&tqId=10595827&ru=/exam/oj&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E9%259D%25A2%25E8%25AF%2595%26topicId%3D354

// 描述
// 农场里有一些牛，每头牛都有一个编号（0-9）。这些牛按照一定的顺序站立，我们可以把这个顺序看作是一个单链表，牛的编号就是链表的节点。现在农场主想知道，这些牛的编号顺序是否是回文的。如果是，则返回空链表；如果不是，返回最大的连续回文子链表的头节点（保证唯一）。
// 示例1
// 输入：
// {1,2,3,2,1,4,5,6,7,8,9,0}
// 复制
// 返回值：
// {1,2,3,2,1}
// 复制
// 示例2
// 输入：
// {1,1,2,3,4,5,6,7,8,9,0}
// 复制
// 返回值：
// {1,1}
// 复制
// 备注：
// 链表中节点数目n
// 1 <= n <= 100
// 0 <= Node.val <= 9

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

//双指针
public class Solution {
    /**
     * 将单链表读入 list 便于双指针遍历。
	 从 左向右 遍历，以当前节点为中心，双指针 pre 代表中心前的节点，tail 代表中心后的节点，如果 pre=中心节点，则 pre-1.比如 4 4 5,4 为中心，4 4 则为回文，如果 tail=中心节点则 tail+1,比如 4 5 5,5 为中心，5 5 为回文，如果 pre 和 tail 相等，则pre-1.tail+1，形态如 4 5 4，以此三个条件为基础向两边发散，不满足以上三个条件则不为回文，结束发散，tail-preh获得回文长度，与 count 相比较，如果比之前的回文长，则记录开始节点和结束节点，即 start=pre，end = tail。 最后判断若 start=end 代表没有回文，若 end-start = list.size()-1代表整个链表为回文，不满足这两个条件即存在回文，将 end 节点的下一个节点置空，返回start 节点即可。
     *
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode maxPalindrome (ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int start = 0;
        int end = 0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int pre = i;
            int tail = i;
            while (pre >=0 && tail < list.size()) {

                if (pre - 1 >= 0 && tail + 1 < list.size() &&
                        list.get(pre - 1).val == list.get(tail + 1).val) {
                    pre--;
                    tail++;

                } else if (tail + 1 < list.size() &&
                           list.get(tail + 1).val == list.get(i).val) {
                    tail++;
                } else if (pre - 1 >= 0 && list.get(pre - 1).val == list.get(i).val) {
                    pre--;
                } else {
                    break;
                }
            }
            if (count < tail - pre) {
                start = pre;
                end = tail;
            }
        }
        if (start != end && end - start != list.size() - 1) {
            list.get(end).next = null;
            return list.get(start);
        }
        return null;
    }
}

