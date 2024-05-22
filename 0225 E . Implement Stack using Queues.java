//225. Implement Stack using Queues
//https://leetcode.com/problems/implement-stack-using-queues/

//have to change queue each time push. keep the new element at the front of list;
class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    public void push(int x) {
        // 将新元素加入queue2
        queue2.add(x);
        // 将queue1中的所有元素转移到queue2，保持新元素在队列的前面
        while (!queue1.isEmpty()) {
            queue2.add(queue1.poll());
        }
        // 交换queue1和queue2，使得queue1始终包含所有元素
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    public int pop() {
        // 直接从queue1的前面取出元素
        return queue1.poll();
    }
    
    public int top() {
        // 直接从queue1的前面查看元素
        return queue1.peek();
    }
    
    public boolean empty() {
        // 如果queue1为空，则栈为空
        return queue1.isEmpty();
    }
}


/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
