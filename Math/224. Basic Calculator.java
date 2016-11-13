public class Solution {
    public int calculate(String s) {
        char[] c = s.toCharArray();
        s.replaceAll("\\s", "");
        int len = c.length, sign = 1, res = 0;
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(c[i])) {
                int tmp = c[i] - '0';
                while (i+1 < len && Character.isDigit(c[i+1])) {
                    tmp = tmp * 10 + c[i+1] - '0';
                    i++;
                }
                res += tmp * sign;
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
                res = res*stack.pop() + stack.pop();
            }
        }
        return res;
    }
}
