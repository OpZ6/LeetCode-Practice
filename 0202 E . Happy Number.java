// 202. Happy Number
// https://leetcode.com/problems/happy-number/description/

//my approach: hashmap
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> check = new HashSet<>();
        while (true) {
            n = computeHappy(n);
            if (n == 1) {
                return true;
            } else {
                if (!check.contains(n)) {
                    check.add(n);
                } else {
                    return false;
                }
            }
        }
    }

    public int computeHappy(int n) {
        int res = 0;
        while (n > 0) {
            int x = n % 10;
            res += x * x;
            n /= 10;
        }
        return res;
    }
}
//Complexity Analysis
// Determining the time complexity for this problem is challenging for an "easy" level question. If you're new to these problems, have a go at calculating the time complexity for just the getNext(n) function (don't worry about how many numbers will be in the chain).
// Time complexity : O(243⋅3+log⁡n+log⁡log⁡n+log⁡log⁡log⁡n)... = O(logn).

//better loop end
class Solution {

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {//better!
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}

//Approach 2: Floyd's Cycle-Finding Algorithm
class Solution {

     public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}

//https://leetcode.com/problems/happy-number/editorial/

}
