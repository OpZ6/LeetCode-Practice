//232. Implement Queue using Stacks
//https://leetcode.com/problems/implement-queue-using-stacks/

//only move when need pop or peek, like a queue break into two stack connected end to end
class MyQueue {

    Stack<Integer> res1, res2;

    public MyQueue() {
        res1 = new Stack<>();
        res2 = new Stack<>();
    }
    
    public void push(int x) {
        // 只在res1栈中添加元素
        res1.add(x);
    }
    
    public int pop() {
        // 如果res2栈为空，则将res1栈中的所有元素转移到res2栈中
        if (res2.isEmpty()) {
            while (!res1.isEmpty()) {
                res2.add(res1.pop());
            }
        }
        // 从res2栈中弹出元素
        return res2.pop();
    }
    
    public int peek() {
        // 如果res2栈为空，则将res1栈中的所有元素转移到res2栈中
        if (res2.isEmpty()) {
            while (!res1.isEmpty()) {
                res2.add(res1.pop());
            }
        }
        // 返回res2栈的顶部元素
        return res2.peek();
    }
    
    public boolean empty() {
        // 如果两个栈都为空，则队列为空
        return res1.isEmpty() && res2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
