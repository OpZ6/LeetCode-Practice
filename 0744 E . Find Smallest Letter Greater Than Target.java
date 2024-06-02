//744. Find Smallest Letter Greater Than Target
//https://leetcode.com/problems/find-smallest-letter-greater-than-target/

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;


        if (compareLetter(letters[start], target) > 0 || compareLetter(letters[end], target) < 0 ) {
            return letters[0];
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (compareLetter(letters[mid], target) > 0 && compareLetter(letters[mid - 1], target) <= 0) {
                return letters[mid];
            }

            if (compareLetter(letters[mid], target) <= 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return letters[0];
    }

    private int compareLetter(char a, char b) {
        return (a - b + 0);
    }
}

//better
//char can be compared directly in JAVA
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (letters[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // 如果 start 超出了数组范围，返回数组的第一个元素
        // 否则返回 letters[start]，它是最小的大于 target 的字符
        return start < letters.length ? letters[start] : letters[0];
    }
}
