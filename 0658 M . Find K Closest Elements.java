//658. Find K Closest Elements
//https://leetcode.com/problems/find-k-closest-elements/


//我们使用二分查找来确定最接近x的k个元素的起始位置。我们维护一个大小为k的窗口，在数组中进行二分查找，根据窗口两端与x的距离来决定窗口的移动方向。当二分查找完成后，我们得到了一个起始位置，从这个位置开始，连续的k个元素就是我们想要的结果。
// 这个方法的时间复杂度是O(log(n-k) + k)，其中O(log(n-k))是二分查找的时间复杂度，O(k)是构造结果列表的时间复杂度。这比直接向两边扩展的方法效率要高，因为直接向两边扩展的方法在最坏情况下可能需要O(n)的时间复杂度。

//binary search + sliding window
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k; // 初始化右边界，确保最终结果中至少有k个元素

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 比较窗口两端与x的距离，以确定窗口的移动方向
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 将最终确定的窗口内的元素添加到结果列表中
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}

//two point
class Solution {
    
    // Approach:
    // Using two pointers, we are going the 'start' and 'end' pointers towards each other,
    // until only k elements between 'start' and 'end'.
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        int start = 0;
        int end = arr.length - 1;
        // Between the 'start' and 'end' pointers, inclusive, contains all the k integers that is closest to x.
        while (end - start >= k) {
            // Move 'start' to the right if 'end' is closer to x, or move 'end' to the left if 'start' is closer to x.
            if (Math.abs(arr[start] - x) > Math.abs(arr[end] - x)) {
                start++;
            } else {
                end--;
            }
        }

        // Input all the k closest integers into the result.
        List<Integer> result = new ArrayList<>(k);
        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}

//heap(slowest)
class Solution {

    // Approach:
    // Using a min heap priority queue, add all the smallest integers up to k integers.
    // Then, traverse the 'arr' array will replacing the priority queue with integer closer to x.

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Traverse the array with,
        // First, add all the smallest integers up to k number.
        // Second, if found a closer integer to x, remove the smallest integer from the priority queue, and add the new integer.
        // This is because the smallest integer is always the further to x, if a larger number is closer to x.
        for (int integer : arr) {
            if (k > 0) {
                minHeap.offer(integer);
                k--;
            } else if (Math.abs(minHeap.peek() - x) > Math.abs(integer - x)) {
                minHeap.poll();
                minHeap.offer(integer);
            }
        }
        
        // Add the integers from the priority queue to the result.
        // This will automatically add in ascending order, from smallest to largest k integers closest to x.
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        return result;
    }
}
