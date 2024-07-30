//220. Contains Duplicate III
//https://leetcode.com/problems/contains-duplicate-iii/description/

import java.util.TreeSet;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();  // 创建一个TreeSet来存储窗口内的值
        for (int i = 0; i < nums.length; i++) {  // 遍历数组中的每一个元素
            Long floor = set.floor((long) nums[i] + valueDiff);  // 在set中找到小于等于 nums[i] + valueDiff 的最大值
            Long ceil = set.ceiling((long) nums[i] - valueDiff);  // 在set中找到大于等于 nums[i] - valueDiff 的最小值
            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) {  // 如果floor或ceil非空且满足条件，表示找到了符合要求的元素
                return true;  // 返回true，表示存在符合条件的一对(i, j)
            }

            set.add((long) nums[i]);  // 将当前元素添加到set中，这样set始终保存窗口内的值
            if (i >= indexDiff) {  // 当窗口大小超过indexDiff时，需要从set中移除最旧的元素
                set.remove((long) nums[i - indexDiff]);  // 移除窗口外的元素，保持窗口大小为indexDiff
            }
        }
        return false;  // 如果遍历完整个数组都没有找到符合条件的一对(i, j)，则返回false
    }
}


//bucket sort (size = indexDiff)
class Solution {
    // Get the ID of the bucket from element value x and bucket width w
    // Java's division `/` rounds towards zero, but we need floor division for
    // correct bucketing.
    private long getID(long x, long w) {
        return Math.floorDiv(x, w);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        Map<Long, Long> buckets = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long bucket = getID(nums[i], w);
            // check if current bucket is empty, each bucket may contain at most one element
            if (buckets.containsKey(bucket))
                return true;
            // check the neighbor buckets for almost duplicate
            if (buckets.containsKey(bucket - 1) &&
                    Math.abs(nums[i] - buckets.get(bucket - 1)) < w)
                return true;
            if (buckets.containsKey(bucket + 1) &&
                    Math.abs(nums[i] - buckets.get(bucket + 1)) < w)
                return true;
            // now bucket is empty and no almost duplicate in neighbor buckets
            buckets.put(bucket, (long) nums[i]);
            if (i >= k)
                buckets.remove(getID(nums[i - k], w));
        }
        return false;
    }

// 代码解释

// private long getID(long x, long w) {
//     return Math.floorDiv(x, w);
// }
// getID 方法用于计算给定数值 x 属于哪个桶。由于 Java 的除法 / 是向零取整，而我们需要的是向下取整，因此使用了 Math.floorDiv(x, w) 来确保即使对于负数也能正确地归入桶。

// if (t < 0) return false;
// 如果 t 小于 0，直接返回 false，因为差值不能为负。

// Map<Long, Long> buckets = new HashMap<>();
// long w = (long) t + 1;
// buckets 用来存储每个桶的最后一个元素的值，键是桶的ID，值是该桶内的元素值。
// w = t + 1 确定了桶的宽度，确保桶内任意两数之差不会超过 t。

// for (int i = 0; i < nums.length; ++i) {
//     long bucket = getID(nums[i], w);
//     if (buckets.containsKey(bucket)) return true;
// 遍历数组 nums，对于每个元素 nums[i]，计算其所在的桶 bucket。
// 如果桶已经包含了元素（由 containsKey 检查），则说明在 k 范围内已经有一个数在同一个桶中，因此返回 true。

// if (
//     buckets.containsKey(bucket - 1) &&
//     Math.abs(nums[i] - buckets.get(bucket - 1)) < w
// ) return true;
// if (
//     buckets.containsKey(bucket + 1) &&
//     Math.abs(nums[i] - buckets.get(bucket + 1)) < w
// ) return true;
// 检查当前元素左右相邻的桶（bucket - 1 和 bucket + 1）。
// 如果相邻桶存在，并且桶内元素与当前元素的绝对差小于 w，则满足条件，返回 true。

// buckets.put(bucket, (long) nums[i]);
// if (i >= k) buckets.remove(getID(nums[i - k], w));
// 将当前元素 nums[i] 放入其对应的桶中。
// 如果索引 i 大于等于 k，则需要移除窗口外的旧元素，即 nums[i - k]。这通过计算 nums[i - k] 的桶ID并从 buckets 中移除实现。
