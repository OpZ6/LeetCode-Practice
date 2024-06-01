//34. Find First and Last Position of Element in Sorted Array
//

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        int resS = -1;
        int resE = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                if (mid == start || (mid - 1 >= 0 && nums[mid - 1] < target)) {
                    resS = mid;
                    break;
                }
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (resS == -1) {
            return new int[]{-1, -1};
        }
        start = resS;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                if (mid == end || (mid + 1 < nums.length && nums[mid + 1] > target)) {
                    resE = mid;
                    break;
                }
                start = mid + 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return new int[]{resS, resE};
    }
}


//eaiser
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int left = 0, right = nums.length - 1;
        // 查找左边界
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // 检查左边界是否为 target
        if (nums[left] != target) {
            return result;
        } else {
            result[0] = left;
        }
        
        // 重置 right，因为我们需要查找右边界
        right = nums.length - 1;
        // 查找右边界
        while (left < right) {
            int mid = left + (right - left) / 2 + 1; // +1 确保 mid 在右侧
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        
        // 检查右边界是否为 target
        result[1] = right;
        
        return result;
    }
}

//best
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int leftBorder = findLeftBorder(nums, target);
        if (leftBorder == -1) {
            return new int[]{-1, -1};
        }
        
        int rightBorder = findRightBorder(nums, target);
        
        return new int[]{leftBorder, rightBorder};
    }
    
    private int findLeftBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
    
    private int findRightBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}

