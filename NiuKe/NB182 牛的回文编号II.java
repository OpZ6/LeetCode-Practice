// https://www.nowcoder.com/practice/0b576fd673834425878b99c736bb6c34?tpId=354&tqId=10595621&ru=/exam/intelligent&qru=/ta/interview-202-top/question-ranking&sourceUrl=%2Fexam%2Fintelligent

// 描述
// 在一个农场中，农夫使用浮点数来标识他的牛群。农夫发现，有些牛的编号在小数点两边各自都是回文数，他觉得这很有趣。现在农夫给你一个浮点数 x ，表示一头牛的编号，如果这个编号的小数点两边各自都是回文数（不算前导0和后导0，某一边为空也算回文），返回 true ；否则，返回 false 。
// 示例1
// 输入：
// "121.121000"
// 复制
// 返回值：
// true
// 复制
// 示例2
// 输入：
// "123.321000"
// 复制
// 返回值：
// false
// 复制
// 示例3
// 输入：
// "131.000000"
// 复制
// 返回值：
// true
// 复制
// 备注：
// 浮点数用string的形式输入，string长度2 <= length <= 10^5

import java.util.*;

public class Solution {
    /**
     * The class name, method name, and parameter names are already specified,
     * please do not modify, and just return the required value.
     *
     * @param x string string
     * @return bool boolean
     */
    public boolean isPalindromeNumber(String x) {
        // Find index of '.'
        int dotIndex = x.indexOf('.');
        String left, right;
        if (dotIndex == -1) {
            // No decimal point, entire string is the left part
            left = x;
            right = "";
        } else {
            // Split the string into left and right parts
            left = x.substring(0, dotIndex);
            right = x.substring(dotIndex + 1);
        }

        // Remove leading zeros from the left part
        int i = 0;
        while (i < left.length() && left.charAt(i) == '0') {
            i++;
        }
        left = left.substring(i);

        // Remove trailing zeros from the right part
        int j = right.length() - 1;
        while (j >= 0 && right.charAt(j) == '0') {
            j--;
        }
        right = right.substring(0, j + 1);

        // Check if the left part is a palindrome
        boolean leftPalindrome = isPalindrome(left);

        // Check if the right part is a palindrome
        boolean rightPalindrome = isPalindrome(right);

        // Return true if both parts are palindromes
        return leftPalindrome && rightPalindrome;
    }

    // Helper method to check if a string is a palindrome
    private boolean isPalindrome(String s) {
        int n = s.length();
        // Empty string counts as palindrome
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
