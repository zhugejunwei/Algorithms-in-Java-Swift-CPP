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
