//374. Guess Number Higher or Lower
//https://leetcode.com/problems/guess-number-higher-or-lower/

/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = right + (left - right) / 2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            }
            if (res == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 1;
    }
}
//start + (end - start) / 2来计算中点，这样可以避免整数溢出的问题，尤其是在start和end非常大的时候。
