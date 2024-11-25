//763. Partition Labels
//https://leetcode.com/problems/partition-labels/description/

class Solution {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        List<Character> currentNum = new ArrayList<>();
        int len = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.get(c) - 1);
            len++;
            if (!currentNum.contains(c)) {
                currentNum.add(c);
            }

            if (currentNum.size() != 0) {
                boolean isSub = true;
                for (char tmp : currentNum) {
                    if (map.get(tmp) != 0) {
                        isSub = false;
                    }
                }
                if (isSub) {
                    res.add(len);
                    len = 0;
                    currentNum = new ArrayList<>();
                }
            }
        }

        return res;
    }
}

//贪心Greedy algorithm
class Solution {
    public List<Integer> partitionLabels(String s) {
        // 记录每个字母的最后出现位置
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0; // 当前片段的起点
        int end = 0;   // 当前片段的终点
        
        for (int i = 0; i < s.length(); i++) {
            // 更新当前片段的最远边界
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
            
            // 如果遍历到了片段的终点
            if (i == end) {
                res.add(end - start + 1); // 记录片段长度
                start = i + 1; // 更新下一片段的起点
            }
        }

        return res;
    }
}
//  最远位置的作用
// 在遍历字符串时，我们用一个变量 end 表示当前片段的结束位置。
// 遍历过程中，动态更新 end 为当前字母的最远位置（lastIndex[当前字母]），这样就确保当前片段能包含已经出现的所有字母的最远位置。
// 例如：
// 对于字符串 s = "ababcbacadefegdehijhklij"：
// 遍历到第一个 a 时，a 的最远位置是索引 8，所以当前片段的结束位置是至少索引 8。
// 在此之前出现的 b 和 c 的最远位置也在索引 8 之前，所以在索引 8 结束当前片段是合法的。
