//215. Kth Largest Element in an Array
//https://leetcode.com/problems/kth-largest-element-in-an-array/

//heap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            tmp.add(nums[i]);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(tmp);
        
        for (int i = k; i < nums.length; i++) {
            int num = nums[i];
            if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }

        return minHeap.peek();
    }
}

//better
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 创建一个最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // 将数组中的前 k 个元素添加到堆中
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        
        // 遍历数组中的剩余元素
        for (int i = k; i < nums.length; i++) {
            int num = nums[i];
            // 如果当前元素大于堆顶元素，则移除堆顶元素，并将当前元素添加到堆中
            if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        
        // 堆顶元素即为第 k 大的元素
        return minHeap.peek();
    }
}



//Quick Select（time limited 0 n^2）
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] arr, int low, int high, int k) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j <= high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);

        if (i > k) {
            return quickSelect(arr, low, i - 1, k);
        } else if (i < k) {
            return quickSelect(arr, i + 1, high, k);
        } else {
            return arr[i];
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
