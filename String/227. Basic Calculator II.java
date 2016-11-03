public class Solution {
    public int calculate(String s) {
        if (s == null) return 0;
        s = s.replaceAll(" ", "");
        int len = s.length();
        
        int res = 0, pre = 0, sign = '+', i = 0;
        while (i < len) {
            int cur = 0;
            while (i < len && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                cur = cur * 10 + s.charAt(i++) - '0';
            }
            if (sign == '+') {
                res += pre;
                pre = cur;
            } else if (sign == '-') {
                res += pre;
                pre = -cur;
            } else if (sign == '*') {
                pre *= cur;
            } else if (sign == '/') {
                pre /= cur;
            }
            if (i < len) {
                sign = s.charAt(i++);
            }
        }
        res += pre;
        return res;
    }
}
