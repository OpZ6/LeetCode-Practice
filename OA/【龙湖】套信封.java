//信封长宽，最多套几层

public static int maxEnvelopes(int[][] envelopes) {
    if (envelopes == null || envelopes.length == 0) {
        return 0;
    }

    // Sort envelopes by width ascending, and by height descending if widths are the same
    Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    // Extract heights and find the longest increasing subsequence
    int[] heights = new int[envelopes.length];
    for (int i = 0; i < envelopes.length; i++) {
        heights[i] = envelopes[i][1];
    }

    return lengthOfLIS(heights);
}

private static int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    int length = 0;

    for (int num : nums) {
        int index = Arrays.binarySearch(dp, 0, length, num);
        if (index < 0) {
            index = -(index + 1);
        }
        dp[index] = num;
        if (index == length) {
            length++;
        }
    }

    return length;
}

// 这些方法用于解决“俄罗斯套娃信封问题”，即给定若干个信封的长宽，问最多可以嵌套多少个信封。下面是代码逐步的详细解释：

// maxEnvelopes 方法
// 功能
// 解决俄罗斯套娃信封问题。
// 输入：一个二维数组 envelopes，其中每个元素 envelopes[i] 表示信封的宽度和高度 [width, height]。
// 输出：最多可以嵌套的信封数量。
// 核心步骤
// 特殊情况处理：

// java
// 复制代码
// if (envelopes == null || envelopes.length == 0) {
//     return 0;
// }
// 检查输入是否为 null 或为空数组。如果是，则返回 0，表示没有信封可以嵌套。
// 排序信封：

// java
// 复制代码
// Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
// 先对信封按照宽度升序排序。
// 如果宽度相同，则按照高度降序排序。
// 这种排序的目的是确保在处理相同宽度的信封时，它们不会被相互嵌套，从而避免错误。
// 例如，如果有多个信封宽度相同，排序时会将高度较大的放在前面，使得这些信封不会相互嵌套。
// 提取高度信息：

// java
// 复制代码
// int[] heights = new int[envelopes.length];
// for (int i = 0; i < envelopes.length; i++) {
//     heights[i] = envelopes[i][1];
// }
// 创建一个数组 heights 来存储所有信封的高度信息。
// 只关心信封的高度，宽度已经通过排序处理好了。
// 求解最长递增子序列：

// java
// 复制代码
// return lengthOfLIS(heights);
// 高度信息存储在 heights 数组中后，这个问题就转化为求解高度数组中的最长递增子序列（Longest Increasing Subsequence, LIS）。
// 最长递增子序列代表了可以嵌套的信封数。
// lengthOfLIS 方法
// 功能
// 找到一个数组中的最长递增子序列的长度。
// 输入：一个整数数组 nums，这里是信封的高度数组。
// 输出：最长递增子序列的长度。
// 核心步骤
// 初始化数组和变量：

// java
// 复制代码
// int[] dp = new int[nums.length];
// int length = 0;
// dp 数组用于存储当前递增子序列的最小末尾元素。
// length 代表当前找到的最长递增子序列的长度。
// 遍历高度数组：

// java
// 复制代码
// for (int num : nums) {
//     int index = Arrays.binarySearch(dp, 0, length, num);
//     if (index < 0) {
//         index = -(index + 1);
//     }
//     dp[index] = num;
//     if (index == length) {
//         length++;
//     }
// }
// 遍历 nums 数组（即信封的高度数组）：
// 二分查找：使用 Arrays.binarySearch(dp, 0, length, num) 查找当前高度 num 应该插入的位置。
// 如果 num 在 dp 数组中不存在，binarySearch 返回一个负数 -(index + 1)，表示应插入的位置。
// 通过取负数并减 1，计算出 num 应该插入的正确位置 index。
// 更新 dp 数组：将 dp[index] 更新为 num。
// 目的是在维护递增子序列的过程中，保持 dp 数组中的元素尽可能小，以便在后续可以构造更长的递增子序列。
// 更新长度：如果插入的位置正好是当前长度的末尾，说明子序列长度增加了，因此更新 length。
// 返回最长递增子序列的长度：

// java
// 复制代码
// return length;
// 返回最终找到的最长递增子序列的长度。
// 为什么使用二分查找
// 使用 Arrays.binarySearch() 的目的是为了加速查找和更新的过程。
// 由于 dp 数组是按顺序存储当前递增子序列的最小末尾元素，我们可以利用二分查找来找到插入位置，从而使时间复杂度从 O(n^2) 降低到 O(n log n)。
// 在 dp 数组中，length 位置之前的元素总是递增的，因此可以高效地使用二分查找。
// 示例
// 假设信封的数组是：[[5,4], [6,4], [6,7], [2,3]]。

// 排序信封：

// 按宽度升序，并按高度降序排序后，数组变为：[[2,3], [5,4], [6,7], [6,4]]。
// 提取高度信息：

// heights = [3, 4, 7, 4]。
// 求最长递增子序列：

// 遍历 heights 数组，得到最长递增子序列是 [3, 4, 7]，其长度为 3。
// 总结
// maxEnvelopes 方法通过对信封排序，将问题转化为求解高度数组中的最长递增子序列问题。
// 排序时采用按宽度升序、高度降序的方式，确保在相同宽度情况下高度不能嵌套，从而避免错误嵌套。
// lengthOfLIS 方法利用动态规划和二分查找的结合，高效地求解最长递增子序列的长度，使得时间复杂度为 O(n log n)，从而提升性能。
