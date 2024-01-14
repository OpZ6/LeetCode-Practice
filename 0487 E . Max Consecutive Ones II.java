//487. Max Consecutive Ones II
//https://leetcode.com/problems/reverse-words-in-a-string-iii/description/

class Solution {
    public String reverseWords(String s) {
        String tmp = "";
        s += " ";
        StringBuilder word = new StringBuilder();
        for(char c: s.toCharArray()){         
            if(c != ' '){
                word.append(c);
            }else if(word.length() > 0){
                if(tmp != ""){
                    tmp += " ";
                }
                tmp += word.reverse().toString();
                word = new StringBuilder();
            }
        }
        return tmp;
    }
}

//use split and trim, regular expression
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+"); 
        StringBuilder reversed = new StringBuilder();
        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word);
            reversedWord.reverse(); 
            reversed.append(reversedWord).append(" "); 
        }
        return reversed.toString().trim();
    }
}

//two pointer
public class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' || i == chars.length - 1) {
                int end = (i == chars.length - 1 && chars[i] != ' ') ? i + 1 : i;
                while (start < end) {
                    char temp = chars[start];
                    chars[start] = chars[end - 1];
                    chars[end - 1] = temp;
                    start++;
                    end--;
                }
                start = i + 1;
            }
        }
        return new String(chars);
    }
}
