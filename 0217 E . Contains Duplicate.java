// 217. Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/description/

//my approach: hashtable
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> checklist = new HashSet<>();
        for (int i : nums) {
            if (checklist.contains(i)) {
                return true;
            }
            checklist.add(i);
        }
        return false;
    }
}

//Approach #2 (Sorting)
public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; ++i) {
        if (nums[i] == nums[i + 1]) return true;
    }
    return false;
}

//should add the 0 case
// what hashset.add return?
// Returns. Boolean. true if the element is added to the HashSet<T> object; false if the element is already present.
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Base case...
        if(nums==null || nums.length==0)
            return false;
        // Create a hashset...
        HashSet<Integer> hset = new HashSet<Integer>();
        // Traverse all the elements through the loop...
        for(int idx: nums){
            // If it contains duplicate...
            if(!hset.add(idx)){
                return true;
            }
        }
        // Otherwise return false...
        return false;
    }
}
