// 78. Subsets
// https://leetcode.com/problems/subsets/description/

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        res.add(new ArrayList<>());
        if (nums.length > 0) {
            helper(new ArrayList<>(), nums, 0);
        }
        return res;
    }

    private void helper(List<Integer> input, int[] nums, int pos) {
        if (pos >= nums.length) {
            return;
        }
        int newNum = nums[pos];

        input.add(newNum);
        res.add(new ArrayList<>(input));
        helper(input, nums, pos + 1);
        input.remove(input.size() - 1);
        helper(input, nums, pos + 1);
    }
}
