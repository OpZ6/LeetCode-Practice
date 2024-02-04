// 3. Longest Substring Without Repeating Characters
// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] s_ = s.toCharArray();// no need
        int fast = 0;
        int slow = 0;
        Set<Character> check = new HashSet<>();
        int res = 0;

        while (slow < s.length() && fast < s.length()) {
            if (check.contains(s_[fast])) {// no need
                check.remove(s_[slow]);// no need
                slow++;
            } else {
                check.add(s_[fast]);// no need
                fast++;
                int len = fast - slow;
                res = res > len ? res : len;
            }
        }
        return res;
    }
}

//no tochar[]
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int fast = 0;
        int slow = 0;
        Set<Character> check = new HashSet<>();
        int res = 0;

        while (slow < s.length() && fast < s.length()) {
            if (check.contains(s.charAt(fast))) {
                check.remove(s.charAt(slow));
                slow++;
            } else {
                check.add(s.charAt(fast));
                fast++;
                int len = fast - slow;
                res = res > len ? res : len;
            }
        }
        return res;
    }
}

//Approach 1 - Set
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (!charSet.contains(s.charAt(right))) {
                charSet.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }
                charSet.add(s.charAt(right));
            }
        }
        
        return maxLength;
    }
}

//Approach 2 - Unordered Map
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (!charMap.containsKey(s.charAt(right)) || charMap.get(s.charAt(right)) < left) {
                charMap.put(s.charAt(right), right);
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                left = charMap.get(s.charAt(right)) + 1;
                charMap.put(s.charAt(right), right);
            }
        }
        
        return maxLength;
    }
}

//Approach 3 - Integer Array
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] charIndex = new int[128];
        Arrays.fill(charIndex, -1);
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (charIndex[s.charAt(right)] >= left) {
                left = charIndex[s.charAt(right)] + 1;
            }
            charIndex[s.charAt(right)] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
