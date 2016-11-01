public class Solution {
    public String parseTernary(String expression) {
        char[] ch = expression.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        for (int i = ch.length - 1; i >= 0; i--) {
            char c = ch[i];
            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop(); // pop '?'
                char first = stack.pop();
                stack.pop(); // pop ':'
                char second = stack.pop();
                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
                
            }
        }
        return "" + stack.peek();
    }
}
