//287. Find the Duplicate Number
//https://leetcode.com/problems/find-the-duplicate-number/description/

//Hashset
class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> check = new HashSet<>();

        for (int num : nums) {
            if (check.contains(num)) {
                return num;
            }
            check.add(num);
        }

        return 0;
    }
}

//binary
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == mid && nums[mid - 1] > mid - 1) {
                return mid;
            }
            if (nums[mid] > mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }
}

//sort and check
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++)
            if(nums[i] == nums[i+1]) return nums[i];
        return -1;
    }
}

//similar to hashset
class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        boolean[] set = new boolean[n+1];
        for(int i=0;i<n;i++) {
            if(set[nums[i]]) return nums[i];
            set[nums[i]] = true;
        }
        return -1;
    }
}

//Hare & Tortoise Method

// 这个方法是基于“链表中的环”的概念来实现的。在这个问题中，我们将数组视为一个链表，数组的每个元素都指向下一个元素的索引。由于数组中有一个重复的数字，这会导致链表中形成一个环。我们的目标是找到这个环的入口，这个入口就是重复的数字。

// 下面是详细的步骤解释：

// 步骤 1：检测环（慢指针和快指针）
// 初始化两个指针，slow 和 fast，都指向数组的第一个元素。
// slow 每次移动一步，fast 每次移动两步。
// 如果数组中没有重复的数字，fast 最终会遇到 null，表示没有环。但由于数组中有一个重复的数字，fast 最终会与 slow 相遇，这表明我们找到了一个环。
// 步骤 2：找到环的入口
// 当 slow 和 fast 相遇后，将 fast 重置为数组的第一个元素。
// 现在，slow 和 fast 都每次移动一步。
// slow 和 fast 最终会在环的入口处再次相遇，这是因为 slow 从相遇点开始，每次移动一步，而 fast 从数组的开始处，以相同的速度移动，最终会在环的入口处相遇。
// 步骤 3：返回重复的数字
// 当 slow 和 fast 第二次相遇时，它们指向的位置就是重复的数字。
// 现在，让我们通过一个例子来具体看看这个过程：

// 假设我们有以下数组：

// [3, 1, 3, 4, 2]
// 我们可以将其视为一个链表，其中每个元素的值指向下一个元素的索引。例如，第一个元素是 3，所以它指向数组的第三个元素，即 4。

// 0 → 2 → 3 → 4 → 1 → 2
// 这里，0 → 2 表示数组的第一个元素指向数组的第三个元素，以此类推。

// 现在，我们使用慢指针和快指针来找到环的入口：

// 初始化 slow 和 fast 都在数组的第一个元素。
// slow 每次移动一步，fast 每次移动两步。
// slow 和 fast 最终在索引 2（值为 3）处相遇。
// 重置 fast 到数组的第一个元素。
// slow 和 fast 都每次移动一步，最终在索引 2 处再次相遇。
// 因此，重复的数字是 3。

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do { // we are sure that at least one duplicate is there
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        // find the head of loop
        fast = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}

//Similar
// 这个方法是基于数组中的值作为索引来修改数组中的值的特性。由于数组长度是 n+1，数组中的值都在 [1, n] 的范围内，这意味着数组中的每个值都可以作为数组的索引。同时，由于数组中有一个重复的值，我们可以利用这个特性来找到重复的值。

// 这里是详细的步骤解释：

// 遍历数组 nums 中的每个值 n。
// 对于每个值 n，将其作为索引去查找 nums 中的对应位置。
// 如果 nums 中对应位置的值是正数，将其乘以 -1，使其变为负数。这样做的目的是标记这个位置已经被访问过。
// 如果 nums 中对应位置的值已经是负数，这意味着我们之前已经访问过这个位置，因此当前的值 n 就是重复的值。
// 由于数组中的每个值都在 [1, n] 的范围内，我们可以安全地使用 Math.abs(n) 来获取一个正数的索引，即使 nums[Math.abs(n)] 已经被标记为负数。

// 这个方法的关键在于，我们利用数组中的重复值会导致我们再次访问同一个索引的特性，从而找到重复的值。由于我们修改数组中的值来标记访问，这个方法会修改原数组，不符合题目中“不修改原数组”的要求。如果题目要求不能修改原数组，那么这个方法就不适用。

// 下面是一个例子来说明这个方法的工作原理：

// 假设我们有以下数组：

// [3, 1, 3, 4, 2]
// 首先，我们访问 nums[0]，值为 3，将其对应位置的值 nums[3]（值为 4）变为负数，数组变为 [3, 1, 3, -4, 2]。
// 接着，我们访问 nums[1]，值为 1，将其对应位置的值 nums[1] 变为负数，数组变为 [3, -1, 3, -4, 2]。
// 然后，我们访问 nums[2]，值为 3，发现 nums[3] 已经是负数，这意味着 3 是重复的值。
// 因此，重复的数字是 3。

class Solution {
    public int findDuplicate(int[] nums) {
        for (int n : nums) {
            if (nums[Math.abs(n)] < 0) {
                return Math.abs(n);
            } else {
                nums[Math.abs(n)] *= -1;
            }
        }
        return -1;
    }
}
