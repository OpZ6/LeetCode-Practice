//20. Valid Parentheses
//https://leetcode.com/problems/valid-parentheses/description/

//first
class Solution {
    public boolean isValid(String s) {
        Stack<Character> store =  new Stack<>();

        int pos = 1;
        int length = s.length();

        if (length % 2 == 1) {
            return false;
        }

        if (length == 0) {
            return true;
        }

        store.push(s.charAt(0));

        while(pos < length) {
            char nxt = s.charAt(pos);
            if (!store.isEmpty()) {
                char pre = store.peek();
                if (checkPair(pre, nxt)) {
                    store.pop();
                } else {
                    store.push(nxt);
                }
            } else {
                store.push(nxt);
            }
            pos++;
        }

        return store.isEmpty();
    }

    private boolean checkPair(char x, char y) {
        if ((x == '(' && y == ')') || (x == '{' && y == '}') || (x == '[' && y == ']')) {
            return true;
        }
        return false;
    }
}

//easier
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}
