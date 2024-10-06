//56. Merge Intervals
//https://leetcode.com/problems/merge-intervals/description/

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // 按照区间的起始位置进行排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 用于存储合并后的区间结果
        List<int[]> mergedIntervals = new ArrayList<>();

        // 初始化第一个区间
        int[] currentInterval = intervals[0];
        mergedIntervals.add(currentInterval);

        // 遍历每个区间
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) {//先添加到结果队列，再更新添加的区间的结尾值
                // 如果当前区间与下一个区间重叠，更新当前区间的结束位置
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // 如果没有重叠，添加新的区间，并更新当前区间
                currentInterval = interval;
                mergedIntervals.add(currentInterval);
            }
        }

        // 将结果转换为二维数组
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
