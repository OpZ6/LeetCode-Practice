//https://www.nowcoder.com/practice/69f4e5b7ad284a478777cb2a17fb5e6a?tpId=295&tqId=691&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E9%259D%25A2%25E8%25AF%2595%26topicId%3D354

// 描述
// 给出一组区间，请合并所有重叠的区间。
// 请保证合并后的区间按区间起点升序排列。

// 数据范围：区间组数 
// 0
// ≤
// n
// ≤
// 2
// ×
// 1
// 0
// 5
// 0≤n≤2×10 
// 5
//  ，区间内 的值都满足 
// 0
// ≤
// v
// a
// l
// ≤
// 2
// ×
// 1
// 0
// 5
// 0≤val≤2×10 
// 5
 
// 要求：空间复杂度 
// O
// (
// n
// )
// O(n)，时间复杂度 
// O
// (
// n
// l
// o
// g
// n
// )
// O(nlogn)
// 进阶：空间复杂度 
// O
// (
// v
// a
// l
// )
// O(val)，时间复杂度
// O
// (
// v
// a
// l
// )
// O(val)
// 示例1
// 输入：
// [[10,30],[20,60],[80,100],[150,180]]
// 复制
// 返回值：
// [[10,60],[80,100],[150,180]]
// 复制
// 示例2
// 输入：
// [[0,10],[10,20]]
// 复制
// 返回值：
// [[0,20]]

import java.util.*;

/*
 * public class Interval {
 *   int start;
 *   int end;
 *   public Interval(int start, int end) {
 *     this.start = start;
 *     this.end = end;
 *   }
 * }
 */

public class Solution {
    /**
     * 合并重叠的区间
     *
     * @param intervals Interval类的ArrayList，表示区间的集合
     * @return Interval类的ArrayList，表示合并后的区间
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // 如果区间数小于2，直接返回，不需要合并
        if (intervals.size() < 2) {
            return intervals;
        }

        // 按照区间的起始点从小到大排序
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        ArrayList<Interval> merged = new ArrayList<>();
        // 把第一个区间加入到结果中
        merged.add(intervals.get(0));

        // 从第二个区间开始，检查是否有重叠
        for (int i = 1; i < intervals.size(); i++) {
            Interval lastMerged = merged.get(merged.size() - 1);
            Interval current = intervals.get(i);

            // 如果当前区间的开始小于等于上一个合并区间的结束，说明有重叠
            if (current.start <= lastMerged.end) {
                // 更新上一个合并区间的结束时间为两个区间结束时间的较大值
                lastMerged.end = Math.max(lastMerged.end, current.end);
            } else {
                // 如果没有重叠，直接将当前区间加入到结果中
                merged.add(current);
            }
        }

        return merged;
    }
}
