// 387. First Unique Character in a String
// https://leetcode.com/problems/first-unique-character-in-a-string/description/

class Solution {
    public int firstUniqChar(String s) {
        HashMap <Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int minIndex = Integer.MAX_VALUE;
        for (char key: map.keySet()) {
            if (map.get(key) == 1) {
                int index = s.indexOf(key);
                minIndex = s.indexOf(key) > minIndex ? minIndex : index;
            }
        }
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }
}

//only contain a-z
class Solution {
    public int firstUniqChar(String s) {
        // Stores lowest index / first index
        int ans = Integer.MAX_VALUE;
        // Iterate from a to z which is 26 which makes it constant
        for (char c = 'a'; c <= 'z'; c++) {
            // indexOf will return first index of alphabet and lastIndexOf will return last index
            // if both are equal then it has occured only once.
            // through this we will get all index's which are occured once
            // but our answer is lowest index
            int index = s.indexOf(c);
            if (index != -1 && index == s.lastIndexOf(c)) {
                ans = Math.min(ans, index);
            }
        }

        // If ans remain's Integer.MAX_VALUE then their is no unique character
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

//
class Solution {
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (i == s.lastIndexOf(c) && i == s.indexOf(c)) return i;
        }

        return -1;

    }
}

//hashtable?
import java.util.Hashtable;

class Solution {
    public int firstUniqChar(String s) {
        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        for (char c : s.toCharArray()) {
            hashtable.put(c, hashtable.getOrDefault(c, 0) + 1);
        }

        if (!hashtable.containsValue(1))
            return -1;

        for (int i = 0; i < s.length(); i++) {
            if (hashtable.get(s.charAt(i)) == 1)
                return i;
        }

        return -1;
    }
}

