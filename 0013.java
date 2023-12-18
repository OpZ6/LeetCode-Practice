//13. Roman to Integer
//Easy
//https://leetcode.com/problems/roman-to-integer/description/

class Solution {
    public int romanToInt(String s) {
        if (s == "") {
            throw new IllegalArgumentException("Input String is null");
        }

        int res = 0;
        int tmpCoef = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            char ch = s.charAt(i);
            int count = romanCharToInt(ch);
            if(count >= tmpCoef){
                res += count;
            }else{
                res -= count;
            }
            tmpCoef = count;
        }
        
        return res;
    }

    public int romanCharToInt(char x){
        if(x == 'I'){
            return 1;
        }
        if(x == 'V'){
            return 5;
        }
        if(x == 'X'){
            return 10;
        }
        if(x == 'L'){
            return 50;
        }
        if(x == 'C'){
            return 100;
        }
        if(x == 'D'){
            return 500;
        }
        if(x == 'M'){
            return 1000;
        }
        return 0;
    }
}

//Use Map
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();
        
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        
        int ans = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && m.get(s.charAt(i)) < m.get(s.charAt(i + 1))) {
                ans -= m.get(s.charAt(i));
            } else {
                ans += m.get(s.charAt(i));
            }
        }
        
        return ans;
    }
}
