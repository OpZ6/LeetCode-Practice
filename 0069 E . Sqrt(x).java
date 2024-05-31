//69. Sqrt(x)
//

class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 4) return 1;
        
        int left = 2;
        int right = x / 2;//faster!!!
        int result = 0;//keep searching!!! To find the largest but not any that <= x

        while (left <= right) {
            int mid = (left + right) / 2;
            int check = x / mid;
            if (check == mid) {
                return mid;
            }
            if (check > mid) {
                result = mid; // 保留当前满足条件的最大值
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result; // 返回满足条件的最大值
    }
}

// 当 check < x 时，代码检查 (mid + 1) * (mid + 1) 是否大于 x，然后返回 mid。但是，当 x 的平方根是一个非整数时，这个判断会导致返回一个错误的结果。例如，当 x 是 6 时，mid 最终会变成 2，而 (mid + 1) * (mid + 1) 即 3 * 3 会大于 6，所以返回了 2，这是错误的。
// 正确的做法是在 check < x 的情况下，应该返回 mid，因为二分查找的目的是找到最大的 mid 使得 mid * mid <= x。所以，当 check < x 时，应该保留 mid 并继续搜索更大的值，而不是立即返回 mid。
