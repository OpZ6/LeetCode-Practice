// 771. Jewels and Stones
// https://leetcode.com/problems/jewels-and-stones/description/

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set <Character> check = new HashSet<>();
        char[] jewels_ = jewels.toCharArray();
        char[] stones_ = stones.toCharArray();
        int res = 0;
        for (char jewel : jewels_) {
            check.add(jewel);
        }
        for (char stone : stones_) {
            if (check.contains(stone)) {
                res++;
            }
        }
        return res;
    }
}

//no need to hashset
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int num = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewels.indexOf(stones.charAt(i)) != -1) {//important!
                num++;
            }
        }
        return num;
    }
}
