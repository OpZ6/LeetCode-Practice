// 249. Group Shifted Strings
// https://leetcode.com/problems/group-shifted-strings/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strings) {//better traversal
            char[] chars = word.toCharArray();
            int tmp = chars[0];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char)((chars[i] - tmp + 26) % 26 + 'a');
            }
            String sortedWord = new String(chars);

            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());//need to create a list and add later.
            }

            map.get(sortedWord).add(word);//existed can directly add.
        }

        return new ArrayList<>(map.values());
    }
}

