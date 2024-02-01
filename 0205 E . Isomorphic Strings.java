// 205. Isomorphic Strings
// https://leetcode.com/problems/isomorphic-strings/description/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap <Character, Character> map = new HashMap<>();
        int index = 0;
        while (index < s.length()) {
            char charT = t.charAt(index);
            char charS = s.charAt(index);
            if (!map.containsKey(charS)) {
                if (!map.containsValue(charT)) {
                    map.put(charS, charT);
                } else {
                    return false;
                } 
            } else {
                if (map.get(charS) != charT) {
                    return false;
                }
            }
            index++;
        }
        return true;
    }
}

//bucket match (all ascii)
class Solution {
    public boolean isIsomorphic(String s, String t) {

        int map1[] = new int[200];
        int map2[] = new int[200];

        if (s.length() != t.length())
            return false;

        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)])
                return false;

            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
