//507. Perfect Number
//https://leetcode.com/problems/perfect-number/description/

// class Solution {
//     public boolean checkPerfectNumber(int num) {
//         // double maxInt = Math.sqrt(num);
//         if (num == 1) return false;
//         int sum = 0;
//         for (int i = num / 2 + 1; i > 0; i--) {
//             if (num % i == 0) {
//                 sum += i;
//                 if (sum > num) {
//                     return false;
//                 }
//             }
//         } 
//         return sum == num;
//     }
// }

class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }

        int sum = 1;
        for (int d = 2; d * d <= num; ++d) {
            if (num % d == 0) {
                sum += d;
                if (d * d < num) {
                    sum += num / d;
                }
            }
        }
        return sum == num;
    }
}
