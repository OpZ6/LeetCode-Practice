//779. K-th Symbol in Grammar
//

//Time Limit Exceeded
//15 / 55 testcases passed
class Solution {
    public int kthGrammar(int n, int k) {
        String res = "0";
        while (n > 1){
            String tmp = "";
            for (char x : res.toCharArray()){
                if(x == '0'){
                    tmp += "01";
                }
                if(x == '1'){
                    tmp += "10";
                }
            }
            res = tmp;
            n--;
        }
        return res.toCharArray()[k - 1]  - '0';
    }
}

//recursion and formula
class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0; // Base case: the first row is always 0
        if (k % 2 == 1) {
            // If k is odd, the kth symbol is the opposite of the (k+1)/2th symbol in the (n-1)th row
            return kthGrammar(n - 1, (k + 1) / 2);
        } else {
            // If k is even, the kth symbol is the same as the (k+1)/2th symbol in the (n-1)th row
            return 1 - kthGrammar(n - 1, (k + 1) / 2);
        }
    }
}
