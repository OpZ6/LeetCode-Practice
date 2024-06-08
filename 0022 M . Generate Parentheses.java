//22. Generate Parentheses
//

//WRONG! DUPLICATE ANSWER.
// class Solution {
//     public List<String> generateParenthesis(int n) {
//         List<String> res = new ArrayList<>();
//         if (n == 1) {
//             res.add("()");
//             return res;
//         }
//         for (String x : generateParenthesis(n - 1)) {
//             res.add("(" + x + ")");
//             res.add("()" + x);
//             res.add(x + "()");
//         }
//         return res;
//     }
// }

//backtrack
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, String cur, int open, int close, int max){
        if(cur.length() == max * 2){
            res.add(cur);
            return;
        }

        if(open < max){
            backtrack(res, cur + "(", open + 1, close, max);
        }
        if(close < open){
            backtrack(res, cur + ")", open, close + 1, max);
        }
    }
}


//stack:iteration
// 初始化一个结果列表res和一个堆栈stack。
// 将初始状态（通常是空字符串、左右括号计数器等）推入堆栈。
// 当堆栈不为空时，执行以下操作：
// 弹出堆栈顶部的状态。
// 检查当前状态是否是有效的括号组合，如果是，则添加到结果列表res。
// 根据当前状态生成新的状态，并将这些新状态推入堆栈。这些新状态通常是通过添加左括号或右括号生成的，同时更新括号计数器。
// 重复步骤3，直到堆栈为空。
//在这个代码中，我们定义了一个内部类State来存储每个状态的信息，包括当前的字符串s、打开括号的数量open和关闭括号的数量close。我们使用一个堆栈stack来存储这些状态，并在循环中处理它们，直到堆栈为空。每次从堆栈中弹出一个状态时，我们检查是否可以添加左括号或右括号，并将新的状态推入堆栈。这样，我们就可以非递归地生成所有有效的括号组合。
class Solution {
    private static class State {
        String s;
        int open;
        int close;
        
        State(String s, int open, int close) {
            this.s = s;
            this.open = open;
            this.close = close;
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        
        Stack<State> stack = new Stack<>();
        stack.push(new State("", 0, 0));
        
        while (!stack.isEmpty()) {
            State tmp = stack.pop();

            if (tmp.open == n && tmp.close == n) {
                res.add(tmp.s);
            }

            if (tmp.open < n) {
                stack.push(new State(tmp.s + "(", tmp.open + 1, tmp.close));
            }
            
            if (tmp.close < tmp.open) {
                stack.push(new State(tmp.s + ")", tmp.open, tmp.close + 1));
            }

        }

        return res;
    }
}
