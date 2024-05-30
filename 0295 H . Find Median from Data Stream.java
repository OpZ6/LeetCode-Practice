//295. Find Median from Data Stream
//https://leetcode.com/problems/find-median-from-data-stream/description/

class MedianFinder {

    PriorityQueue<Integer> firstHalf;
    PriorityQueue<Integer> lastHalf;

    public MedianFinder() {
        lastHalf = new PriorityQueue<>();
        firstHalf = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {

        int lowerMedian = Integer.MIN_VALUE;
        if (firstHalf.peek() != null) {
            lowerMedian = firstHalf.peek();
        }
        // int upperMedian = Integer.MAX_VALUE;
        // if (lastHalf.peek() != null) {
        //     upperMedian = lastHalf.peek();
        // }

        if (num <= lowerMedian) {
            firstHalf.offer(num);
        } else {
            lastHalf.offer(num);
        }

        while (firstHalf.size() > lastHalf.size()) {
            lastHalf.offer(firstHalf.poll());
        }

        while (firstHalf.size() + 1 < lastHalf.size()) {
            firstHalf.offer(lastHalf.poll());
        }
    }
    
    public double findMedian() {
        if(lastHalf.size() > firstHalf.size()){
            return lastHalf.peek();
        }
        return (lastHalf.peek() + firstHalf.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

//easier but more time cost
class MedianFinder {

    PriorityQueue<Integer> firstHalf; // 大根堆，存储中位数左边的数字
    PriorityQueue<Integer> lastHalf; // 小根堆，存储中位数右边的数字

    public MedianFinder() {
        lastHalf = new PriorityQueue<>();
        firstHalf = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        // 直接向右半部分添加数字
        lastHalf.offer(num);
        // 然后调整，确保左半部分的大小不小于右半部分
        firstHalf.offer(lastHalf.poll());

        // 如果左半部分比右半部分多了多于一个元素，调整回来
        if (firstHalf.size() > lastHalf.size()) {
            lastHalf.offer(firstHalf.poll());
        }
    }
    
    public double findMedian() {
        if (lastHalf.size() > firstHalf.size()) {
            return lastHalf.peek();
        }
        return (lastHalf.peek() + firstHalf.peek()) / 2.0;
    }
}
