//367. Valid Perfect Square
//https://leetcode.com/problems/valid-perfect-square/description/

class Solution { // use long!!!
    public boolean isPerfectSquare(int num) {
        long start = 1;
        long end = num / 2;

        while (start <= end) {
            long mid = start + (end - start) / 2;

            if (mid * mid == num) {
                return true;
            }

            if (mid * mid < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start * start == num) {
            return true;
        }

        return false;
    }
}

//Newton’s method
    long r = x;
    while (r*r > x)
        r = (r + x/r) / 2;
    return r*r == x;
