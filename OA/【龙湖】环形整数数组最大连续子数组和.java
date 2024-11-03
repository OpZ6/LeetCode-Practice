//求一个环形整数数组的最大 可能的连续子数组的和
  public static int maxSubarraySumCircular(int[] nums) {
      int totalSum = 0;
      int maxSum = Integer.MIN_VALUE;
      int minSum = Integer.MAX_VALUE;
      int currentMax = 0;
      int currentMin = 0;

      for (int num : nums) {
          totalSum += num;

          // Calculate maximum subarray sum using Kadane's algorithm
          currentMax = Math.max(num, currentMax + num);
          maxSum = Math.max(maxSum, currentMax);

          // Calculate minimum subarray sum using Kadane's algorithm
          currentMin = Math.min(num, currentMin + num);
          minSum = Math.min(minSum, currentMin);
      }

      // If all numbers are negative, maxSum will be the result
      if (maxSum < 0) {
          return maxSum;
      }

      // Otherwise, the result is the maximum between maxSum and (totalSum - minSum)
      return Math.max(maxSum, totalSum - minSum);
  }


// 给定一个整数数组 nums，这个数组是环形的，也就是说，数组的尾部可以与头部相连，形成一个循环。你的目标是找到在这个环形数组中可能的最大连续子数组的和。

// 核心思想
// 我们需要找到最大连续子数组的和。为了处理环形数组的特殊性，我们把问题分为两种可能的情况：

// 子数组在数组内部，不跨越数组的末尾和开头。
// 子数组跨越了数组的末尾和开头。
// 代码详细解释

// 步骤分解
// 初始化变量：

// totalSum：数组中所有元素的总和。
// maxSum：最大子数组和，初始值为 Integer.MIN_VALUE，用于存储 Kadane 算法计算的结果。
// minSum：最小子数组和，初始值为 Integer.MAX_VALUE，用于存储 Kadane 算法计算的结果。
// currentMax 和 currentMin：分别用于跟踪当前最大和最小子数组和。

// 遍历数组并使用 Kadane 算法计算：

// for (int num : nums) {
//     totalSum += num;

//     // Calculate maximum subarray sum using Kadane's algorithm
//     currentMax = Math.max(num, currentMax + num);
//     maxSum = Math.max(maxSum, currentMax);

//     // Calculate minimum subarray sum using Kadane's algorithm
//     currentMin = Math.min(num, currentMin + num);
//     minSum = Math.min(minSum, currentMin);
// }
// totalSum += num：累加整个数组的和，用于后续计算环形子数组的情况。
// 最大子数组和 (maxSum)：
// currentMax = Math.max(num, currentMax + num)：这是 Kadane 算法，用于计算以当前元素为结尾的最大子数组和。如果当前元素 num 大于 currentMax + num，则重置子数组，以 num 开始。
// maxSum = Math.max(maxSum, currentMax)：更新全局最大值。
// 最小子数组和 (minSum)：
// currentMin = Math.min(num, currentMin + num)：用于计算以当前元素为结尾的最小子数组和。
// minSum = Math.min(minSum, currentMin)：更新全局最小值。

// 处理全负数情况：

// if (maxSum < 0) {
//     return maxSum;
// }
// 如果所有的元素都是负数，那么最大的子数组和就是 maxSum。因为在这种情况下，将整个数组视为一个子数组显然不合适，所以直接返回 Kadane 算法求得的最大子数组和。
// 计算环形子数组和：

// return Math.max(maxSum, totalSum - minSum);
// 最大子数组和可能是 maxSum（直接求得的内部子数组和）。
// 也可能是通过跨越末尾和开头的情况得到，这时的最大子数组和可以表示为**totalSum - minSum**。
// totalSum - minSum：表示去除最小子数组和后的剩余部分。这样就相当于选择了一个从尾到头跨越的子数组。
// 最终结果是 maxSum 和 (totalSum - minSum) 中的较大值。
// 例子分析
// 考虑输入数组 nums = [1, -2, 3, -2]：

// Kadane 算法计算 maxSum：

// currentMax：1, -1, 3, 1
// maxSum = 3，最大子数组和为 [3]。
// Kadane 算法计算 minSum：

// currentMin：1, -2, 1, -2
// minSum = -2，最小子数组和为 [-2]。
// 计算 totalSum：

// totalSum = 1 + (-2) + 3 + (-2) = 0
// 结果：

// 环形子数组和为 totalSum - minSum = 0 - (-2) = 2
// 最大和为 Math.max(3, 2) = 3，因此结果为 3。
// 总结
// 最大连续子数组和可以通过 Kadane 算法直接求得。
// 对于环形数组的情况，可以通过 totalSum - minSum 来获得最大跨越的子数组和。
// 如果数组全部是负数，则 maxSum 是最大值。
// 最终返回 maxSum 和 totalSum - minSum 中的较大值，以找到可能的最大子数组和。
// 该方法的时间复杂度为 O(n)，因为每个元素只被遍历了一次，非常高效。
