//162. Find Peak Element
//

class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length - 1;

        if(len == 0 || nums[0] > nums[1]){
            return 0;
        }
        if(nums[len] > nums[len - 1]){
            return len;
        }

        int start = 1;
        int end = len - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            
            //WRONG!
            // } else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
            //     start = mid + 1;
            // } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
            //     end = mid - 1;
            // }
            // 1, 2, 1, 2, 1 error!

            } else if (nums[mid - 1] < nums[mid]) {
            //} else if (nums[mid] < nums[mid + 1]) { //both true
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
