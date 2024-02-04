// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/description/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[][] counts = new int[map.size()][2];
        int i = 0;

        for (int key : map.keySet()) {
            counts[i][0] = map.get(key);
            counts[i][1] = key;
            i++;
        }
        Arrays.sort(counts, Comparator.comparingInt(o -> o[0]));
        for (int[] count : counts) {
            System.out.println(Arrays.toString(count));
        }

        for (int j = 0; j < k; j++) {
            res[j] = counts[counts.length - j - 1][1];
        }
        return res;
    }
}

//bucket sort, use two map
class Solution {
    // O(nums.length) time because we iterate nums to create the frequency map andthen do a bucket sort.
    // O(nums.length) space because of the array we use for bucket sort, also all numbers could be distinct and in this case the HashMap would have one entry for each element.
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
            return new int[0];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int currNum : nums)
            freqMap.put(currNum, freqMap.getOrDefault(currNum, 0) + 1);

        List<Integer>[] buckets = new ArrayList[nums.length + 1]; // Number of occurrences of all elements must be in [0, nums.length].
        for (int key : freqMap.keySet()) {
            if (buckets[freqMap.get(key)] == null)
                buckets[freqMap.get(key)] = new ArrayList<>();
            buckets[freqMap.get(key)].add(key);
        }

        int[] result = new int[Math.min(freqMap.size(), k)]; // In case we want to return less than k elements, k could be greater than the number of distinct elements in nums.
        int resIdx = 0;
        for (int i = buckets.length - 1; i >= 0; --i) {
            if (buckets[i] == null)
                continue; // Because we only initialized buckets that we inserted elements into, empty buckets are null values.
            for (int currNum : buckets[i]) {
                result[resIdx++] = currNum;
                if (resIdx == result.length)
                    return result;
            }
        }
        return result;
    }
}

//use maxheap

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for (int key : map.keySet()) {
            heap.add(key);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(heap.poll());
        }
        
        return ans;
    }
}
