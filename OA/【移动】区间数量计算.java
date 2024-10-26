//java代码题，给定一个int数组，以及整数x和k，求有多少个区间满足其中正好有k个数可以被x整除，例如1 2 3 4这个序列，给定x=2，k=1，则返回6

import java.util.ArrayList;
import java.util.List;

public class CountSubarrays {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int x = 2;
        int k = 1;
        System.out.println(countSubarrays(nums, x, k));
    }

    public static int countSubarrays(int[] nums, int x, int k) {
        int n = nums.length;
        int result = 0;
        int divisibleCount = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        
        for (int right = 0; right < n; right++) {
            if (nums[right] % x == 0) {
                divisibleCount++;
            }
            
            if (countMap.containsKey(divisibleCount - k)) {
                result += countMap.get(divisibleCount - k);
            }
            
            countMap.put(divisibleCount, countMap.getOrDefault(divisibleCount, 0) + 1);
        }
        
        return result;
    }
}
