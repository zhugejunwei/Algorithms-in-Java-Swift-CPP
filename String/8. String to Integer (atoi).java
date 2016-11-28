public class Solution {
    public int myAtoi(String str) {
        if (str.length() == 0) return 0;
        char[] s = str.toCharArray();
        int i = 0, res = 0, sign = 1;
        while (s[i] == ' ') ++i;
        if (s[i] == '+') i++;
        else if (s[i] == '-') {
            sign = -1;
            ++i;
        }
        while (i < s.length && s[i] >= '0' && s[i] <= '9') {
            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && s[i] - '0' > 7)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + s[i++] - '0';
            
        }
        return res * sign;
    }
}
