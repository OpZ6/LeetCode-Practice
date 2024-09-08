//39. Combination Sum
//https://leetcode.com/problems/combination-sum/description/

class Solution {
    List<Integer> path = new ArrayList<Integer>();
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        rollBack(candidates, target, 0, 0);
        return res;
    }

    public void rollBack(int[] nums, int target, int index, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target)
            return;
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            sum += nums[i];
            rollBack(nums, target, i, sum);
            path.remove(path.size() - 1);
            sum -= nums[i];
        }
    }
}
