public class Solution {
    public int calculate(String s) {
        int len = s.length();
        char[] c = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque();
        int sign = 1, res = 0;
        for (int i = 0; i < c.length; i++) {
            if (Character.isDigit(c[i])) {
                int tmp = c[i] - '0';
                while (i + 1 < len && Character.isDigit(c[i + 1])) {
                    tmp = tmp*10 + c[i + 1] - '0';
                    i++;
                }
                res += sign * tmp;
            } else if (c[i] == '+') {
                sign = 1;
            } else if (c[i] == '-') {
                sign = -1;
            } else if (c[i] == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c[i] == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }
}
