public class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() <= 1 || s == null) return 0;
        
        int n = s.length();
        Stack<Integer> stack = new Stack();
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                if (!stack.empty()) {
                    if (s.charAt(stack.peek()) == '(') stack.pop();
                    else stack.push(i);
                }
                else stack.push(i);
            }
        }
        if (stack.empty()) return n;
        int a = n, b = 0;
        while(!stack.empty()) {
            b = stack.pop();
            max = Math.max(max, a-b-1);
            a = b;
        }
        max = Math.max(max, a);
        return max;
    }
}
