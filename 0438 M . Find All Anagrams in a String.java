//438. Find All Anagrams in a String
//https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        // 用于存储 p 中每个字符的频率
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // 用于存储滑动窗口中的字符频率
        int[] sCount = new int[26];
        int windowLength = p.length();

        // 初始化第一个窗口
        for (int i = 0; i < windowLength; i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        // 检查第一个窗口是否是异位词
        if (areArraysEqual(pCount, sCount)) {
            result.add(0);
        }

        // 滑动窗口遍历字符串 s
        for (int i = windowLength; i < s.length(); i++) {
            // 增加新字符到当前窗口
            sCount[s.charAt(i) - 'a']++;
            // 移除窗口中最左侧的字符
            sCount[s.charAt(i - windowLength) - 'a']--;

            // 检查当前窗口是否是异位词
            if (areArraysEqual(pCount, sCount)) {
                result.add(i - windowLength + 1);
            }
        }

        return result;
    }

    // 辅助函数，用于比较两个数组是否相等
    private boolean areArraysEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
