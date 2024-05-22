//394. Decode String
//https://leetcode.com/problems/decode-string/description/

// Initialize an empty stack and an empty result string.
// Initialize variables num to 0 and word to an empty string.
// Iterate through each character c in the input string s.
// If c is a digit, update the num by multiplying it by 10 and adding the current digit.
// If c is an opening bracket ‘[’, push the current num and word onto the stack and reset num to 0 and word to an empty string.
// If c is a closing bracket ‘]’, pop the top of the stack into prevNum and prevWord, and append prevWord + word.repeat(prevNum) to word.
// If c is a letter, append it to word.
// After the loop, append word to the result string.
// Return the result string.
class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String result = "";
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                countStack.push(num);
                stringStack.push(result);
                num = 0;
                result = "";
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(result);
                }
                result = temp.toString();
            } else {
                result += c;
            }
        }

        return result;
    }
}
