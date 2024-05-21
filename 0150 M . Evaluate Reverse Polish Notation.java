//150. Evaluate Reverse Polish Notation
//https://leetcode.com/problems/evaluate-reverse-polish-notation/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();

        for (String token : tokens) {
            if (!(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))) {
                s.push(Integer.valueOf(token));
            } else {
                int nxt = s.pop();
                int pre = s.pop();
                if (token.equals("+")) {
                    s.push(pre + nxt);
                }
                if (token.equals("-")) {
                    s.push(pre - nxt);
                }
                if (token.equals("*")) {
                    s.push(pre * nxt);
                }
                if (token.equals("/")) {
                    s.push(pre / nxt);
                }                
            }
        }
        return s.pop();
    }
}

// 避免在每次调用 pop 之后立即调用 push，而是先将操作数压入栈中，然后进行操作。这样可以减少栈的操作次数。
// 使用一个临时变量来存储第二个操作数，这样可以避免在每次操作后都需要调用 pop。
// 使用 Integer.parseInt 方法时，如果 token 不是一个有效的整数，它会抛出 NumberFormatException。为了避免这个异常，我们可以先检查 token 是否可以被解析为整数。
// 为了简化代码，我们可以将所有的运算符和操作数都视为字符串，并在执行运算时再将字符串转换为整数。这样可以避免使用 Integer.valueOf 方法，从而避免可能出现的异常。

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> s = new Stack<>();

        for (String token : tokens) {
            if (!s.isEmpty() && !token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                String op = s.pop();
                s.push(op);
            }
            s.push(token);
        }

        String result = "";
        while (s.size() >= 2) {
            String op = s.pop();
            String nxt = s.pop();
            String pre = s.pop();
            if (op.equals("+")) {
                result = String.valueOf(Integer.parseInt(pre) + Integer.parseInt(nxt));
            } else if (op.equals("-")) {
                result = String.valueOf(Integer.parseInt(pre) - Integer.parseInt(nxt));
            } else if (op.equals("*")) {
                result = String.valueOf(Integer.parseInt(pre) * Integer.parseInt(nxt));
            } else if (op.equals("/")) {
                result = String.valueOf(Integer.parseInt(pre) / Integer.parseInt(nxt));
            }
            s.push(result);
        }

        return Integer.parseInt(s.pop());
    }
}
