public class Solution {
    public int calculate(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque();
        int sign = 1, res = 0;
        for (int i = 0; i < len; i++) {
            // current value
            if (Character.isDigit(arr[i])) {
                int cur = arr[i] - '0';
                while (i + 1 < len && Character.isDigit(arr[i + 1])) {
                    cur = cur * 10 + arr[i + 1] - '0';
                    i++;
                }
                res += cur * sign;
            }
            // use this current value do the later operation
            if (arr[i] == '+') {
                sign = 1;
            } else if (arr[i] == '-') {
                sign = -1;
            } else if (arr[i] == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (arr[i] == ')') {
                res = stack.pop() * res + stack.pop(); // res = sign * cur_res + pre_res
            }
        }
        return res;
    }
}
