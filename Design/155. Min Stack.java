// 1 stack
public class MinStack {
    Deque<Integer> stack = new ArrayDeque();
    int min = Integer.MAX_VALUE;
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.pop() == min) min = stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

// 2 stack

public class MinStack {
    Deque<Integer> stack = new ArrayDeque(), minStack = new ArrayDeque();
    
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= getMin()) minStack.push(x);
    }
    
    public void pop() {
        if (getMin() == stack.pop()) minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
