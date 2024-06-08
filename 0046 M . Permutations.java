//46. Permutations
//https://leetcode.com/problems/permutations/

//backtrack
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        backtrack(nums, temp, result);
        return result;
    }

    public void backtrack(int[] nums, List<Integer> temp, List<List<Integer>> result){
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));//important!!! dont break temp!!!
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                backtrack(nums, temp, result);
                temp.remove(temp.size() - 1);
            }
        }
    }   
}
