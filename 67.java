//67. Add Binary
//Easy
//https://leetcode.com/problems/add-binary


class Solution {
    public String addBinary(String a, String b) {
        while(a.length() < b.length()){
            a = "0" + a;
        }
        while(b.length() < a.length()){
            b = "0" + b;
        }

        int addUp = 0;
        String res = "";
        for(int i = a.length() - 1; i >= 0; i--){
            int num1 = a.charAt(i) - '0';
            int num2 = b.charAt(i) - '0';
            int sum = addUp + num1 + num2;
            res = ((sum % 2) + "") + res;
            addUp = sum / 2;
        }
        if(addUp == 1){
            return "1" + res;
        }
        return res;
    }
}

//use StringBuilder, eg. append/toString/reverse
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
