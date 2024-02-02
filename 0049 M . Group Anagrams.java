// 49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/description/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String value = strs[i];
            char tmp[] = value.toCharArray();
            Arrays.sort(tmp);
            String key = new String(tmp);

            List<String> tmpValue = map.getOrDefault(key, new ArrayList<String>());
            tmpValue.add(value);
            map.put(key, tmpValue);
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }
}

//better
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {//better traversal
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());//need to create a list and add later.
            }

            map.get(sortedWord).add(word);//existed can directly add.
        }

        return new ArrayList<>(map.values());
    }
}
