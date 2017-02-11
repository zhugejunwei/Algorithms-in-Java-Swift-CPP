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


// one stack, O(1) space
public class MinStack {
    Deque<Long> stack = new ArrayDeque();
    long min;
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            stack.push(x - min);
            if (x < min) min = x;
        }
    }
    
    public void pop() {
        long tmp = stack.pop();
        if (tmp < 0) min -= tmp;
    }
    
    public int top() {
        long top = stack.peek();
        if (top < 0) return (int)min;
        else return (int)(min + top);
    }
    
    public int getMin() {
        return (int)min;
    }
}

