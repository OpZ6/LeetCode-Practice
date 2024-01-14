//151. Reverse Words in a String
//https://leetcode.com/problems/reverse-words-in-a-string/description/

class Solution {
    public String reverseWords(String s) {
        String tmp = "";
      
        s += " ";
      //Important Trick！！！
      
        StringBuilder word = new StringBuilder();
        for(char c: s.toCharArray()){         
            if(c != ' '){
                word.append(c);
            }else if(word.length() > 0){
                if(tmp != ""){
                    tmp = " " + tmp;
                }
                tmp = word.toString() + tmp;
                word = new StringBuilder();
            }
        }
        return tmp;
    }
}


// StringBuilder word = new StringBuilder();
// word.toString()

// String[] arr = s.trim().split("\\s+");
// return String.join(",", arr);

class Solution {
    public String reverseWords(String s) {
        // Trim the input string to remove leading and trailing spaces
        String[] str = s.trim().split("\\s+");

        // Initialize the output string
        String out = "";

        // Iterate through the words in reverse order
        for (int i = str.length - 1; i > 0; i--) {
            // Append the current word and a space to the output
            out += str[i] + " ";
        }

        // Append the first word to the output (without trailing space)
        return out + str[0];
    }
}
