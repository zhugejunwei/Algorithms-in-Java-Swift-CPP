// better
public class Solution {
    int len;
    char[] ss;
    public boolean canWin(String s) {
        len = s.length();
        ss = s.toCharArray();
        return canWin();
    }
    public boolean canWin() {
        for (int i = 0; i + 2 <= len; ++i) {
            if (ss[i] == '+' && ss[i + 1] == '+') {
                // mark
                ss[i] = '-'; ss[i + 1] = '-';
                // recursion
                boolean win = !canWin();
                // clean
                ss[i] = '+'; ss[i + 1] = '+';
                if (win) return true;
            }
        }
        return false;
    }
}

// slower
public class Solution {
    public boolean canWin(String s) {
        if (s.length() < 2 || s == null) return false;
        for (int i = 0; i + 1 < s.length(); i++) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);
                
                if (!canWin(t)) return true;
            }
        }
        return false;
    }
}

// same with the first solution
public class Solution {
    // If the next step cannot win, start person win;
    public boolean canWin(String s) {
        return canWin(s.toCharArray(), s.length());
    }
    
    private boolean canWin(char[] s, int len) {
        for (int i = 0; i < len - 1; i++) {
            if (s[i] == '+' && s[i+1] == '+') {
                s[i] = '-';
                s[i+1] = '-';
                boolean win = !canWin(s, len);
                s[i] = '+';
                s[i+1] = '+';
                if (win) return true;
            }
        }
        return false;
    }
}
