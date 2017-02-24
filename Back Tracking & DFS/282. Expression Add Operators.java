public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        helper(res, num, target, "", 0, 0, 0);
        return res;
    }
    
    private void helper(List<String> res, String s, int target, String tmp, int start, long sum, long mulit) {
        if (start == s.length()) {
            if (sum == target) res.add(new String(tmp));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            // skip invalid num
            if (i > start && s.charAt(start) == '0') break;
            long val = Long.valueOf(s.substring(start, i + 1));
            if (start == 0) {
                helper(res, s, target, tmp + val, i + 1, val, val);
            } else {
                // +
                helper(res, s, target, tmp + "+" + val, i + 1, sum + val, val);
                // -
                helper(res, s, target, tmp + "-" + val, i + 1, sum - val, -val);
                // *, A + B * C, (A+B)-B+B*C
                helper(res, s, target, tmp + "*" + val, i + 1, sum-mulit+mulit*val, mulit*val);
            }
        }
    }
}

// super fast solution
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        if (num == null || num.length() == 0) return res;
        char[] path = new char[num.length()*2 - 1];
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < num.length(); i++) {
            if (i > 0 && digits[0] == '0') break;
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            helper(res, path, i + 1, 0, n, digits, i + 1, target);
        }
        return res;
    }
    
    private void helper(List<String> res, char[] path, int len, long sum, long mult, char[] digits, int start, int target) {
        if (start == digits.length) {
            if (sum + mult == target)
                res.add(new String(path, 0, len));
            return;
        }
        long n = 0;
        int j = len + 1; // j is index in path
        for (int i = start; i < digits.length; i++) { // i is index in digits
            if (i > start && digits[start] == '0') break;
            n = n * 10 + digits[i] - '0';
            path[j++] = digits[i];
            // +
            path[len] = '+';
            helper(res, path, j, sum + mult, n, digits, i + 1, target);
            
            // -
            path[len] = '-';
            helper(res, path, j, sum + mult, -n, digits, i + 1, target);
            
            // *
            path[len] = '*';
            helper(res, path, j, sum, mult * n, digits, i + 1, target);
        }
    }
}
