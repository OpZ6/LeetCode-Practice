//84. Largest Rectangle in Histogram
//https://leetcode.com/problems/largest-rectangle-in-histogram/

//stack of range
//正确的解决方案是使用单调栈来找到每个高度的左右边界，然后计算以该高度为高度的矩形面积，并更新最大面积。单调栈是一种特殊的栈，它的元素严格单调递增或递减。这种数据结构可以高效地处理类似的问题，如寻找一个数组中每个元素左边第一个比它小的元素、右边第一个比它小的元素等。
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // 计算每个高度的左边第一个比它小的元素的索引
        left[0] = -1;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && heights[j] >= heights[i]) j = left[j];
            left[i] = j;
        }

        // 计算每个高度的右边第一个比它小的元素的索引
        right[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && heights[j] >= heights[i]) j = right[j];
            right[i] = j;
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int area = width * heights[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}


//Sliding window！WRONG!! 87/99

// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int maxlen = heights.length;
//         int res = 0;

//         for (int len = 1; len <= maxlen; len++) {
//             int[] tmp = getSlidingWindowMinimum(heights, len);
//             //System.out.println(Arrays.toString(tmp));

//             res = Math.max(res, Arrays.stream(tmp).max().getAsInt() * len);
            
//             // System.out.println(res);
//         }

//         return res;
//     }

//     public static int[] getSlidingWindowMinimum(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k <= 0) {
//             return new int[0];
//         }
        
//         int n = nums.length;
//         int[] minValues = new int[n - k + 1]; // 结果数组，长度为 n - k + 1
//         Deque<Integer> deque = new LinkedList<>(); // 双端队列，存储窗口内的元素索引
        
//         for (int i = 0; i < n; i++) {
//             // 移除窗口之外的元素索引
//             while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
//                 deque.pollFirst();
//             }
            
//             // 移除所有大于当前元素值的元素索引
//             while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
//                 deque.pollLast();
//             }
            
//             // 将当前元素索引添加到队列尾部
//             deque.offerLast(i);
            
//             // 当窗口形成时，记录最小值
//             if (i >= k - 1) {
//                 minValues[i - k + 1] = nums[deque.peekFirst()];
//             }
//         }
        
//         return minValues;
//     }
// }
