//703. Kth Largest Element in a Stream
//

class KthLargest {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int max = 0;

    public KthLargest(int k, int[] nums) {
        int len = nums.length;
        max = k;
        for (int i = 0; i < Math.min(len, k); i++) {
        minHeap.add(nums[i]);
        }
        if (nums.length > k) {
            for (int i = k; i < len; i++) {
                minHeap.add(nums[i]);
                minHeap.poll();
            }
        }
        // if (nums.length > k) {
        //     for (int i = len; i < Math.min(len, k); i++) {
        //         minHeap.add(nums[i]);
        //         minHeap.poll();
        //     }
        // }
        // System.out.println(minHeap.size());
        // System.out.println(minHeap.peek());
    }

    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > max) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

//eaiser

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k);

        // Add all elements from nums to the heap
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);
        if (minHeap.size()>k)minHeap.poll();     
        return minHeap.peek();
    }
}
