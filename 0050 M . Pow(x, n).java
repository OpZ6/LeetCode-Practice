//50. Pow(x, n)
//

//No Recursion
class Solution {
    public double myPow(double x, int n) {
        
        if(n < 0){
            n = -n;
            x = 1 / x;
        }
        
        double pow = 1;
        
        while(n != 0){
            if((n & 1) != 0){
                pow *= x;
            } 
                
            x *= x;
            n >>>= 1;
            
        }
        
        return pow;
    }
}

//Use tail Recursion
class Solution {
    public double myPow(double x, int n) {
        // 处理n为负数的情况
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return helper(x, n);
    }

    // 递归辅助函数，处理n为正数的情况
    private double helper(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = helper(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}

