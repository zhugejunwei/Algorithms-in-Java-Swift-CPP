public class Solution {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        int res = 0, pre = 0, opt = '+', i = 0;
        while (i < s.length()) {
            int cur = 0;
            while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                cur = cur * 10 + s.charAt(i++) - '0';
            }
            if (opt == '+') {
                res += pre;
                pre = cur;
            } else if (opt == '-') {
                res += pre;
                pre = -cur;
            } else if (opt == '*') {
                pre *= cur;
            } else if (opt == '/') {
                pre /= cur;
            }
            if (i < s.length())
                opt = s.charAt(i++);
        }
        return res + pre;
    }
}

// using stack
public class Solution {
    public int calculate(String s) {
        char[] arr = s.replaceAll(" ", "").toCharArray();
        Deque<Integer> stack = new ArrayDeque();
        char sign = '+';
        for (int i = 0; i < arr.length; i++) {
            int val = 0;
            while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                val = val * 10 + arr[i++] - '0';
            }
            if (sign == '+') {
                stack.push(val);
            } else if (sign == '-') {
                stack.push(-val);
            } else if (sign == '*') {
                stack.push(stack.pop() * val);
            } else if (sign == '/') {
                stack.push(stack.pop() / val);
            }
            if (i < arr.length) sign = arr[i];
        }
        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }
}
