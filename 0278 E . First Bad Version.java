//278. First Bad Version
//

// /* The isBadVersion API is defined in the parent class VersionControl.
//       boolean isBadVersion(int version); */

// public class Solution extends VersionControl {
//     public int firstBadVersion(int n) {
//         int left = 0;
//         int right = n;
//         int res = Integer.MAX_VALUE;

//         while (left < right) {
//             int mid = left + (right - left) / 2;
//             if (isBadVersion(mid) == false) {
//                 left = mid + 1;
//             } else {
//                 res = Math.min(res, mid);
//                 right = mid;
//             }
//         }

//         if (isBadVersion(left) == true) {
//             res = Math.min(res, left);
//         }

//         return res;
//     }
// }

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        int res = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid) == false) {
                left = mid + 1;
            } else {
                res = Math.min(res, mid);
                right = mid - 1;
            }
        }
        
        return res;
    }
}//No need to change template

//Easier
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid) == false) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}

