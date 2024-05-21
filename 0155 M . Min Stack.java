//155. Min Stack
//https://leetcode.com/problems/min-stack/description/

class MinStack {
    private List<Integer> data;

    public MinStack() {
        data = new ArrayList<>();
    }
    
    public void push(int val) {
        data.add(val);
    }
    
    public void pop() {
        if (!data.isEmpty()) {
            data.remove(data.size() - 1);
        }
    }
    
    public int top() {
        return data.get(data.size() - 1);
    }
    
    public int getMin() {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < data.size(); i++) {
            int tmp = data.get(i);
            res = res < tmp ? res : tmp;
        }
        return res;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

//faster: keep the min
class MinStack {
    LinkedList<TplusMin> stack;
    private class TplusMin {
        int val;
        int min;
        public TplusMin(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    public MinStack() {
        stack = new LinkedList<>();
    }
    
    public void push(int val) {
        int newMin;
        if (stack.size() == 0){
            newMin = val;
        }
        else {
            int currentMin = stack.getFirst().min;
            newMin = val < currentMin ? val : currentMin;
        }
        stack.addFirst(new TplusMin(val, newMin));
    }
    
    public void pop() {
        stack.removeFirst();
    }
    
    public int top() {
        return stack.peekFirst().val;
    }
    
    public int getMin() {
        return stack.peekFirst().min;
    }
}
